package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadoia;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadoia.ValorLogicoTipoDadoIA;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadoia.ValoresLogicosTipoDadoIA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoDadoIA(calculo = ValoresLogicosTipoDadoIA.VALORPADRAO)
public class ValorLogicoTipoDadoIAValorPadrao
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoTipoDadoIAValorPadrao(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public TipoDadoIA getTipoDadoIA() {
		return (TipoDadoIA) getCampoInst().getObjetoRaizDoAtributo();
	}
}