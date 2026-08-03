package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitarqatualizacaoeqp;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitarqatualizacaoeqp.ValorLogicoSolicitArqAtualizacaoEqp;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitarqatualizacaoeqp.ValoresLogicosSolicitArqAtualizacaoEqp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitArqAtualizacaoEqp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitArqAtualizacaoEqp(calculo = ValoresLogicosSolicitArqAtualizacaoEqp.EMATRASO)
public class ValorLogicoSolicitArqAtualizacaoEqpEmAtraso
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoSolicitArqAtualizacaoEqpEmAtraso(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public SolicitArqAtualizacaoEqp getSolicitArqAtualizacaoEqp() {
		return (SolicitArqAtualizacaoEqp) getCampoInst()
				.getObjetoRaizDoAtributo();
	}
}