package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatividadecrmautoexecucao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrmautoexecucao.ValorLogicoTipoAtividadeCRMAutoExecucao;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrmautoexecucao.ValoresLogicosTipoAtividadeCRMAutoExecucao;

@ValorLogicoTipoAtividadeCRMAutoExecucao(calculo = ValoresLogicosTipoAtividadeCRMAutoExecucao.XHTMLALTERNATIVO)
public class ValorLogicoTipoAtividadeCRMAutoExecucaoXhtmlAlternativo
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoTipoAtividadeCRMAutoExecucaoXhtmlAlternativo(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}