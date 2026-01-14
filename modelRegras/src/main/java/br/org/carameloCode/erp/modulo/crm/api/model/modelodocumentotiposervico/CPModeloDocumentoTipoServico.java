package br.org.carameloCode.erp.modulo.crm.api.model.modelodocumentotiposervico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoTipoServico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ModeloDocumentoTipoServico.class)
public enum CPModeloDocumentoTipoServico {
	_TIPOSERVICO;

	public static final String tiposervico = "tipoServico";
}