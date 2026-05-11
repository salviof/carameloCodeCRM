package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahoraremotovideo;

import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValorLogicoReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValoresLogicosReservaHoraRemotoVideo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraRemotoVideo;

@ValorLogicoReservaHoraRemotoVideo(calculo = ValoresLogicosReservaHoraRemotoVideo.LINKCONFERENCIAFOIDEFINIDO)
public class ValorLogicoReservaHoraRemotoVideoLinkConferenciaFoiDefinido
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoReservaHoraRemotoVideoLinkConferenciaFoiDefinido(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getReservaHorarioVideoConferencia().setLinkConferenciaFoiDefinido(getReservaHorarioVideoConferencia().getLinkConferencia() != null);
        return getReservaHorarioVideoConferencia().isLinkConferenciaFoiDefinido();
    }

    public ReservaHoraRemotoVideo getReservaHorarioVideoConferencia() {
        return (ReservaHoraRemotoVideo) getCampoInst().getObjetoDoAtributo();
    }
}
