package br.org.carameloCode.erp.modulo.crm.api.model.modeloemailmarketing;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ModeloEmailMarketing.class)
public enum CPModeloEmailMarketing {
	_CODIGOMAUTIC, _INSTRUCOESIA;

	public static final String codigomautic = "codigoMautic";
	public static final String instrucoesia = "instrucoesIA";
}