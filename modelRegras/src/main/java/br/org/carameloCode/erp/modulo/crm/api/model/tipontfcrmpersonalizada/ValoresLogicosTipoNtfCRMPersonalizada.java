package br.org.carameloCode.erp.modulo.crm.api.model.tipontfcrmpersonalizada;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TipoNtfCRMPersonalizada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoNtfCRMPersonalizada.class)
public enum ValoresLogicosTipoNtfCRMPersonalizada {
	NOME, ACAOGATILHONOTIFICACAO, ACAOAUTOEXECUCAOENVIO, ACAORESPOSTAPERSONALIZADA, ACAOAUTOEXECUCAOENTREGA, ACAOAUTOEXECUCAOLEITURA, ESTRUTURAENTIDADE, ACAOESGATILHODISPONIVEIS, ENTIDADESDISPONIVEIS
}