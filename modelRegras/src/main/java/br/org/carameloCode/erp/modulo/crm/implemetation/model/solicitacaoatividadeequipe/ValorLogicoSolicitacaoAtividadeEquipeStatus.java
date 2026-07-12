package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoatividadeequipe;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadeequipe.ValorLogicoSolicitacaoAtividadeEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadeequipe.ValoresLogicosSolicitacaoAtividadeEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAtividadeEquipe;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoAtividadeEquipe(calculo = ValoresLogicosSolicitacaoAtividadeEquipe.STATUS)
public class ValorLogicoSolicitacaoAtividadeEquipeStatus
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoSolicitacaoAtividadeEquipeStatus(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public SolicitacaoAtividadeEquipe getSolicitacaoAtividadeEquipe() {
		return (SolicitacaoAtividadeEquipe) getCampoInst()
				.getObjetoRaizDoAtributo();
	}
}