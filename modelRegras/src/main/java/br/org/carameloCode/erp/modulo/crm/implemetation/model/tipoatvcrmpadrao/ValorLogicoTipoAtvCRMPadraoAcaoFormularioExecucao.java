package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatvcrmpadrao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvcrmpadrao.ValorLogicoTipoAtvCRMPadrao;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvcrmpadrao.ValoresLogicosTipoAtvCRMPadrao;

@ValorLogicoTipoAtvCRMPadrao(calculo = ValoresLogicosTipoAtvCRMPadrao.ACAOFORMULARIOEXECUCAO)
public class ValorLogicoTipoAtvCRMPadraoAcaoFormularioExecucao
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoTipoAtvCRMPadraoAcaoFormularioExecucao(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}