package br.org.carameloCode.erp.modulo.crm.api.model.resultadotiporelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.zonaRelacionamento.ResultadoTipoRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ResultadoTipoRelacionamento.class)
public enum CPResultadoTipoRelacionamento {
	_ID, _NOME, _CORRESULTADO, _CLASSERESULTADO, _FABRICARESULTADO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String corresultado = "corResultado";
	public static final String classeresultado = "classeResultado";
	public static final String fabricaresultado = "fabricaResultado";
}