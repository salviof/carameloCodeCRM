package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoorcamento;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoorcamento.ValorLogicoSolicitacaoOrcamento;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoorcamento.ValoresLogicosSolicitacaoOrcamento;

@ValorLogicoSolicitacaoOrcamento(calculo = ValoresLogicosSolicitacaoOrcamento.NOME)
public class ValorLogicoSolicitacaoOrcamentoNome
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoSolicitacaoOrcamentoNome(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}