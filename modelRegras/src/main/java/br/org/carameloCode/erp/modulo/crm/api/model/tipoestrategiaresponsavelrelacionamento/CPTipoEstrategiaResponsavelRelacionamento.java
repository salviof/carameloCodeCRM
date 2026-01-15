package br.org.carameloCode.erp.modulo.crm.api.model.tipoestrategiaresponsavelrelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.estrategiaResponsavelTipoRelacionamento.TipoEstrategiaResponsavelRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoEstrategiaResponsavelRelacionamento.class)
public enum CPTipoEstrategiaResponsavelRelacionamento {
	_ID, _NOME, _URL, _HEADER, _REGEXRESULTADOESPERADO, _DATAHORACRIOU, _DATAHORAEDITOU, _USUARIOCRIOU, _USUARIOEDITOU, _TIPOESTRATEGIA;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String url = "url";
	public static final String header = "header";
	public static final String regexresultadoesperado = "regexResultadoEsperado";
	public static final String datahoracriou = "dataHoraCriou";
	public static final String datahoraeditou = "dataHoraEditou";
	public static final String usuariocriou = "usuarioCriou";
	public static final String usuarioeditou = "usuarioEditou";
	public static final String tipoestrategia = "tipoEstrategia";
}