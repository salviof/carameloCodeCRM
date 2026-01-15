package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadeequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAtividadeEquipe;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoAtividadeEquipe.class)
public enum CPSolicitacaoAtividadeEquipe {
	_TIPOATIVIDADE;

	public static final String tipoatividade = "tipoAtividade";
}