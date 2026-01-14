package br.org.carameloCode.erp.modulo.crm.api.model.origemprospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = OrigemProspecto.class)
public enum CPOrigemProspecto {
	_ID, _NOME, _DESCRICAO, _RELACIONAMENTOPADRAO, _PROSPECTOS, _TIPOORIGEM, _UMAORIGEMPRIVADA, _UMAORIGEMPUBLICA, _QUANTIDADELEADS, _QUANTIDADEMEUSLEADS, _QTDLEADSATIVOS, _QTDLEADSINATIVOS, _USUARIOSCOMACESSO, _ATIVO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String relacionamentopadrao = "relacionamentoPadrao";
	public static final String prospectos = "prospectos";
	public static final String tipoorigem = "tipoOrigem";
	public static final String umaorigemprivada = "umaOrigemPrivada";
	public static final String umaorigempublica = "umaOrigempublica";
	public static final String quantidadeleads = "quantidadeLeads";
	public static final String quantidademeusleads = "quantidadeMeusLeads";
	public static final String qtdleadsativos = "qtdLeadsAtivos";
	public static final String qtdleadsinativos = "qtdLeadsInativos";
	public static final String usuarioscomacesso = "usuariosComAcesso";
	public static final String ativo = "ativo";
}