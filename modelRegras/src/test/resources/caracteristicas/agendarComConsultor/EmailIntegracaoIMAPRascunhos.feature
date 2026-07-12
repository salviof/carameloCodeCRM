# language: pt
#DEPRECIADO, A CAMPANHA SÓ CONVERTE QUANDO ATINGE A DATA
@IntegrandoRascunhoIntranetComRascunhoImap
Funcionalidade: Testar a conversão por tempo da campanha
  1) A campanha deve ser cadastrada por um fornecedor

  Contexto: BancoSemCampanhaCom3CompradoresEUmFornecedor

  Cenario: Banco Sem Campanha
    Dado O cenário banco sem campanha
    Quando Uma nova campanha é criada e aprovada
    Entao Uma campanha com status Vigente com data final menor que 10 min é criada
