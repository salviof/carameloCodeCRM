package br.org.carameloCode.erp.modulo.crm.api.model.reservahorariovideoconferencia;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraRemotoVideo;

@InfoReferenciaEntidade(tipoObjeto = ReservaHoraRemotoVideo.class)
public enum CPReservaHorarioVideoConferencia {
	_LINKCONFERENCIA, _LINKCONFERENCIAFOIDEFINIDO;

	public static final String linkconferencia = "linkConferencia";
	public static final String linkconferenciafoidefinido = "linkConferenciaFoiDefinido";
}