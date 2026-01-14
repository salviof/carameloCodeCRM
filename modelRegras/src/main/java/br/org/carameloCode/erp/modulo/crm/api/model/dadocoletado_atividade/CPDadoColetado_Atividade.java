package br.org.carameloCode.erp.modulo.crm.api.model.dadocoletado_atividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.DadoColetado_Atividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = DadoColetado_Atividade.class)
public enum CPDadoColetado_Atividade {
	_ID, _ATIVIDADE, _DADOCRM;

	public static final String id = "id";
	public static final String atividade = "atividade";
	public static final String dadocrm = "dadoCrm";
}