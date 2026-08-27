package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipontfcrmpersonalizada;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipontfcrmpersonalizada.ValorLogicoTipoNtfCRMPersonalizada;
import br.org.carameloCode.erp.modulo.crm.api.model.tipontfcrmpersonalizada.ValoresLogicosTipoNtfCRMPersonalizada;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TipoNtfCRMPersonalizada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoNtfCRMPersonalizada(calculo = ValoresLogicosTipoNtfCRMPersonalizada.ENTIDADESDISPONIVEIS)
public class ValorLogicoTipoNtfCRMPersonalizadaEntidadesDisponiveis
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoTipoNtfCRMPersonalizadaEntidadesDisponiveis(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public TipoNtfCRMPersonalizada getTipoNtfCRMPersonalizada() {
		return (TipoNtfCRMPersonalizada) getCampoInst()
				.getObjetoRaizDoAtributo();
	}
}