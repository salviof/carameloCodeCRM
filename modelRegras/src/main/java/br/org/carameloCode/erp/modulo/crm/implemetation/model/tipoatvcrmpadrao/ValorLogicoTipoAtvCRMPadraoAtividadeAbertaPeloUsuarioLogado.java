package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatvcrmpadrao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvcrmpadrao.ValorLogicoTipoAtvCRMPadrao;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvcrmpadrao.ValoresLogicosTipoAtvCRMPadrao;

@ValorLogicoTipoAtvCRMPadrao(calculo = ValoresLogicosTipoAtvCRMPadrao.ATIVIDADEABERTAPELOUSUARIOLOGADO)
public class ValorLogicoTipoAtvCRMPadraoAtividadeAbertaPeloUsuarioLogado
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoTipoAtvCRMPadraoAtividadeAbertaPeloUsuarioLogado(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}