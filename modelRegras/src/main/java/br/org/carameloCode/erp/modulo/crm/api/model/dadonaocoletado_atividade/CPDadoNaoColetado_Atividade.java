package br.org.carameloCode.erp.modulo.crm.api.model.dadonaocoletado_atividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.DadoNaoColetado_Atividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = DadoNaoColetado_Atividade.class)
public enum CPDadoNaoColetado_Atividade {
	_ID, _ATIVIDADE, _DADOCRM;

	public static final String id = "id";
	public static final String atividade = "atividade";
	public static final String dadocrm = "dadoCRM";
}