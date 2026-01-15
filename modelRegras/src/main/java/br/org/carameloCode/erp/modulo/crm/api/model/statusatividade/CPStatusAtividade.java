package br.org.carameloCode.erp.modulo.crm.api.model.statusatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.StatusAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = StatusAtividade.class)
public enum CPStatusAtividade {
	_ID, _NOME, _DESCRICAO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
}