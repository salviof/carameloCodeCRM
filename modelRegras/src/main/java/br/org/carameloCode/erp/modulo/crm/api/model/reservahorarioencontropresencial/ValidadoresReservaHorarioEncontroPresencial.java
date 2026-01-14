package br.org.carameloCode.erp.modulo.crm.api.model.reservahorarioencontropresencial;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraPresencial;

@InfoReferenciaEntidade(tipoObjeto = ReservaHoraPresencial.class)
public enum ValidadoresReservaHorarioEncontroPresencial {
	ATENDIMENTOOUTSIDE, PESSOARELACIONADA
}