package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorapresencial;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValorLogicoReservaHoraPresencial;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValoresLogicosReservaHoraPresencial;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraPresencial;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoReservaHoraPresencial(calculo = ValoresLogicosReservaHoraPresencial.CHAVEPIX)
public class ValorLogicoReservaHoraPresencialChavePix
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoReservaHoraPresencialChavePix(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public ReservaHoraPresencial getReservaHoraPresencial() {
		return (ReservaHoraPresencial) getCampoInst().getObjetoRaizDoAtributo();
	}
}