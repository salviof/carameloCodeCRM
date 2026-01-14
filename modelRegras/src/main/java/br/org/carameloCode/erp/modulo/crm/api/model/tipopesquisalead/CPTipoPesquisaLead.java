package br.org.carameloCode.erp.modulo.crm.api.model.tipopesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.TipoPesquisaLead;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoPesquisaLead.class)
public enum CPTipoPesquisaLead {
	_ID, _NOME, _ICONE, _DESCRITIVO, _TIPOPESQUISA;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String icone = "icone";
	public static final String descritivo = "descritivo";
	public static final String tipopesquisa = "tipoPesquisa";
}