package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacao.ValorLogicoTipoNotificacao;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacao.ValoresLogicosTipoNotificacao;

@ValorLogicoTipoNotificacao(calculo = ValoresLogicosTipoNotificacao.ENTIDADESDISPONIVEIS)
public class ValorLogicoTipoNotificacaoEntidadesDisponiveis
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoTipoNotificacaoEntidadesDisponiveis(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}