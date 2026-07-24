package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoacessocard;

import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoacessocard.ValorLogicoSolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoacessocard.ValoresLogicosSolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao.ValorLogicoSolicitacaoNotificacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoAcessoCard(calculo = ValoresLogicosSolicitacaoAcessoCard.NOTIFICACAO)
public class ValorLogicoSolicitacaoAcessoCardNotificacao
        extends
        ValorLogicoSolicitacaoNotificacao {

    public ValorLogicoSolicitacaoAcessoCardNotificacao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public SolicitacaoAcessoCard getSolicitacaoAcessoCard() {
        return (SolicitacaoAcessoCard) getCampoInst().getObjetoRaizDoAtributo();
    }
}
