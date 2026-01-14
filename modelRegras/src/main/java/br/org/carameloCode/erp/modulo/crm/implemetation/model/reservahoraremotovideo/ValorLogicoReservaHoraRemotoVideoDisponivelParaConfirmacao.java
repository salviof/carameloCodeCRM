package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahoraremotovideo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValorLogicoReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValoresLogicosReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorario.ValorLogicoReservaHorarioDisponivelParaConfirmacao;

@ValorLogicoReservaHoraRemotoVideo(calculo = ValoresLogicosReservaHoraRemotoVideo.DISPONIVELPARACONFIRMACAO)
public class ValorLogicoReservaHoraRemotoVideoDisponivelParaConfirmacao
        extends
        ValorLogicoReservaHorarioDisponivelParaConfirmacao {

    public ValorLogicoReservaHoraRemotoVideoDisponivelParaConfirmacao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
