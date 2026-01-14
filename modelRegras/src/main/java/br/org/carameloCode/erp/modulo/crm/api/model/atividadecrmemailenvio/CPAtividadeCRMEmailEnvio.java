package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmemailenvio;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais.AtividadeCRMEmailEnvio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCRMEmailEnvio.class)
public enum CPAtividadeCRMEmailEnvio {
	_CODIGOIMAP;

	public static final String codigoimap = "codigoImap";
}