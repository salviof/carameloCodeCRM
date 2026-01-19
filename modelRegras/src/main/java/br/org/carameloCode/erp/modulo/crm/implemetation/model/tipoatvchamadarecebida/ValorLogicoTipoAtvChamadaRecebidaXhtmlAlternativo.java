package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatvchamadarecebida;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarecebida.ValorLogicoTipoAtvChamadaRecebida;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarecebida.ValoresLogicosTipoAtvChamadaRecebida;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRecebida;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoAtvChamadaRecebida(calculo = ValoresLogicosTipoAtvChamadaRecebida.XHTMLALTERNATIVO)
public class ValorLogicoTipoAtvChamadaRecebidaXhtmlAlternativo
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoTipoAtvChamadaRecebidaXhtmlAlternativo(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public TipoAtvChamadaRecebida getTipoAtvChamadaRecebida() {
		return (TipoAtvChamadaRecebida) getCampoInst()
				.getObjetoRaizDoAtributo();
	}
}