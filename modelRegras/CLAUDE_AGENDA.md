# Contexto — Módulo de Agenda (CarameloCode CRM)

Guia para trabalhar nos fluxos de agenda / disponibilidade / reserva de horário.
Leia isto antes de sair explorando o código.

## Onde fica cada coisa

| O quê | Caminho |
|---|---|
| Testes cucumber (regras) | `/home/superBits/projetos/carameloCode/source/carameloCodeCRM/modelRegras` |
| Features | `modelRegras/src/test/resources/caracteristicas/<fluxo>/*.feature` |
| Passos (steps) | `modelRegras/src/test/java/.../cucumber/<fluxo>/etapas/` |
| **Módulo de agenda (fonte)** | `/home/superBits/projetos/coletivoJava/source/erpColetivoJava/SbErpAgenda` |
| Web (JSF) | `carameloCodeCRM/webApp` |

> O módulo de agenda **não** está no repositório do CRM — é a dependência maven
> `br.org.carameloCode.erp.agenda:SbErpAgenda:1.0.0-RC-SNAPSHOT`.
>
> Existem cópias obsoletas em `source/modelupdate/` e `source/bkp/`. **Ignore.**
> Nunca use `find /` para localizar classes; procure direto em `SbErpAgenda/src`.

Os arquivos de passo são prefixados `A_`, `B_`, … `M_` na ordem de execução do
cenário, e o runner (`Fluxo*.java`, com `@CucumberOptions`) define a tag e o
pacote `glue`.

## Como rodar

```bash
cd /home/superBits/projetos/carameloCode/source/carameloCodeCRM/modelRegras
JAVA_HOME=/usr/java/jdk1.8.0_202-amd64 /usr/lib/apache-netbeans/java/maven/bin/mvn \
  -Dtest=<CLASSE_RUNNER_FQN> -DforkMode=once --no-transfer-progress \
  process-test-classes surefire:test > /tmp/teste.log 2>&1
```

Para debug remoto, acrescente:
`-Dmaven.surefire.debug=-agentlib:jdwp=transport=dt_socket,server=n,address=44115 -Djpda.listen=true -Djpda.address=44115`

**Ao alterar `SbErpAgenda`, instale antes de rodar o teste do CRM:**

```bash
cd /home/superBits/projetos/coletivoJava/source/erpColetivoJava/SbErpAgenda
JAVA_HOME=/usr/java/jdk1.8.0_202-amd64 /usr/lib/apache-netbeans/java/maven/bin/mvn \
  -o -DskipTests --no-transfer-progress install
```

## Lendo o log (passa de 30.000 linhas — nunca leia inteiro)

Sempre redirecione para arquivo e use `grep`/`tail`. Marcadores úteis:

| Marcador | Significa |
|---|---|
| `Pesquisando melhores horários no dia` | dia varrido pelo DeLorean |
| `Analizando horário` | slot candidato gerado |
| `OK dentro do escopo código N` | slot aprovado pela disponibilidade de id N |
| `não existe disponibilidade cadastrada` | slot rejeitado |
| `Negado devido a horas de antecedência` | barrado por antecedência mínima |
| `Failed scenarios`, `Steps (`, `Tests run:` | resumo |

Diagnóstico rápido de "qual disponibilidade aprovou os horários":

```bash
grep "OK dentro do escopo código" /tmp/teste.log | awk -F'código ' '{print $2}' | sort | uniq -c
```

Se todos os slots vierem de um único id quando deveria haver mais de uma
disponibilidade em jogo (ou vice-versa), o problema é de filtragem.

## Pipeline de geração de horários

```
AgendaDisponibilidade.getHorariosDisponiveis()
  └─ loadgendaXDias(n)
       └─ new TokenLinhaDoTempoDeLorean(filtro, ...)
            │  construtor:
            │    disponibilidades = MapaHorariosDisponiveis.getDisponibilidadesDoEscopo(filtro)
            │    ^^^ ÚNICA fonte válida: filtra por tipoAgendamento, atendente,
            │        período e ativo (via UtilSBAgendaHorariosDisponiveis
            │        .isDisponibilidadeDentroDoEscopo)
            └─ loadHorariosDoDia()
                 ├─ filtra disponibilidades do dia (dia da semana + intervalo de datas)
                 ├─ funde em IntervaloHorariosDoDia (união dos intervalos)
                 ├─ fatia em slots de tipoAgendamento.getDuracaoAtendenteMinutos()
                 └─ valida cada slot em
                    UtilSBAgendaHorariosDisponiveis.isHorarioDentroDisponibilidade
                    (LocalTime em America/Sao_Paulo + antecedência mínima)
```

Classes-chave em
`SbErpAgenda/src/main/java/br/org/carameloCode/erp/modulo/agenda/`:

- `entidadesJPA/escopoPesquisa/AgendaDisponibilidade.java` — fachada usada pelos testes e telas
- `regradeNegocio/mapeamentoAgenda/TokenLinhaDoTempoDeLorean.java` — varredura da linha do tempo
- `regradeNegocio/mapeamentoAgenda/MapaHorariosDisponiveis.java` — cache em memória + filtro por escopo
- `regradeNegocio/mapeamentoAgenda/UtilSBAgendaHorariosDisponiveis.java` — regras de "cabe ou não cabe"
- `regradeNegocio/mapeamentoAgenda/IntervaloHorariosDoDia.java` / `ComparadorDisponibilidadeOrdemHorario.java`

