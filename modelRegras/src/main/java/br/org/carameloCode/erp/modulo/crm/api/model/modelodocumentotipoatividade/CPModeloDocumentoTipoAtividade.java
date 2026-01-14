package br.org.carameloCode.erp.modulo.crm.api.model.modelodocumentotipoatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoTipoAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ModeloDocumentoTipoAtividade.class)
public enum CPModeloDocumentoTipoAtividade {
	_TIPOATIVIDADEVINCULADA;

	public static final String tipoatividadevinculada = "tipoAtividadeVinculada";
}