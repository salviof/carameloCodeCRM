package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoconfirmacaoequipe;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaoequipe.ValorLogicoSolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaoequipe.ValoresLogicosSolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoEquipe;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoConfirmacaoEquipe(calculo = ValoresLogicosSolicitacaoConfirmacaoEquipe.STATUS)
public class ValorLogicoSolicitacaoConfirmacaoEquipeStatus
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoSolicitacaoConfirmacaoEquipeStatus(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public SolicitacaoConfirmacaoEquipe getSolicitacaoConfirmacaoEquipe() {
		return (SolicitacaoConfirmacaoEquipe) getCampoInst()
				.getObjetoRaizDoAtributo();
	}
}