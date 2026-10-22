# CarameloCode CRM

CRM em Java (JPA/Hibernate + JSF/PrimeFaces) construído sobre o framework SuperBits/coletivoJava.
Responda sempre em português do Brasil.

## Estrutura

| Módulo | Conteúdo |
|---|---|
| `modelRegras` | Entidades JPA, enums de ações (`Fab*`), controllers (`Modulo*`), testes cucumber |
| `webApp` | Aplicação JSF: páginas (`Pg*`), serviços de tela, xhtml, composite components em `src/main/webapp/resources` |
| `webappExtensor` | **Cópia gerada** do `webApp` (java, site, resources) pelo `crmAtualizarExtensor.sh`. Não edite à mão: altere o `webApp` e rode o script |
| `apiJava`, `clientERP` | API e cliente ERP |

Para o módulo de agenda/disponibilidade/reserva, leia `CLAUDE_AGENDA.md` antes.

### Framework (fora deste repositório)

- Framework base: `/home/superBits/projetos/coletivoJava/source/fw` (`SBCore`, `SBApiCore`, `SBWebPaginas`, `util/gestaoArquivosProtocoloS3`, ...). Cada submódulo é um repositório git próprio.
- Módulos ERP: `/home/superBits/projetos/coletivoJava/source/erpColetivoJava`.
- Execução de IA (ExecucaoIA, Persona, ModuloExecucaoAcao): `/home/superBits/projetos/carameloCode/source/iaCarameloExecucaoPadrao`.
- O CRM usa esses módulos como dependências `SNAPSHOT`: depois de alterar o framework, rode `mvn install` no módulo alterado antes de compilar o CRM.

## Arquivos gerados (não editar à mão)

- `modelRegras/.../api/model/<entidade>/` (`CP*`, `ValoresLogicos*`, anotação `ValorLogico*`): gerados a partir das entidades. Altere só a entidade e as implementações em `implemetation/model/...` e avise para rodar o gerador.
- `webApp/.../org/coletivoJava/superBitsFW/webPaginas/config/Acoes*` (ex.: `AcoesAdminCrm_ExecucaoIaMbGestao`): beans de acesso às ações, gerados a partir dos enums `Fab*`. Ao criar uma ação nova não crie o getter; o xhtml já pode referenciar o nome que será gerado (`EXECUCAO_IA_CTR_CRIAR_DIALOGO_ATIVIDADE` → `#{acoesAdminCrm_ExecucaoIaMbGestao.execucaoIaCtrCriarDialogoAtividade}`).

## Padrões do código

### Ações

- Declaradas em enums `Fab*` por domínio em `modelRegras/.../crm/api/dominio/acoes/<dominio>/` (ex.: `FabAcaoCrmAdmin`, `FabAcaoCRMIA`).
- Nomenclatura: `<ENTIDADE>_MB_GESTAO` (`@InfoTipoAcaoGestaoEntidade`), `<ENTIDADE>_FRM_<ACAO>` (`@InfoTipoAcaoFormulario`: `LISTAR`, `NOVO`, `EDITAR`, `VISUALIZAR`, `REMOVER`), `<ENTIDADE>_CTR_<ACAO>` (`@InfoTipoAcaoController`: `SALVAR_MERGE`, `REMOVER`, ...).
- Implementação no controller do domínio (`ModuloCRMAdmin`, `ModuloCRM_IA`, ...) com a anotação do domínio, ex.: `@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.X)`, método `public static ItfRespostaAcaoDoSistema`.
- Corpo padrão: `new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(p), p) { regraDeNegocio() {...} }`. Use `atualizarEntidade`, `loadEntidade`, `removerEntidade`; erros de regra com `throw new ErroRegraDeNegocio(...)`; SQL direto com `UtilSBPersistencia.executaSQL(getEMResposta(), ...)`.

### Páginas

- `webApp/.../crm/paginas/<dominio>/Pg*`: `@Named @ViewScoped @InfoPagina(...)` + anotação da ação de gestão, estendendo `MB_paginaCadastroEntidades<Entidade>`. Exemplo: `PgModeloEmailMkt`.

### Execução de IA

- Cada entidade editável com IA tem uma subclasse de `ExecucaoIA` em `modelRegras/.../ia/entidadesJPA/execucaoAtividade/` com `prepararNovoObjeto` (`@InfoPreparacaoObjeto`) que define a entidade alvo e a persona. O mapeamento entidade → classe de execução fica em `FabTipoExecucaoIA.getClasseExecucaoPorEntidade`.
- `ServicoIA` (webApp) localiza o diálogo pela entidade selecionada na página (`entidadeAlvoNome` = nome da classe concreta sem proxy + id).
- Composite components em `webApp/src/main/webapp/resources/iaDialogos/`: `iaInlineEditor` recebe `acaoCriarDialogo` e `acaoAtualizarDados`; os componentes específicos (`iaInlineEditorModeloEmailMkt`, ...) só repassam as ações. `recarregarEntidade="false"` para ações transitórias (alteram a entidade em memória sem gravar).

## Build e testes

- Compilar: `mvn -o -q -pl modelRegras install -DskipTests` e depois `mvn -o -q -pl webApp compile`.
- Testes de regra: cucumber em `modelRegras/src/test` (features em `src/test/resources/caracteristicas/`).
- Execução local: `jetty:run` em `webApp` (porta 8080), servindo `src/main/webapp` direto (xhtml/css valem sem reiniciar).
- `crm.casanovadigital.com.br` aponta para o servidor remoto e não recebe edições locais sem deploy. Quando uma correção "não funcionar", confirme primeiro em qual URL foi testado.

## Git

- Os commits do repositório são automáticos ("Atualizacao automática"). Não faça commit sem pedido explícito.
