package br.org.carameloCode.erp.modulo.crm.api.model.metarelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.etapaFunil.MetaRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = MetaRelacionamento.class)
public enum ValoresLogicosMetaRelacionamento {
	TIPOSATIVIDADEUPGRADE, TIPOSATIVIDADEDOWGRADE, TIPOSATIVIDADEGRUPOATIVIDADE, FUNIL
}