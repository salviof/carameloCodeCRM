# language: pt
#DEPRECIADO, A CAMPANHA SÓ CONVERTE QUANDO ATINGE A DATA
@CampanhaConvertidaPorTempo
Funcionalidade: Testar a conversão por tempo da campanha
  1) A campanha deve ser cadastrada por um fornecedor
  2) A campanha deve ser aprovada pelo Administrador
  3) A campanha deve conter 3 produtos->Manteiga Achocolatado e Leite Moça
  4) O Valor Máximo em volume principal por filial deve ser 40
  5) O Minimo 2
  6) Conversao com 30
  7) Minimo Conversao 20
  8) estoque 40

  Contexto: BancoSemCampanhaCom3CompradoresEUmFornecedor

  Cenario: Banco Sem Campanha
    Dado O cenário banco sem campanha
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
