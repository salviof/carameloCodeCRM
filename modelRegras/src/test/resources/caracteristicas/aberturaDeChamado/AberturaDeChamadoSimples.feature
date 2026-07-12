# language: pt
@AberturaDeChamadoSimples
Funcionalidade: Testar abertura e fechamento de chamados


Contexto: Um cliente, abre um chamado, e o ciclo completo de abertura e interação acontece.

Cenario: Banco Sem Campanha
Dado Um cliente pré cadastrado logado
Quando o Cliente cria um novo chamado
Entao o atendente principal do cliente é notificado pelo chat interno
E o cliente é notificado sobre a abertura pelo email
Quando o atendente assume o chamado
Entao uma sala entre o atendente e o cliente é criado
E as mensagens enviadas nesta sala são encaminhadas para o whatsapp do cliente que abriu o chamado
Dado um chamado aberto
Quando o atendente fecha o chamado
Entao o cliente é notificado via email
E a os usuários são retirados da sala de chamado
E o statuso do chamado é alterado para finalizado
Dado um chamado fechado
Quando o chamado é reaberto
E o atendimento define um novo responsável pelo chamado
Entao O responsavel é notificado
E o responsavel é adicionado na sala junto ao atendente principal




