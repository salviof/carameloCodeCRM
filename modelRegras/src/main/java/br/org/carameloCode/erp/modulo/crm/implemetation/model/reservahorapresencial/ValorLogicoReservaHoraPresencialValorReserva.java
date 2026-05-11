package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorapresencial;

import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValorLogicoReservaHoraPresencial;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValoresLogicosReservaHoraPresencial;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;

@ValorLogicoReservaHoraPresencial(calculo = ValoresLogicosReservaHoraPresencial.VALORRESERVA)
public class ValorLogicoReservaHoraPresencialValorReserva
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoReservaHoraPresencialValorReserva(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
