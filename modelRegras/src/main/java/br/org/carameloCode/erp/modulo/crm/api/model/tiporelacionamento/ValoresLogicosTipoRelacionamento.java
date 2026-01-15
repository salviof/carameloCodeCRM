package br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoRelacionamento.class)
public enum ValoresLogicosTipoRelacionamento {
	QTDEMPRESASNESTETIPORELACIONAMENTO, PESSOASCOMESTERELACIONAMENTO, ATIVIDADESDOWNGRADEMETA, ATIVIDADESUPGRADEMETA, PROXIMOSRESPONSAVEIS
}