package br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo;

import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraRemotoVideo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ReservaHoraRemotoVideo.class)
public enum CPReservaHoraRemotoVideo {
	_LINKCONFERENCIA, _LINKCONFERENCIAFOIDEFINIDO;

	public static final String linkconferencia = "linkConferencia";
	public static final String linkconferenciafoidefinido = "linkConferenciaFoiDefinido";
}