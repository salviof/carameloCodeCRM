package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorapresencial;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValidadorReservaHoraPresencial;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValidadoresReservaHoraPresencial;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorario.ValidacaoReservaHorarioFinalReservaAtendente;

@ValidadorReservaHoraPresencial(validador = ValidadoresReservaHoraPresencial.FINALRESERVAATENDENTE)
public class ValidacaoReservaHoraPresencialFinalReservaAtendente extends ValidacaoReservaHorarioFinalReservaAtendente {

    public ValidacaoReservaHoraPresencialFinalReservaAtendente(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
