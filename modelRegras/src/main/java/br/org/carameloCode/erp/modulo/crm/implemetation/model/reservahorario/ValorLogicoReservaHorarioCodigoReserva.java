package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.ValorLogicoReservaHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.ValoresLogicosReservaHorario;

@ValorLogicoReservaHorario(calculo = ValoresLogicosReservaHorario.CODIGORESERVA)
public class ValorLogicoReservaHorarioCodigoReserva
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoReservaHorarioCodigoReserva(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