## Armadilhas conhecidas

- **Fontes divergentes de disponibilidade.** Se a geração de slots e a validação
  usarem listas de disponibilidade obtidas por caminhos diferentes, uma aceita o
  que a outra rejeita. Já aconteceu: `loadHorariosDoDia` reconsultava o banco
  filtrando só por `usuarioResponsavel` + `ativo`, **sem filtrar tipo de
  agendamento**, e a disponibilidade PRESENCIAL vazava para a busca de
  CONFERÊNCIA. Corrigido em 2026-08-31 passando a usar o campo `disponibilidades`.
- **Fusão de intervalos.** A união de dois intervalos que se tocam tem que
  terminar no **maior** dos dois finais. Já houve sobrescrita cega do fim, que
  encolhia a janela (8:00–20:00 + 8:30–12:30 virava 8:00–12:30). Corrigido em
  2026-08-31.
- **Cache de agenda em duas camadas (`AgendaDisponibilidade`).** `getHorariosDisponiveis()`
  só dispara `loadgendaXDias` quando `isTemAgenda()` é falso **e** o campo
  `horariosDisponiveis` está vazio; depois lê de `mapaHorariosDisponiveis
  .get(diaSelecionado)`. Consequências que já morderam:
  - `isTemAgenda()` recalcula `temAgenda = !mapaHorariosDisponiveis.isEmpty()`, então
    zerar só a flag `temAgenda` (o que todos os `limpar*` faziam) **não** invalida nada.
    Sintoma: nenhuma varredura nova (`Pesquisando melhores horários no dia` para de
    aparecer) e a lista devolvida é a da consulta anterior — reserva recém-criada
    continua listada. Corrigido em 2026-09-01: os `limpar*` chamam
    `limparAgendaCarregada()`, que limpa mapa, campo `horariosDisponiveis` e
    `dataultimaAtualizacaoAgenda`.
  - Limpar o mapa **sem** limpar o campo `horariosDisponiveis` é pior que não limpar:
    o reload é pulado e a leitura cai num mapa vazio → lista vazia
    (`IndexOutOfBoundsException: Index: 0, Size: 0` nos passos que fazem `.get(0)`).
  - O recálculo por tempo estava invertido (`> 5l` onde o comentário diz "recalcula a
    cada 5 minutos") e `dataultimaAtualizacaoAgenda` nunca era renovado após um load.
    Corrigido em 2026-09-01.
- **Nos testes, use `limparPesquisa()` e não `limparAtendenteDefinido()`** para forçar
  releitura da agenda. `limparAtendenteDefinido()` anula `usuarioAtendente`, o que
  desarma o curto-circuito no topo de `setUsuarioAtendente` e faz o método trocar o
  `escopo` pelo `escopoAgendaClientes` do usuário — descartando o escopo montado nos
  passos anteriores e devolvendo lista vazia.
- **O bloqueio por reserva era avaliado sobre o horário errado.**
  `TokenLinhaDoTempoDeLorean` chamava `filtro.getBloqueioHorario(horarioMomentoAtual)`
  em vez de `novoHorarioDisponivel`; `horarioMomentoAtual` só é atribuído depois no
  laço, então no primeiro slot do dia ia `null` e `isHorarioOcupadoPorReserva(null)`
  devolvia `false`. Marcador de sanidade: se o cenário tem reserva e
  `Negado por conter reserva no mesmo horário` não aparece no log, o filtro não rodou.
  Corrigido em 2026-09-01.
- **`AgendaDisponibilidade.setUsuarioAtendente`** troca o `escopo` pelo
  `escopoAgendaClientes` do usuário quando `isUsandoEscopoPadrao()`. Chamar isso
  *depois* de configurar o escopo no teste descarta a configuração.
- **`hh:mm` em `SimpleDateFormat`** é formato de 12 horas. Vários passos de teste
  usam `new SimpleDateFormat("hh:mm")`; para horários ≥ 13h use `HH:mm` ou
  `UtilCRCDataHora.converteString_HH_doisPontos_mm_EmData`.
- Comparações de horário usam `LocalTime` fixado em `America/Sao_Paulo`
  (`isHorarioDentroDisponibilidade`), enquanto o resto usa o fuso default da JVM.

## Estado atual dos testes

`FluxoAgendaDoConsultor` (tag `@AgendaConsultorDefinirAgenda`): **13 passos, todos
passando** (`Tests run: 14, Failures: 0, Errors: 0`) desde 2026-09-01.

`K` guarda a reserva persistida em `FluxoAgendaDoConsultor.reservaHorario` via
`(ReservaHorario) resp.getRetorno()` — não use a instância pré-`merge`, ela é
descartada por `atualizarEntidadesDeclaradas()`. `M` relê a agenda e afirma que
nenhum horário livre se sobrepõe a essa reserva.

Ruído esperado no log e sem relação com a agenda: `NullPointerException` em
`DriverFWBancoJPANativo.selecaoRegistro` e `IllegalAccessException: Can not set
static final ... escopoAtendimentoRemoto to null`, ambos vindos de
`atualizarEntidadesDeclaradas()` tentando recarregar os campos `static final` do
runner.

## Como me passar a tarefa (economiza tokens)

Diga **em qual passo falha** e **com qual mensagem** — isso me poupa a primeira
execução exploratória. Peça o diagnóstico antes da correção, e peça confirmação
antes de editar `SbErpAgenda`, que é módulo compartilhado.
