package br.org.carameloCode.erp.modulo.crm.api.model.tipocomponentvisual;

import com.super_bits.telas.compomente.TipoComponentVisual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoComponentVisual.class)
public enum CPTipoComponentVisual {
	_ID, _PARAMETROS, _NOMECOMPONENTE, _CODIGOFONTE, _JAVASCRIPT, _CSS;

	public static final String id = "id";
	public static final String parametros = "parametros";
	public static final String nomecomponente = "nomeComponente";
	public static final String codigofonte = "codigoFonte";
	public static final String javascript = "javaScript";
	public static final String css = "css";
}