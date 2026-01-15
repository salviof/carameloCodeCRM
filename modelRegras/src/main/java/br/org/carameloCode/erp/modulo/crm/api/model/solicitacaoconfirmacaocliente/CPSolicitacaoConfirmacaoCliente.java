package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoConfirmacaoCliente.class)
public enum CPSolicitacaoConfirmacaoCliente {
	_DESCRICAOCONFIRMACAO, _CONTATOPESSOA;

	public static final String descricaoconfirmacao = "descricaoConfirmacao";
	public static final String contatopessoa = "contatoPessoa";
}