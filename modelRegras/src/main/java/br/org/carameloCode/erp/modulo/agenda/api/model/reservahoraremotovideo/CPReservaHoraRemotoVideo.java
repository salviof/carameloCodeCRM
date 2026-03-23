package br.org.carameloCode.erp.modulo.agenda.api.model.reservahoraremotovideo;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHoraRemotoVideo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ReservaHoraRemotoVideo.class)
public enum CPReservaHoraRemotoVideo {
	_LINKCONFERENCIA, _LINKCONFERENCIAFOIDEFINIDO;

	public static final String linkconferencia = "linkConferencia";
	public static final String linkconferenciafoidefinido = "linkConferenciaFoiDefinido";
}