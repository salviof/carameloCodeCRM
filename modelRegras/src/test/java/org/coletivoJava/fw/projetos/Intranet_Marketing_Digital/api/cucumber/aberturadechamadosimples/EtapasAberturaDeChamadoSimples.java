package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples;
public enum EtapasAberturaDeChamadoSimples {
	_DADO_UM_CLIENTE_PRE_CADASTRADO_LOGADO, _QUANDO_O_CLIENTE_CRIA_UM_NOVO_CHAMADO, _ENTAO_O_ATENDENTE_PRINCIPAL_DO_CLIENTE_E_NOTIFICADO_PELO_CHAT_INTERNO, _E_O_CLIENTE_E_NOTIFICADO_SOBRE_A_ABERTURA_PELO_EMAIL, _QUANDO_O_ATENDENTE_ASSUME_O_CHAMADO, _ENTAO_UMA_SALA_ENTRE_O_ATENDENTE_E_O_CLIENTE_E_CRIADO, _E_AS_MENSAGENS_ENVIADAS_NESTA_SALA_SAO_ENCAMINHADAS_PARA_O_WHATSAPP_DO_CLIENTE_QUE_ABRIU_O_CHAMADO, _DADO_UM_CHAMADO_ABERTO, _QUANDO_O_ATENDENTE_FECHA_O_CHAMADO, _ENTAO_O_CLIENTE_E_NOTIFICADO_VIA_EMAIL, _E_A_OS_USUARIOS_SAO_RETIRADOS_DA_SALA_DE_CHAMADO, _E_O_STATUSO_DO_CHAMADO_E_ALTERADO_PARA_FINALIZADO, _DADO_UM_CHAMADO_FECHADO, _QUANDO_O_CHAMADO_E_REABERTO, _E_O_ATENDIMENTO_DEFINE_UM_NOVO_RESPONSAVEL_PELO_CHAMADO, _ENTAO_O_RESPONSAVEL_E_NOTIFICADO, _E_O_RESPONSAVEL_E_ADICIONADO_NA_SALA_JUNTO_AO_ATENDENTE_PRINCIPAL;

	public static final String DADO_UM_CLIENTE_PRE_CADASTRADO_LOGADO = "Um cliente pré cadastrado logado";
	public static final String QUANDO_O_CLIENTE_CRIA_UM_NOVO_CHAMADO = "o Cliente cria um novo chamado";
	public static final String ENTAO_O_ATENDENTE_PRINCIPAL_DO_CLIENTE_E_NOTIFICADO_PELO_CHAT_INTERNO = "o atendente principal do cliente é notificado pelo chat interno";
	public static final String E_O_CLIENTE_E_NOTIFICADO_SOBRE_A_ABERTURA_PELO_EMAIL = "o cliente é notificado sobre a abertura pelo email";
	public static final String QUANDO_O_ATENDENTE_ASSUME_O_CHAMADO = "o atendente assume o chamado";
	public static final String ENTAO_UMA_SALA_ENTRE_O_ATENDENTE_E_O_CLIENTE_E_CRIADO = "uma sala entre o atendente e o cliente é criado";
	public static final String E_AS_MENSAGENS_ENVIADAS_NESTA_SALA_SAO_ENCAMINHADAS_PARA_O_WHATSAPP_DO_CLIENTE_QUE_ABRIU_O_CHAMADO = "as mensagens enviadas nesta sala são encaminhadas para o whatsapp do cliente que abriu o chamado";
	public static final String DADO_UM_CHAMADO_ABERTO = "um chamado aberto";
	public static final String QUANDO_O_ATENDENTE_FECHA_O_CHAMADO = "o atendente fecha o chamado";
	public static final String ENTAO_O_CLIENTE_E_NOTIFICADO_VIA_EMAIL = "o cliente é notificado via email";
	public static final String E_A_OS_USUARIOS_SAO_RETIRADOS_DA_SALA_DE_CHAMADO = "a os usuários são retirados da sala de chamado";
	public static final String E_O_STATUSO_DO_CHAMADO_E_ALTERADO_PARA_FINALIZADO = "o statuso do chamado é alterado para finalizado";
	public static final String DADO_UM_CHAMADO_FECHADO = "um chamado fechado";
	public static final String QUANDO_O_CHAMADO_E_REABERTO = "o chamado é reaberto";
	public static final String E_O_ATENDIMENTO_DEFINE_UM_NOVO_RESPONSAVEL_PELO_CHAMADO = "o atendimento define um novo responsável pelo chamado";
	public static final String ENTAO_O_RESPONSAVEL_E_NOTIFICADO = "O responsavel é notificado";
	public static final String E_O_RESPONSAVEL_E_ADICIONADO_NA_SALA_JUNTO_AO_ATENDENTE_PRINCIPAL = "o responsavel é adicionado na sala junto ao atendente principal";
}