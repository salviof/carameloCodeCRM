package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesquisamelhorhorario;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EscopoPesquisaMelhorHorario.class)
public enum CPEscopoPesquisaMelhorHorario {
	_ID, _NOME, _ATENDENTES, _ATENDENTESPOSSIVEIS, _TIPOSAGENDAMENTOSDISPONIVEIS, _TIPORESERVA, _TIPOCONTEXTO, _NUMEROOPCOES, _QTDMAXIMORESERVAS, _QTDRESERVASREALIZADAS, _QTDRESERVASDISPONIVEIS, _DATAINICIAL, _HORARIOINICIO, _HORARIOFINAL, _TIPOESCOPO, _DIASDASEMANA, _DATAHORAMAXIMOPESQUISA, _ATIVO, _TOKENDEACESSO, _DIASEMANASEGUNDA, _DIASEMANATERCA, _DIASEMANAQUARTA, _DIASEMANAQUINTA, _DIASEMANASEXTA, _DIASEMANASABADO, _DIASEMANADOMINGO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String atendentes = "atendentes";
	public static final String atendentespossiveis = "atendentesPossiveis";
	public static final String tiposagendamentosdisponiveis = "tiposAgendamentosDisponiveis";
	public static final String tiporeserva = "tipoReserva";
	public static final String tipocontexto = "tipoContexto";
	public static final String numeroopcoes = "numeroOpcoes";
	public static final String qtdmaximoreservas = "qtdMaximoReservas";
	public static final String qtdreservasrealizadas = "qtdReservasRealizadas";
	public static final String qtdreservasdisponiveis = "qtdReservasDisponiveis";
	public static final String datainicial = "dataInicial";
	public static final String horarioinicio = "horarioinicio";
	public static final String horariofinal = "horarioFinal";
	public static final String tipoescopo = "tipoEscopo";
	public static final String diasdasemana = "diasDaSemana";
	public static final String datahoramaximopesquisa = "datahoraMaximoPesquisa";
	public static final String ativo = "ativo";
	public static final String tokendeacesso = "tokenDeAcesso";
	public static final String diasemanasegunda = "diaSemanaSegunda";
	public static final String diasemanaterca = "diaSemanaTerca";
	public static final String diasemanaquarta = "diaSemanaQuarta";
	public static final String diasemanaquinta = "diaSemanaQuinta";
	public static final String diasemanasexta = "diaSemanaSexta";
	public static final String diasemanasabado = "diaSemanaSabado";
	public static final String diasemanadomingo = "diaSemanaDomingo";
}