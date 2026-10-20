package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoia;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ExecucaoIA.class)
public enum ValoresLogicosExecucaoIA {
	PERSONA, ENTIDADEVINCULADA, DIALOGOJSON, SYSTEMPROMPTMD, VALORGASTO
}