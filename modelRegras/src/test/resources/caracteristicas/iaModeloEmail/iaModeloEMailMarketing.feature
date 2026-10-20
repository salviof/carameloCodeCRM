# language: pt
@IAModeloEmailMarketing
Funcionalidade: Criar modelos de email marketing com apoio de IA
Como atendente
Quero criar modelos de email baseados em uma persona
Para que o conteúdo seja gerado com IA e sincronizado com o Mautic

Contexto:

Cenario: Atendente cria uma persona, e persnaliza um modelo de email marketing no mautic
Dado um atendente autenticado no sistema


Quando o atendente cria uma nova persona
Entao a persona aparece na lista de personas para novos modelos de email


Dado uma persona cadastrada
E o atendente seleciona a persona em um novo modelo de email
Quando o atendente salva o modelo
Entao o modelo fica registrado no sistema
E o modelo e enviado para o Mautic


Dado um modelo de email salvo com uma persona associada
E o atendente ativa o assistente de IA no modelo
Quando o atendente envia instrucoes de atualizacao ao assistente
Entao o assistente gera um novo conteudo coerente com a persona
E o novo conteudo e enviado para o Mautic


