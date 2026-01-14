package br.org.carameloCode.erp.modulo.crm.implemetation.model.subpastaprivada;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.subpastaprivada.ValorLogicoSubPastaPrivada;
import br.org.carameloCode.erp.modulo.crm.api.model.subpastaprivada.ValoresLogicosSubPastaPrivada;

@ValorLogicoSubPastaPrivada(calculo = ValoresLogicosSubPastaPrivada.MIGALHASDEPAO)
public class ValorLogicoSubPastaPrivadaMigalhasDePao
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoSubPastaPrivadaMigalhasDePao(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}