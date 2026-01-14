package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoorcamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoOrcamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoOrcamento.class)
public enum CPSolicitacaoOrcamento {
	_ORCAMENTO;

	public static final String orcamento = "orcamento";
}