package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoConfirmacaoCliente.class)
public enum CPSolicitacaoConfirmacaoCliente {
	_CONTATOPESSOA, _DESCRICAOCONFIRMACAO, _FABTIPOCOMUNICACAO, _TIPORESPOSTASELECIONADA, _RESPOSTASPOSSIVEIS;

	public static final String contatopessoa = "contatoPessoa";
	public static final String descricaoconfirmacao = "descricaoConfirmacao";
	public static final String fabtipocomunicacao = "fabTipoComunicacao";
	public static final String tiporespostaselecionada = "tipoRespostaSelecionada";
	public static final String respostaspossiveis = "respostasPossiveis";
}