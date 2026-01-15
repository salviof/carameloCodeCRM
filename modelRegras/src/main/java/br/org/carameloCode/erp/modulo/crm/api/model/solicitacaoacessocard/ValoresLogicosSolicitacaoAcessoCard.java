package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoacessocard;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAcessoCard;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoAcessoCard.class)
public enum ValoresLogicosSolicitacaoAcessoCard {
	NOME, LINKCONVITE
}