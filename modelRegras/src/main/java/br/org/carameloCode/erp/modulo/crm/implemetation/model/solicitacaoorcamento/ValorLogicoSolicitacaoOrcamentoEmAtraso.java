package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoorcamento;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoorcamento.ValorLogicoSolicitacaoOrcamento;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoorcamento.ValoresLogicosSolicitacaoOrcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoOrcamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoOrcamento(calculo = ValoresLogicosSolicitacaoOrcamento.EMATRASO)
public class ValorLogicoSolicitacaoOrcamentoEmAtraso
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoSolicitacaoOrcamentoEmAtraso(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public SolicitacaoOrcamento getSolicitacaoOrcamento() {
		return (SolicitacaoOrcamento) getCampoInst().getObjetoRaizDoAtributo();
	}
}