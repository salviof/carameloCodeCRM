# language: pt
@EnvioOrcamentoFatura
Funcionalidade: Testar o envio de orçamentos para o sistema de faturamento

  Contexto: Consultores cadastrados, com suas respectivas agenda

  Cenario: Teste envio de orçamento
    Dado uma chave de acesso ao fatura funcionando
    Dado um usuario vendedor logado
    Quando um novo orçamento com 3 itens sazonais e um mensal é salvo
    Entao um novo orçamento com 4 itens e  status rascunho é gerado
    Dado um usuario vendedor logado
    Quando o usuário fatura o orçamento
    Entao um orçamento com 4 itens é gerado no sistema fatura
    E oi status do orçamento é alterado para faturado
