# language: pt
@ReservaAtivaLinkPublico
Funcionalidade: Testar marcação de reserva através de link público
Uma pessoa acessa o link público de agendamento e realiza a marcação
de uma reserva, com tratamento para visitantes novos, reservas ativas
e emails já cadastrados no sistema.

    Contexto:
Dado um Tipo de Agendamento Primeira Consultoria
E com disponibilidade cadastrada para o atendente Salvio Furbino
E uma pessoa acessando o link público de agendamento

Cenário: Pessoa com reserva ativa acessa o link novamente
Dado uma pessoa com uma reserva ativa
Quando ela entra no link público de agendamento
Então ela é redirecionada para a tela com os dados da sua reserva
E nenhuma nova reserva é criada

