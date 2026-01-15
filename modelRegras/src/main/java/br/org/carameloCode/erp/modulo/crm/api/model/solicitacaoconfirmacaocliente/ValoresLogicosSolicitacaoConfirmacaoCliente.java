package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoConfirmacaoCliente.class)
public enum ValoresLogicosSolicitacaoConfirmacaoCliente {
	NOME, LINKCONVITE
}