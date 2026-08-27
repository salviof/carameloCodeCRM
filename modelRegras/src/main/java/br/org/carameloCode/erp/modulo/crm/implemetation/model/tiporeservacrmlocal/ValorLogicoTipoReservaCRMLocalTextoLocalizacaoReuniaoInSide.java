package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiporeservacrmlocal;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporeservacrmlocal.ValorLogicoTipoReservaCRMLocal;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporeservacrmlocal.ValoresLogicosTipoReservaCRMLocal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.TipoReservaCRMLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoReservaCRMLocal(calculo = ValoresLogicosTipoReservaCRMLocal.TEXTOLOCALIZACAOREUNIAOINSIDE)
public class ValorLogicoTipoReservaCRMLocalTextoLocalizacaoReuniaoInSide
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoTipoReservaCRMLocalTextoLocalizacaoReuniaoInSide(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public TipoReservaCRMLocal getTipoReservaCRMLocal() {
		return (TipoReservaCRMLocal) getCampoInst().getObjetoRaizDoAtributo();
	}
}