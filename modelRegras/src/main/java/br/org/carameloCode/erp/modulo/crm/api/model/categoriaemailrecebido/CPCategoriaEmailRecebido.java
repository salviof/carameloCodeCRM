package br.org.carameloCode.erp.modulo.crm.api.model.categoriaemailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.categoriaMailRecebido.CategoriaEmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = CategoriaEmailRecebido.class)
public enum CPCategoriaEmailRecebido {
	_ID, _DESCRICAO, _CATEGORIAFAB;

	public static final String id = "id";
	public static final String descricao = "descricao";
	public static final String categoriafab = "categoriaFab";
}