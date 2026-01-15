package br.org.carameloCode.erp.modulo.crm.api.model.tagatendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento.TagAtendimento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TagAtendimento.class)
public enum CPTagAtendimento {
	_ID, _NOME, _COR, _DESCRICAO, _CRIADOEM, _EDITADOEM, _CRIADOPOR, _EDITADOPOR;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String cor = "cor";
	public static final String descricao = "descricao";
	public static final String criadoem = "criadoEm";
	public static final String editadoem = "editadoEm";
	public static final String criadopor = "criadoPor";
	public static final String editadopor = "editadoPor";
}