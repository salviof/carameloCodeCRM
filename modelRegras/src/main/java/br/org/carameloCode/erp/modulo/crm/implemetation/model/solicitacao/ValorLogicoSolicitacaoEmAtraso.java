package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.ValorLogicoSolicitacao;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.ValoresLogicosSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacao(calculo = ValoresLogicosSolicitacao.EMATRASO)
public class ValorLogicoSolicitacaoEmAtraso extends ValorLogicoCalculoGenerico {

	public ValorLogicoSolicitacaoEmAtraso(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public Solicitacao getSolicitacao() {
		return (Solicitacao) getCampoInst().getObjetoRaizDoAtributo();
	}
}