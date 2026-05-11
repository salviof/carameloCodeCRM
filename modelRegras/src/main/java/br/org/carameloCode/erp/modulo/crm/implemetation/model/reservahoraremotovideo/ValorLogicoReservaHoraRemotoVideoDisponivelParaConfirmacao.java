package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahoraremotovideo;

import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValorLogicoReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValoresLogicosReservaHoraRemotoVideo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.reservahorario.ValorLogicoReservaHorarioDisponivelParaConfirmacao;

@ValorLogicoReservaHoraRemotoVideo(calculo = ValoresLogicosReservaHoraRemotoVideo.DISPONIVELPARACONFIRMACAO)
public class ValorLogicoReservaHoraRemotoVideoDisponivelParaConfirmacao
        extends
        ValorLogicoReservaHorarioDisponivelParaConfirmacao {

    public ValorLogicoReservaHoraRemotoVideoDisponivelParaConfirmacao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
