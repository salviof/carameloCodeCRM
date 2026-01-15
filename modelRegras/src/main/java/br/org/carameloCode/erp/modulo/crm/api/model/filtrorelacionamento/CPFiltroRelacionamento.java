package br.org.carameloCode.erp.modulo.crm.api.model.filtrorelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.filtroTipoRelacionamento.FiltroRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = FiltroRelacionamento.class)
public enum CPFiltroRelacionamento {
	_ID, _NOME, _CONDICOES, _TIPORELACIONAMENTO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String condicoes = "condicoes";
	public static final String tiporelacionamento = "tipoRelacionamento";
}