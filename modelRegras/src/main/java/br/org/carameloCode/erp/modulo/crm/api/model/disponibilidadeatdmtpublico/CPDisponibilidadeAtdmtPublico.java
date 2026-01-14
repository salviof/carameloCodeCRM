package br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico;

import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = DisponibilidadeAtdmtPublico.class)
public enum CPDisponibilidadeAtdmtPublico {
	_ID, _DIASDASEMANA, _HORARIOINICIO, _HORARIOFIM, _NOME, _DIASEMANASEGUNDA, _DIASEMANATERCA, _DIASEMANAQUARTA, _DIASEMANAQUINTA, _DIASEMANASEXTA, _DIASEMANASABADO, _DIASEMANADOMINGO, _DIAINICIAL, _DIAFINAL, _TIPODISPONIBILIDADE, _LOCAL, _TIPOSAGENDAMENTOSPUBLICOS, _ATENDENTESDISPONIVEIS, _LISTADEATENDENTESPOSSIVEIS, _USUARIORESPONSAVEL, _DISTANCIADOFUTUROPERMITIDOEMHORAS, _RESERVARLOCAL, _RESERVARATENDENTE, _ATENDIMENTOREMOTO, _IGNORARFERIADOS;

	public static final String id = "id";
	public static final String diasdasemana = "diasDaSemana";
	public static final String horarioinicio = "horarioInicio";
	public static final String horariofim = "horarioFim";
	public static final String nome = "nome";
	public static final String diasemanasegunda = "diaSemanaSegunda";
	public static final String diasemanaterca = "diaSemanaTerca";
	public static final String diasemanaquarta = "diaSemanaQuarta";
	public static final String diasemanaquinta = "diaSemanaQuinta";
	public static final String diasemanasexta = "diaSemanaSexta";
	public static final String diasemanasabado = "diaSemanaSabado";
	public static final String diasemanadomingo = "diaSemanaDomingo";
	public static final String diainicial = "diaInicial";
	public static final String diafinal = "diaFinal";
	public static final String tipodisponibilidade = "tipoDisponibilidade";
	public static final String local = "local";
	public static final String tiposagendamentospublicos = "tiposAgendamentosPublicos";
	public static final String atendentesdisponiveis = "atendentesDisponiveis";
	public static final String listadeatendentespossiveis = "listaDeAtendentesPossiveis";
	public static final String usuarioresponsavel = "usuarioResponsavel";
	public static final String distanciadofuturopermitidoemhoras = "distanciaDoFuturoPermitidoEmHoras";
	public static final String reservarlocal = "reservarLocal";
	public static final String reservaratendente = "reservarAtendente";
	public static final String atendimentoremoto = "atendimentoRemoto";
	public static final String ignorarferiados = "ignorarFeriados";
}