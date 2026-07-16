package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaoequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoEquipe;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoConfirmacaoEquipe.class)
public enum CPSolicitacaoConfirmacaoEquipe {
	_DESCRICAOCONFIRMACAO, _FABTIPOCOMUNICACAO, _TIPORESPOSTASELECIONADA, _RESPOSTASPOSSIVEIS;

	public static final String descricaoconfirmacao = "descricaoConfirmacao";
	public static final String fabtipocomunicacao = "fabTipoComunicacao";
	public static final String tiporespostaselecionada = "tipoRespostaSelecionada";
	public static final String respostaspossiveis = "respostasPossiveis";
}