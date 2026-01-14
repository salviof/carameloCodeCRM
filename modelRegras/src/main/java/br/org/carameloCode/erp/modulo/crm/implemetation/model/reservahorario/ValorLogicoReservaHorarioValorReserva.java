package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.ValorLogicoReservaHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.ValoresLogicosReservaHorario;

@ValorLogicoReservaHorario(calculo = ValoresLogicosReservaHorario.VALORRESERVA)
public class ValorLogicoReservaHorarioValorReserva
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoReservaHorarioValorReserva(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}