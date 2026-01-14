package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.fluxotypebotformulario;
public enum EtapasFluxoTypebotFormulario {
	_DADO_O_SERVICO_DE_TYPEBOT_CONFIGURADO, _QUANDO_O_SISTEMA_REQUISITAR_A_LISTA_DE_FORMULARIOS, _ENTAO_A_API_DEVE_RETORNAR_OS_FORMULARIOS_EXISTENTES, _E_O_SISTEMA_DEVE_SALVAR_CADA_FORMULARIO_NO_REPOSITORIO_JPA, _E_OS_DADOS_DA_PESSOA_DEVEM_TER_SIDO_ATUALIZADOS;

	public static final String DADO_O_SERVICO_DE_TYPEBOT_CONFIGURADO = "o servico de typebot configurado";
	public static final String QUANDO_O_SISTEMA_REQUISITAR_A_LISTA_DE_FORMULARIOS = "o sistema requisitar a lista de formulários";
	public static final String ENTAO_A_API_DEVE_RETORNAR_OS_FORMULARIOS_EXISTENTES = "a API deve retornar os formulários existentes";
	public static final String E_O_SISTEMA_DEVE_SALVAR_CADA_FORMULARIO_NO_REPOSITORIO_JPA = "o sistema deve salvar cada formulário no repositório JPA";
	public static final String E_OS_DADOS_DA_PESSOA_DEVEM_TER_SIDO_ATUALIZADOS = "os dados da pessoa devem ter sido atualizados";
}