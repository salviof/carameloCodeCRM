package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorapresencial;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValorLogicoReservaHoraPresencial;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValoresLogicosReservaHoraPresencial;

@ValorLogicoReservaHoraPresencial(calculo = ValoresLogicosReservaHoraPresencial.VALORRESERVA)
public class ValorLogicoReservaHoraPresencialValorReserva
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoReservaHoraPresencialValorReserva(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}