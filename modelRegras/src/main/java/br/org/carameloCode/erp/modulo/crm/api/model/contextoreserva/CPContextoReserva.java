package br.org.carameloCode.erp.modulo.crm.api.model.contextoreserva;

import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ContextoReserva;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ContextoReserva.class)
public enum CPContextoReserva {
	_ID, _NOME, _DESCRICAO, _FATORDEOCUPACAOAGENDA, _FABRICA;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String fatordeocupacaoagenda = "fatorDeOcupacaoAgenda";
	public static final String fabrica = "fabrica";
}