package br.org.carameloCode.erp.modulo.crm.api.model.execucaoiaatividade;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ExecucaoIAAtividade.class)
public enum CPExecucaoIAAtividade {
	_ATIVIDADE;

	public static final String atividade = "atividade";
}