package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tipoexecucaoia;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.TipoExecucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoExecucaoIA.class)
public enum CPTipoExecucaoIA {
	_ID, _NOME, _DESCRICAO, _ICONE, _INSTRUCAOPRINCIPAL, _STRUCTUREDOUTPUTCONFIG, _OUTPUTCONFIGOPENIA, _OUTPUTCONFIGCPILOT, _OUTPUTCONFIGDEEPSEEK, _HABILITARPLACEHOLDERS;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String icone = "icone";
	public static final String instrucaoprincipal = "instrucaoPrincipal";
	public static final String structuredoutputconfig = "structuredOutputConfig";
	public static final String outputconfigopenia = "outputConfigOpenIA";
	public static final String outputconfigcpilot = "outputConfigCpilot";
	public static final String outputconfigdeepseek = "outputConfigDeepSeek";
	public static final String habilitarplaceholders = "habilitarPLaceHolders";
}