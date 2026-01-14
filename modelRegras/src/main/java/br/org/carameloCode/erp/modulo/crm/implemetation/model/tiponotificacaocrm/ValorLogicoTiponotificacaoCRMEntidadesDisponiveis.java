package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValorLogicoTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValoresLogicosTiponotificacaoCRM;

@ValorLogicoTiponotificacaoCRM(calculo = ValoresLogicosTiponotificacaoCRM.ENTIDADESDISPONIVEIS)
public class ValorLogicoTiponotificacaoCRMEntidadesDisponiveis
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoTiponotificacaoCRMEntidadesDisponiveis(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}