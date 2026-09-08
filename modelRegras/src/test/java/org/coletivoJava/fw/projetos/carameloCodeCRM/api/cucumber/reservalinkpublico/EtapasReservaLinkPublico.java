package org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.reservalinkpublico;
public enum EtapasReservaLinkPublico {
	_DADO_UM_TIPO_DE_AGENDAMENTO_PRIMEIRA_CONSULTORIA_COM_DISPONIBILIDADE, _QUANDO_A_PESSOA_PREENCHE_NOME_E_EMAIL_NAO_ENCONTRADOS_NO_SISTEMA, _E_SELECIONA_UM_HORARIO_DISPONIVEL_DO_ATENDENTE_SALVIO_FURBINO, _ENTAO_UMA_NOVA_RESERVA_E_CRIADA_COM_STATUS_ATIVO, _E_O_HORARIO_SELECIONADO_DEIXA_DE_APARECER_COMO_DISPONIVEL_NO_LINK_PUBLICO, _E_A_PESSOA_E_NOTIFICADA_SOBRE_A_RESERVA_PELO_EMAIL, _E_O_ATENDENTE_SALVIO_FURBINO_E_NOTIFICADO_PELO_CHAT_INTERNO;

	public static final String DADO_UM_TIPO_DE_AGENDAMENTO_PRIMEIRA_CONSULTORIA_COM_DISPONIBILIDADE = "um Tipo de Agendamento Primeira Consultoria com disponibilidade";
	public static final String QUANDO_A_PESSOA_PREENCHE_NOME_E_EMAIL_NAO_ENCONTRADOS_NO_SISTEMA = "a pessoa preenche nome e email não encontrados no sistema";
	public static final String E_SELECIONA_UM_HORARIO_DISPONIVEL_DO_ATENDENTE_SALVIO_FURBINO = "seleciona um horário disponível do atendente Salvio Furbino";
	public static final String ENTAO_UMA_NOVA_RESERVA_E_CRIADA_COM_STATUS_ATIVO = "uma nova reserva é criada com status ativo";
	public static final String E_O_HORARIO_SELECIONADO_DEIXA_DE_APARECER_COMO_DISPONIVEL_NO_LINK_PUBLICO = "o horário selecionado deixa de aparecer como disponível no link público";
	public static final String E_A_PESSOA_E_NOTIFICADA_SOBRE_A_RESERVA_PELO_EMAIL = "a pessoa é notificada sobre a reserva pelo email";
	public static final String E_O_ATENDENTE_SALVIO_FURBINO_E_NOTIFICADO_PELO_CHAT_INTERNO = "o atendente Salvio Furbino é notificado pelo chat interno";
}