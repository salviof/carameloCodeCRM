package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoagendamentoatdmpresencial;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoagendamentoatdmpresencial.ValorLogicoTipoAgendamentoAtdmPresencial;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoagendamentoatdmpresencial.ValoresLogicosTipoAgendamentoAtdmPresencial;

@ValorLogicoTipoAgendamentoAtdmPresencial(calculo = ValoresLogicosTipoAgendamentoAtdmPresencial.TEXTOLOCALIZACAOREUNIAOINSIDE)
public class ValorLogicoTipoAgendamentoAtdmPresencialTextoLocalizacaoReuniaoInSide
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoTipoAgendamentoAtdmPresencialTextoLocalizacaoReuniaoInSide(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}