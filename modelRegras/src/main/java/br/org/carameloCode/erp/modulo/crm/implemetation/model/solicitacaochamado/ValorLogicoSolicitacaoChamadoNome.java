package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaochamado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaochamado.ValorLogicoSolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaochamado.ValoresLogicosSolicitacaoChamado;

@ValorLogicoSolicitacaoChamado(calculo = ValoresLogicosSolicitacaoChamado.NOME)
public class ValorLogicoSolicitacaoChamadoNome
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoSolicitacaoChamadoNome(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}