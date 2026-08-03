package br.org.carameloCode.erp.modulo.crm.api.model.solicitarqatualizacaoeqp;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitArqAtualizacaoEqp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitArqAtualizacaoEqp.class)
public enum ValoresLogicosSolicitArqAtualizacaoEqp {
	NOME, STATUS, LINKCONVITE, EMATRASO, NOTIFICACAO
}