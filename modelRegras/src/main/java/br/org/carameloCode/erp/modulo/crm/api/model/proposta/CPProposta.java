package br.org.carameloCode.erp.modulo.crm.api.model.proposta;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.contrato.Proposta;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = Proposta.class)
public enum CPProposta {
	_ID, _NOME, _DESCRICAO, _PROSPECTO, _ATIVIDADE;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String prospecto = "prospecto";
	public static final String atividade = "atividade";
}