package br.org.carameloCode.erp.modulo.crm.api.model.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = Solicitacao.class)
public enum ValoresLogicosSolicitacao {
	NOME, LINKCONVITE
}