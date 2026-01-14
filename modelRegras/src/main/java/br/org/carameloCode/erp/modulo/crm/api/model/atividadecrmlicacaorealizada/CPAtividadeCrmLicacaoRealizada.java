package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmlicacaorealizada;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais.AtividadeCrmLicacaoRealizada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCrmLicacaoRealizada.class)
public enum CPAtividadeCrmLicacaoRealizada {
	_CODIGOPABX;

	public static final String codigopabx = "codigoPABX";
}