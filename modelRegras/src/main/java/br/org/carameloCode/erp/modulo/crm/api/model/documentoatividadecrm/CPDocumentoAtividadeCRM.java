package br.org.carameloCode.erp.modulo.crm.api.model.documentoatividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.DocumentoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = DocumentoAtividadeCRM.class)
public enum CPDocumentoAtividadeCRM {
	_MODELODOCUMENTO, _ATIVIDADEGERADORA, _ENVIOS, _FOIENVIADO;

	public static final String modelodocumento = "modeloDocumento";
	public static final String atividadegeradora = "atividadeGeradora";
	public static final String envios = "envios";
	public static final String foienviado = "foiEnviado";
}