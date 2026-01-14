package br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario;

import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EscopoPesquisaMelhorHorario.class)
public enum CPEscopoPesquisaMelhorHorario {
	_ID, _NOME, _ATENDENTES, _ATENDENTESPOSSIVEIS, _TIPOAGENDAMENTO, _TIPOSAGENDAMENTOSDISPONIVEIS, _CONTEXTO, _NUMEROOPCOES, _QTDMAXIMORESERVAS, _QTDRESERVASREALIZADAS, _QTDRESERVASDISPONIVEIS, _DATAINICIAL, _HORARIOINICIO, _HORARIOFINAL, _TIPOESCOPO, _DIASDASEMANA, _LISTAHORARIOSDISPONIVEIS, _DISPONIBILIDADESDOESCOPO, _DATAHORAMAXIMOPESQUISA, _ATIVO, _TOKENDEACESSO, _DIASEMANASEGUNDA, _DIASEMANATERCA, _DIASEMANAQUARTA, _DIASEMANAQUINTA, _DIASEMANASEXTA, _DIASEMANASABADO, _DIASEMANADOMINGO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String atendentes = "atendentes";
	public static final String atendentespossiveis = "atendentesPossiveis";
	public static final String tipoagendamento = "tipoAgendamento";
	public static final String tiposagendamentosdisponiveis = "tiposAgendamentosDisponiveis";
	public static final String contexto = "contexto";
	public static final String numeroopcoes = "numeroOpcoes";
	public static final String qtdmaximoreservas = "qtdMaximoReservas";
	public static final String qtdreservasrealizadas = "qtdReservasRealizadas";
	public static final String qtdreservasdisponiveis = "qtdReservasDisponiveis";
	public static final String datainicial = "dataInicial";
	public static final String horarioinicio = "horarioinicio";
	public static final String horariofinal = "horarioFinal";
	public static final String tipoescopo = "tipoEscopo";
	public static final String diasdasemana = "diasDaSemana";
	public static final String listahorariosdisponiveis = "listaHorariosDisponiveis";
	public static final String disponibilidadesdoescopo = "disponibilidadesDoEscopo";
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