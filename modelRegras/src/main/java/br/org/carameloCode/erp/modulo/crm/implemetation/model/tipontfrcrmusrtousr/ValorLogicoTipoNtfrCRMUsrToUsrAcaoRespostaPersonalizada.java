package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipontfrcrmusrtousr;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipontfrcrmusrtousr.ValorLogicoTipoNtfrCRMUsrToUsr;
import br.org.carameloCode.erp.modulo.crm.api.model.tipontfrcrmusrtousr.ValoresLogicosTipoNtfrCRMUsrToUsr;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TipoNtfrCRMUsrToUsr;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoNtfrCRMUsrToUsr(calculo = ValoresLogicosTipoNtfrCRMUsrToUsr.ACAORESPOSTAPERSONALIZADA)
public class ValorLogicoTipoNtfrCRMUsrToUsrAcaoRespostaPersonalizada
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoTipoNtfrCRMUsrToUsrAcaoRespostaPersonalizada(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public TipoNtfrCRMUsrToUsr getTipoNtfrCRMUsrToUsr() {
		return (TipoNtfrCRMUsrToUsr) getCampoInst().getObjetoRaizDoAtributo();
	}
}