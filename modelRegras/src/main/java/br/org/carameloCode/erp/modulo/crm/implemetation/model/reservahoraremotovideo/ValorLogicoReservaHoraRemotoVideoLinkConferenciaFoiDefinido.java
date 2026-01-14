package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahoraremotovideo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorariovideoconferencia.ValorLogicoReservaHorarioVideoConferencia;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorariovideoconferencia.ValoresLogicosReservaHorarioVideoConferencia;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraRemotoVideo;

@ValorLogicoReservaHorarioVideoConferencia(calculo = ValoresLogicosReservaHorarioVideoConferencia.LINKCONFERENCIAFOIDEFINIDO)
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
