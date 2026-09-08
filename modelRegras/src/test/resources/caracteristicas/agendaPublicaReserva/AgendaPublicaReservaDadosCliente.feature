# language: pt
@ReservaLinkPublicoDadosCliente
Funcionalidade: Testar marcação de reserva através de link público
Uma pessoa acessa o link público de agendamento e realiza a marcação
de uma reserva, com tratamento para visitantes novos, reservas ativas
e emails já cadastrados no sistema.

    Contexto:
Dado um Tipo de Agendamento Primeira Consultoria
E com disponibilidade cadastrada para o atendente Salvio Furbino
E uma pessoa acessando o link público de agendamento


Cenário: Email já cadastrado no sistema sem reserva ativa
Dado um email já cadastrado no sistema sem reserva ativa
Quando a pessoa tenta criar uma reserva utilizando este email
Então nenhuma reserva é criada
E o usuário é recomendado a entrar pela área do cliente