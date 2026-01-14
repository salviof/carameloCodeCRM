package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahoraremotovideo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValidadorReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValidadoresReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorario.ValidacaoReservaHorarioInicioReservaAtendente;

@ValidadorReservaHoraRemotoVideo(validador = ValidadoresReservaHoraRemotoVideo.INICIORESERVAATENDENTE)
public class ValidacaoReservaHoraRemotoVideoInicioReservaAtendente extends ValidacaoReservaHorarioInicioReservaAtendente {

    public ValidacaoReservaHoraRemotoVideoInicioReservaAtendente(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
