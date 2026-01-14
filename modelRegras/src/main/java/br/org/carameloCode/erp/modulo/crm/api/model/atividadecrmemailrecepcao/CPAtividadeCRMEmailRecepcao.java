package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmemailrecepcao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais.AtividadeCRMEmailRecepcao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCRMEmailRecepcao.class)
public enum CPAtividadeCRMEmailRecepcao {
	_CODIGOIMAP;

	public static final String codigoimap = "codigoImap";
}