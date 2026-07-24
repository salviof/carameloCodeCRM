package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoatividadeequipe;

import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadeequipe.ValorLogicoSolicitacaoAtividadeEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadeequipe.ValoresLogicosSolicitacaoAtividadeEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAtividadeEquipe;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao.ValorLogicoSolicitacaoNotificacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoAtividadeEquipe(calculo = ValoresLogicosSolicitacaoAtividadeEquipe.NOTIFICACAO)
public class ValorLogicoSolicitacaoAtividadeEquipeNotificacao
        extends
        ValorLogicoSolicitacaoNotificacao {

    public ValorLogicoSolicitacaoAtividadeEquipeNotificacao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public SolicitacaoAtividadeEquipe getSolicitacaoAtividadeEquipe() {
        return (SolicitacaoAtividadeEquipe) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
