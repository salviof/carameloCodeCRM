package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmlicacaorealizada;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.AtividadeCrmLigacaoRealizada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCrmLigacaoRealizada.class)
public enum CPAtividadeCrmLicacaoRealizada {
	_CODIGOPABX;

	public static final String codigopabx = "codigoPABX";
}