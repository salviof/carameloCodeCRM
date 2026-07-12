# language: pt
@FluxoTypebotFormulario
Funcionalidade:
Sincronização de formulários Typebot
Dado que a API Typebot está configurada com formulários cadastrados
1) Um dos formulários deve estar programado para ser processado automaticamente
2) Quero buscar os formulários do Typebot Para armazená-los no banco via JPA

Contexto: BancoSemFormularioTypebot


Cenario: Importar todos os formulários disponíveis

Dado o servico de typebot configurado
Quando o sistema requisitar a lista de formulários
Então a API deve retornar os formulários existentes
E o sistema deve salvar cada formulário no repositório JPA