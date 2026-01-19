package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatvchamadarealizada;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarealizada.ValorLogicoTipoAtvChamadaRealizada;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarealizada.ValoresLogicosTipoAtvChamadaRealizada;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRealizada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoAtvChamadaRealizada(calculo = ValoresLogicosTipoAtvChamadaRealizada.ACOESDEPLUGINSDISPONIVEIS)
public class ValorLogicoTipoAtvChamadaRealizadaAcoesDePluginsDisponiveis
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoTipoAtvChamadaRealizadaAcoesDePluginsDisponiveis(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public TipoAtvChamadaRealizada getTipoAtvChamadaRealizada() {
		return (TipoAtvChamadaRealizada) getCampoInst()
				.getObjetoRaizDoAtributo();
	}
}