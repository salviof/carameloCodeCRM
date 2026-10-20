package org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing;
public enum EtapasIAModeloEmailMarketing {
	_DADO_UM_ATENDENTE_AUTENTICADO_NO_SISTEMA, _QUANDO_O_ATENDENTE_CRIA_UMA_NOVA_PERSONA, _ENTAO_A_PERSONA_APARECE_NA_LISTA_DE_PERSONAS_PARA_NOVOS_MODELOS_DE_EMAIL, _DADO_UMA_PERSONA_CADASTRADA, _E_O_ATENDENTE_SELECIONA_A_PERSONA_EM_UM_NOVO_MODELO_DE_EMAIL, _QUANDO_O_ATENDENTE_SALVA_O_MODELO, _ENTAO_O_MODELO_FICA_REGISTRADO_NO_SISTEMA, _E_O_MODELO_E_ENVIADO_PARA_O_MAUTIC, _DADO_UM_MODELO_DE_EMAIL_SALVO_COM_UMA_PERSONA_ASSOCIADA, _E_O_ATENDENTE_ATIVA_O_ASSISTENTE_DE_IA_NO_MODELO, _QUANDO_O_ATENDENTE_ENVIA_INSTRUCOES_DE_ATUALIZACAO_AO_ASSISTENTE, _ENTAO_O_ASSISTENTE_GERA_UM_NOVO_CONTEUDO_COERENTE_COM_A_PERSONA, _E_O_NOVO_CONTEUDO_E_ENVIADO_PARA_O_MAUTIC;

	public static final String DADO_UM_ATENDENTE_AUTENTICADO_NO_SISTEMA = "um atendente autenticado no sistema";
	public static final String QUANDO_O_ATENDENTE_CRIA_UMA_NOVA_PERSONA = "o atendente cria uma nova persona";
	public static final String ENTAO_A_PERSONA_APARECE_NA_LISTA_DE_PERSONAS_PARA_NOVOS_MODELOS_DE_EMAIL = "a persona aparece na lista de personas para novos modelos de email";
	public static final String DADO_UMA_PERSONA_CADASTRADA = "uma persona cadastrada";
	public static final String E_O_ATENDENTE_SELECIONA_A_PERSONA_EM_UM_NOVO_MODELO_DE_EMAIL = "o atendente seleciona a persona em um novo modelo de email";
	public static final String QUANDO_O_ATENDENTE_SALVA_O_MODELO = "o atendente salva o modelo";
	public static final String ENTAO_O_MODELO_FICA_REGISTRADO_NO_SISTEMA = "o modelo fica registrado no sistema";
	public static final String E_O_MODELO_E_ENVIADO_PARA_O_MAUTIC = "o modelo e enviado para o Mautic";
	public static final String DADO_UM_MODELO_DE_EMAIL_SALVO_COM_UMA_PERSONA_ASSOCIADA = "um modelo de email salvo com uma persona associada";
	public static final String E_O_ATENDENTE_ATIVA_O_ASSISTENTE_DE_IA_NO_MODELO = "o atendente ativa o assistente de IA no modelo";
	public static final String QUANDO_O_ATENDENTE_ENVIA_INSTRUCOES_DE_ATUALIZACAO_AO_ASSISTENTE = "o atendente envia instrucoes de atualizacao ao assistente";
	public static final String ENTAO_O_ASSISTENTE_GERA_UM_NOVO_CONTEUDO_COERENTE_COM_A_PERSONA = "o assistente gera um novo conteudo coerente com a persona";
	public static final String E_O_NOVO_CONTEUDO_E_ENVIADO_PARA_O_MAUTIC = "o novo conteudo e enviado para o Mautic";
}