package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaochamado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaochamado.ValorLogicoSolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaochamado.ValoresLogicosSolicitacaoChamado;

@ValorLogicoSolicitacaoChamado(calculo = ValoresLogicosSolicitacaoChamado.LINKCONVITE)
public class ValorLogicoSolicitacaoChamadoLinkConvite
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoSolicitacaoChamadoLinkConvite(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}