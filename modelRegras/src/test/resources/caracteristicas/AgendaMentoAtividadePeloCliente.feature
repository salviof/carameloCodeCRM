# language: pt
#DEPRECIADO, A CAMPANHA SÓ CONVERTE QUANDO ATINGE A DATA
@AgendamentoAtividadePeloCliente
Funcionalidade: Testar o Agendamento de uma campanha para o cliente

  Contexto: Consultores cadastrados, com suas respectivas agenda

  Cenario: Banco Sem Campanha
    Dado Uma atividade 
    Quando Uma nova campanha é criada e aprovada
    Entao Uma campanha com status Vigente com data final menor que 10 min é criada
    Quando O tempo para expirar passa
    Entao O status da campanha passa a ser Cancelado
    Quando Uma nova campanha é criada e aprovada
    Então Uma campanha com status Vigente com data final menor que 10 min é criada
    Quando O Comprador faz um pedido com 40 unidades e paga
    Então Um pedido com Status Aguardando fim da campanha é gerado
    Quando O tempo para expirar passa
    Então a campanha muda para status convertida
    E o pedido muda para status FINALIZADO
