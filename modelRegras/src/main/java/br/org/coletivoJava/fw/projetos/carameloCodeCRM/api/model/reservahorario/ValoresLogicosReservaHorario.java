package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.reservahorario;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ReservaHorario.class)
public enum ValoresLogicosReservaHorario {
	DESCRICAO, VALORRESERVA, CODIGORESERVA, DISPONIVELPARACONFIRMACAO
}