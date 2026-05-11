package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahoraremotovideo;

import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValorLogicoReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValoresLogicosReservaHoraRemotoVideo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraRemotoVideo;

@ValorLogicoReservaHoraRemotoVideo(calculo = ValoresLogicosReservaHoraRemotoVideo.LINKCONFERENCIA)
public class ValorLogicoReservaHoraRemotoVideoLinkConferencia
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoReservaHoraRemotoVideoLinkConferencia(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getReservaHorarioVideoConferencia().getLinkConferencia();
    }

    public ReservaHoraRemotoVideo getReservaHorarioVideoConferencia() {
        return (ReservaHoraRemotoVideo) getCampoInst().getObjetoDoAtributo();
    }

}
