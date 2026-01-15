package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmligacaorecebida;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.AtividadeCrmLigacaoRecebida;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCrmLigacaoRecebida.class)
public enum CPAtividadeCrmLigacaoRecebida {
	_CODIGOPABX;

	public static final String codigopabx = "codigoPABX";
}