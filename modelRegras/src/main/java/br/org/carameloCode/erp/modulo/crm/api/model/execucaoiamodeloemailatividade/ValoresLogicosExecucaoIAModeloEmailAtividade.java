package br.org.carameloCode.erp.modulo.crm.api.model.execucaoiamodeloemailatividade;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ExecucaoIAModeloEmailAtividade.class)
public enum ValoresLogicosExecucaoIAModeloEmailAtividade {
	ENTIDADEVINCULADA, DIALOGOJSON, SYSTEMPROMPTMD, VALORGASTO
}