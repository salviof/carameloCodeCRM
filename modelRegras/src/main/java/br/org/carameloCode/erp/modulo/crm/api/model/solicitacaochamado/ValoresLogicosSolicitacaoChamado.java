package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaochamado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoChamado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoChamado.class)
public enum ValoresLogicosSolicitacaoChamado {
	NOME, LINKCONVITE
}