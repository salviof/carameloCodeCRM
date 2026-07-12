package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoacessocard;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoacessocard.ValorLogicoSolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoacessocard.ValoresLogicosSolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAcessoCard;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoAcessoCard(calculo = ValoresLogicosSolicitacaoAcessoCard.STATUS)
public class ValorLogicoSolicitacaoAcessoCardStatus
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoSolicitacaoAcessoCardStatus(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public SolicitacaoAcessoCard getSolicitacaoAcessoCard() {
		return (SolicitacaoAcessoCard) getCampoInst().getObjetoRaizDoAtributo();
	}
}