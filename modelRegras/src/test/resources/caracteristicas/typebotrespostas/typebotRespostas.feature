# language: pt
@FluxoTypebotRespostas
Funcionalidade:
Sincronização de respostas dos formularios programados para serem sincronizados
Dado que o sistema sincronizou um formulário que deve ser processado
1) As repostas devem ser buscadas no servidor de typebot
2) Caso não exista a pessoa, um novo cadastro deve ser preenchido, buscando NOME EMAIL e TELEFONE
3) Os dados dinamicos devem ser atualizados.

Contexto: FluxoTypebotFormulario


Cenario: Processar respostas do formulário

Dado que existe um formulário a ser processado no banco de dados
Quando o sistema processa respostas
Então então uma nova pessoa é registrada
E os dados dinamicos são atualizados