package br.org.carameloCode.erp.modulo.crm.api.model.tipomensagemmktwhatsapp;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoMensagemMktWhatsApp.class)
public enum CPTipoMensagemMktWhatsApp {
	_ID, _NOME, _TELEFONE, _SLUGTEMPLATE, _ENVIOUNICO, _PARAMETROS, _URLMENSAGEMPREVIEW;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String telefone = "telefone";
	public static final String slugtemplate = "slugTemplate";
	public static final String enviounico = "envioUnico";
	public static final String parametros = "parametros";
	public static final String urlmensagempreview = "urlMensagemPreview";
}