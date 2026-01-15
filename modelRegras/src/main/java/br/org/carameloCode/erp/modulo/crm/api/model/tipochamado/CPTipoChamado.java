package br.org.carameloCode.erp.modulo.crm.api.model.tipochamado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.TipoChamado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoChamado.class)
public enum CPTipoChamado {
	_ID, _NOME, _DESCRICAO, _RESPONSAVEIS, _RESPONSAVEISDISPONIVEIS, _ATIVO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String responsaveis = "responsaveis";
	public static final String responsaveisdisponiveis = "responsaveisDisponiveis";
	public static final String ativo = "ativo";
}