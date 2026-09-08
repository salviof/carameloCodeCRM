# language: pt
@ReservaLinkPublico
Funcionalidade: Testar marcação de reserva através de link público
Uma pessoa acessa o link público de agendamento e realiza a marcação
de uma reserva, com tratamento para visitantes novos, reservas ativas
e emails já cadastrados no sistema.

    Contexto: FLuxo agenda publia com disponilidade

Cenário: Visitante novo cria uma reserva
Dado um Tipo de Agendamento Primeira Consultoria com disponibilidade
Quando a pessoa preenche nome e email não encontrados no sistema
E seleciona um horário disponível do atendente Salvio Furbino
Então uma nova reserva é criada com status ativo
E o horário selecionado deixa de aparecer como disponível no link público
E a pessoa é notificada sobre a reserva pelo email
E o atendente Salvio Furbino é notificado pelo chat interno

