package br.org.carameloCode.erp.modulo.crm.api.model.documentoapresentacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.apresentacao.DocumentoApresentacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = DocumentoApresentacao.class)
public enum CPDocumentoApresentacao {
	_TIPOSSERVICOVINCULADO;

	public static final String tiposservicovinculado = "tiposServicoVinculado";
}