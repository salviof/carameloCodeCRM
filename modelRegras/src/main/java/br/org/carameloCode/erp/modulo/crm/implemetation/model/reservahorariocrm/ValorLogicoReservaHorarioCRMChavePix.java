package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorariocrm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorariocrm.ValorLogicoReservaHorarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorariocrm.ValoresLogicosReservaHorarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoReservaHorarioCRM(calculo = ValoresLogicosReservaHorarioCRM.CHAVEPIX)
public class ValorLogicoReservaHorarioCRMChavePix
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoReservaHorarioCRMChavePix(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public ReservaHorarioCRM getReservaHorarioCRM() {
		return (ReservaHorarioCRM) getCampoInst().getObjetoRaizDoAtributo();
	}
}