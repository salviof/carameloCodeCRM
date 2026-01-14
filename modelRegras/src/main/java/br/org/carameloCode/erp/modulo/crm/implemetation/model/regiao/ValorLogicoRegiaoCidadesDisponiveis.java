package br.org.carameloCode.erp.modulo.crm.implemetation.model.regiao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.regiao.ValorLogicoRegiao;
import br.org.carameloCode.erp.modulo.crm.api.model.regiao.ValoresLogicosRegiao;

@ValorLogicoRegiao(calculo = ValoresLogicosRegiao.CIDADESDISPONIVEIS)
public class ValorLogicoRegiaoCidadesDisponiveis
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoRegiaoCidadesDisponiveis(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}