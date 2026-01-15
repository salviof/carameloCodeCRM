package br.org.carameloCode.erp.modulo.crm.api.model.dadocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = DadoCRM.class)
public enum CPDadoCRM {
	_ID, _NOME, _VALOR, _DATAHORA, _PROSPECTOEMPRESA, _ATIVIDADECRM, _CAMPOVALORREFLECTION, _TIPODADOCRM, _CAMPOCRMINSTANCIADO, _TIPORELACIONAMENTOVINCULADO, _USUARIOCADASTROU, _USUARIOEDITOU, _DATAHORACADASTROU, _DATAHORAEDITOU, _DOCUMENTOPESSOA, _INFORMACAOVALIDADA;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String valor = "valor";
	public static final String datahora = "datahora";
	public static final String prospectoempresa = "prospectoEmpresa";
	public static final String atividadecrm = "atividadeCRM";
	public static final String campovalorreflection = "campoValorReflection";
	public static final String tipodadocrm = "tipoDadoCRM";
	public static final String campocrminstanciado = "campoCRMInstanciado";
	public static final String tiporelacionamentovinculado = "tipoRelacionamentoVinculado";
	public static final String usuariocadastrou = "usuarioCadastrou";
	public static final String usuarioeditou = "usuarioEditou";
	public static final String datahoracadastrou = "dataHoraCadastrou";
	public static final String datahoraeditou = "dataHoraEditou";
	public static final String documentopessoa = "documentoPessoa";
	public static final String informacaovalidada = "informacaoValidada";
}