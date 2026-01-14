package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoArquivoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoArquivoCliente.class)
public enum ValoresLogicosSolicitacaoArquivoCliente {
	NOME, LINKCONVITE
}