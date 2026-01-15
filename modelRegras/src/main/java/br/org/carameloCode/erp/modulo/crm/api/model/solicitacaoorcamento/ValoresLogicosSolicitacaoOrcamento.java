package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoorcamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoOrcamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoOrcamento.class)
public enum ValoresLogicosSolicitacaoOrcamento {
	NOME, LINKCONVITE
}