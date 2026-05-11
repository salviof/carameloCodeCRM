package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipochamado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipochamado.ValorLogicoTipoChamado;
import br.org.carameloCode.erp.modulo.crm.api.model.tipochamado.ValoresLogicosTipoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.TipoChamado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoChamado(calculo = ValoresLogicosTipoChamado.RESPONSAVEIS)
public class ValorLogicoTipoChamadoResponsaveis
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoTipoChamadoResponsaveis(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public TipoChamado getTipoChamado() {
		return (TipoChamado) getCampoInst().getObjetoRaizDoAtributo();
	}
}