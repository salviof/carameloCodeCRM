# language: pt
@AgendaConsultorDefinirAgenda
Funcionalidade: Testar a definição de horários para agendamentos
1) Logar com usuário do grupo atendimento
2) Definir agendamento de reunião presencial, para segunda terça e sexta entre 13:00 às 16:00
3) Definir o agendamento de reunião online, para segundas e quintas das 8:00 às 12:00
4) Listar os horários disponíveis do usuário para reunião presencial
5) Listar os horarios disponíveus para reunião online


Cenario: Banco com Atividades e usuário atendimento
Dado O usuário atendimento logado
E escopo de pesquisa remoto para segunda dás 8 às 15
E escopo de pesquisa presencial quarta e sexta das 15 as 19
Quando O usuário altera seus horários disponíveis para reunião presencial
Entao Uma nova configuração de agenda presencial é gerada
Dado O usuário atendimento logado
Quando O usuário altera seus horários disponíveis para reunião remota
Entao Uma nova configuração de agenda para reunião remota é gerada
E A Lista de Horários diponíveis para reunião presencial está de acordo com o Cadastrado
E A Lista de Horários diponíveis para reunião online está de acordo com o Cadastrado
Quando uma reserva é realizada para segunda as 8 horas
E o usuario tenta encontrar este horario novamente
Entao o usuário não encontra mais este horário