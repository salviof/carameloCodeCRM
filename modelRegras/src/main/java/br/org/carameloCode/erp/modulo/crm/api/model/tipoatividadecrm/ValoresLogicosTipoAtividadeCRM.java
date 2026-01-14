package br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoAtividadeCRM.class)
public enum ValoresLogicosTipoAtividadeCRM {
	XHTMLALTERNATIVO, ACAOFORMULARIOEXECUCAO, ATIVIDADEABERTAPELOUSUARIOLOGADO, ACOESDEPLUGINSDISPONIVEIS
}