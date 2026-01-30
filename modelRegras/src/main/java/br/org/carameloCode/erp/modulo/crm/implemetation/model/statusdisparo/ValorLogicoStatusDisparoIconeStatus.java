package br.org.carameloCode.erp.modulo.crm.implemetation.model.statusdisparo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.statusdisparo.ValorLogicoStatusDisparo;
import br.org.carameloCode.erp.modulo.crm.api.model.statusdisparo.ValoresLogicosStatusDisparo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa.StatusDisparo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoStatusDisparo(calculo = ValoresLogicosStatusDisparo.ICONESTATUS)
public class ValorLogicoStatusDisparoIconeStatus
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoStatusDisparoIconeStatus(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public StatusDisparo getStatusDisparo() {
		return (StatusDisparo) getCampoInst().getObjetoRaizDoAtributo();
	}
}