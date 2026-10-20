package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiaatividade;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ExecucaoIAAtividade.class)
public enum ValoresLogicosExecucaoIAAtividade {
	PERSONA, ENTIDADEVINCULADA, DIALOGOJSON, SYSTEMPROMPTMD, VALORGASTO
}