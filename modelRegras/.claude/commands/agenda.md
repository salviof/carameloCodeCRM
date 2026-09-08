---
description: Diagnostica e corrige falhas nos fluxos de agenda/disponibilidade/reserva
argument-hint: <teste, passo e mensagem de erro>
---

@CLAUDE_AGENDA.md

## Tarefa

$ARGUMENTS

## Como proceder

1. **Diagnostique antes de corrigir.** Se eu não informei o passo ou a mensagem
   de erro acima, rode o teste primeiro (redirecionando para `/tmp/teste.log`) e
   use os marcadores de grep do guia para localizar a falha. Não leia o log
   inteiro nem classes inteiras — leia só o trecho que o sintoma exigir.
2. **Explique a causa raiz** com o arquivo e a linha, e cite a evidência do log
   (SQL emitido, slots gerados, id da disponibilidade que aprovou).
3. **Confirme comigo antes de editar `SbErpAgenda`** — é módulo compartilhado com
   outros projetos. Alterações apenas em `modelRegras/src/test` pode aplicar
   direto.
4. Ao alterar `SbErpAgenda`, rode o `mvn install` dele antes de reexecutar o
   teste do CRM (comandos no guia).
5. **Relate o resultado real** da reexecução: quantos passos passaram, qual passo
   falha agora e por quê. Se a falha migrou para um passo diferente, diga
   explicitamente que é outro problema em vez de tratar como sucesso.
6. Se descobrir uma armadilha nova (fonte divergente de dados, fuso, formato de
   hora, cache em memória), acrescente-a à seção **Armadilhas conhecidas** do
   `CLAUDE_AGENDA.md`.
