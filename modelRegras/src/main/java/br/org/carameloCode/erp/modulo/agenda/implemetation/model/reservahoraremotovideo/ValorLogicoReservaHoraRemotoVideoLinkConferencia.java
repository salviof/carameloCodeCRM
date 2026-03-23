package br.org.carameloCode.erp.modulo.agenda.implemetation.model.reservahoraremotovideo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorariovideoconferencia.ValorLogicoReservaHorarioVideoConferencia;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorariovideoconferencia.ValoresLogicosReservaHorarioVideoConferencia;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHoraRemotoVideo;

@ValorLogicoReservaHorarioVideoConferencia(calculo = ValoresLogicosReservaHorarioVideoConferencia.LINKCONFERENCIA)
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
