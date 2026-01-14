package br.org.carameloCode.erp.modulo.crm.api.model.statusreserva;

import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.StatusReserva;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = StatusReserva.class)
public enum CPStatusReserva {
	_ID, _NOME, _TIPORESERVA;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String tiporeserva = "tipoReserva";
}