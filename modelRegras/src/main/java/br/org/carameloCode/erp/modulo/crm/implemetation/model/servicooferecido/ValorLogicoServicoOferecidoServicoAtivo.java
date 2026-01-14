package br.org.carameloCode.erp.modulo.crm.implemetation.model.servicooferecido;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.servicooferecido.ValorLogicoServicoOferecido;
import br.org.carameloCode.erp.modulo.crm.api.model.servicooferecido.ValoresLogicosServicoOferecido;

@ValorLogicoServicoOferecido(calculo = ValoresLogicosServicoOferecido.SERVICOATIVO)
public class ValorLogicoServicoOferecidoServicoAtivo
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoServicoOferecidoServicoAtivo(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}