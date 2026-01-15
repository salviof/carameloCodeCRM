package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadecliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAtividadeCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoAtividadeCliente.class)
public enum CPSolicitacaoAtividadeCliente {
	_CONTATOPESSOA;

	public static final String contatopessoa = "contatoPessoa";
}