package br.org.carameloCode.erp.modulo.crm.api.model.tipocondicaofiltrorelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.filtroTipoRelacionamento.TipoCondicaoFiltroRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoCondicaoFiltroRelacionamento.class)
public enum CPTipoCondicaoFiltroRelacionamento {
	_ID, _NOME, _ICONE, _TIPOCONDICAO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String icone = "icone";
	public static final String tipocondicao = "tipoCondicao";
}