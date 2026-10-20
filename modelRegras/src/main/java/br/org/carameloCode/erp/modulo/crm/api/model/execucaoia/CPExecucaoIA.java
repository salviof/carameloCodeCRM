package br.org.carameloCode.erp.modulo.crm.api.model.execucaoia;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ExecucaoIA.class)
public enum CPExecucaoIA {
	_ID, _TIPOENTIDADE, _DATAHORA, _USUARIO, _TIPOEXECUCAO, _PERSONA, _ENTIDADEALVONOME, _ENTIDADEALVOCODIGO, _ENTIDADEVINCULADA, _INSTRUCOES, _NOVAMENSAGEM, _NOVARESPOSTA, _DIALOGOJSON, _SYSTEMPROMPTMD, _TOKENSINPUT, _TOKENSOUTPUT, _VALORGASTO, _HASHULTIMAEXECUCAOSUCESSO;

	public static final String id = "id";
	public static final String tipoentidade = "tipoEntidade";
	public static final String datahora = "dataHora";
	public static final String usuario = "usuario";
	public static final String tipoexecucao = "tipoExecucao";
	public static final String persona = "persona";
	public static final String entidadealvonome = "entidadeAlvoNome";
	public static final String entidadealvocodigo = "entidadeAlvoCodigo";
	public static final String entidadevinculada = "entidadeVinculada";
	public static final String instrucoes = "instrucoes";
	public static final String novamensagem = "novaMensagem";
	public static final String novaresposta = "novaResposta";
	public static final String dialogojson = "dialogoJson";
	public static final String systempromptmd = "systemPromptMD";
	public static final String tokensinput = "tokensInput";
	public static final String tokensoutput = "tokensOutput";
	public static final String valorgasto = "valorGasto";
	public static final String hashultimaexecucaosucesso = "hashUltimaExecucaoSucesso";
}