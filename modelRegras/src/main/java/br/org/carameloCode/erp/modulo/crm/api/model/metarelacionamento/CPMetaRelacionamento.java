package br.org.carameloCode.erp.modulo.crm.api.model.metarelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.etapaFunil.MetaRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = MetaRelacionamento.class)
public enum CPMetaRelacionamento {
	_ID, _NOME, _DESCRICAOETAPAVISAOPRESTADOR, _DESCRICAOETAPAVISAOCLIENTE, _PESO, _QTDEMPRESASNESTAMETA, _COR, _CLIENTE, _TIPOSRELACIONAMENTO, _TIPOSATIVIDADEDISPONIVEIS, _TIPOSATIVIDADEUPGRADE, _TIPOSATIVIDADEDOWGRADE, _TIPOSATIVIDADEGRUPOATIVIDADE, _METACONVERSAO, _FUNIL;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricaoetapavisaoprestador = "descricaoEtapaVisaoPrestador";
	public static final String descricaoetapavisaocliente = "descricaoEtapaVisaoCliente";
	public static final String peso = "peso";
	public static final String qtdempresasnestameta = "qtdEmpresasNestaMeta";
	public static final String cor = "cor";
	public static final String cliente = "cliente";
	public static final String tiposrelacionamento = "tiposRelacionamento";
	public static final String tiposatividadedisponiveis = "tiposAtividadeDisponiveis";
	public static final String tiposatividadeupgrade = "tiposAtividadeUpGrade";
	public static final String tiposatividadedowgrade = "tiposAtividadeDowGrade";
	public static final String tiposatividadegrupoatividade = "tiposAtividadeGrupoAtividade";
	public static final String metaconversao = "metaConversao";
	public static final String funil = "funil";
}