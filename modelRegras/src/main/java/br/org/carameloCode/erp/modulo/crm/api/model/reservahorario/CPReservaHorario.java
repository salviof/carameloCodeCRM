package br.org.carameloCode.erp.modulo.crm.api.model.reservahorario;

import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ReservaHorario.class)
public enum CPReservaHorario {
	_ID, _DESCRICAO, _INICIORESERVAATENDENTE, _FINALRESERVAATENDENTE, _ESCOPOORIGEM, _VALORRESERVA, _TIPORESERVAHORARIO, _CODIGORESERVA, _STATUS, _ATENDIDORESPONSAVEL, _PESSOARELACIONADA, _CONTATOSATENDIDOS, _ATENDIDOS, _ATENDENTES, _ATENDENTERESPONSAVEL, _TIPOAGENDAMENTO, _DISPONIVELPARACONFIRMACAO, _ATIVO, _DADOSCONTATOACESSOANONIMO;

	public static final String id = "id";
	public static final String descricao = "descricao";
	public static final String inicioreservaatendente = "inicioReservaAtendente";
	public static final String finalreservaatendente = "finalReservaAtendente";
	public static final String escopoorigem = "escopoOrigem";
	public static final String valorreserva = "valorReserva";
	public static final String tiporeservahorario = "tipoReservaHorario";
	public static final String codigoreserva = "codigoReserva";
	public static final String status = "status";
	public static final String atendidoresponsavel = "atendidoResponsavel";
	public static final String pessoarelacionada = "pessoaRelacionada";
	public static final String contatosatendidos = "contatosAtendidos";
	public static final String atendidos = "atendidos";
	public static final String atendentes = "atendentes";
	public static final String atendenteresponsavel = "atendenteResponsavel";
	public static final String tipoagendamento = "tipoAgendamento";
	public static final String disponivelparaconfirmacao = "disponivelParaConfirmacao";
	public static final String ativo = "ativo";
	public static final String dadoscontatoacessoanonimo = "dadosContatoAcessoAnonimo";
}