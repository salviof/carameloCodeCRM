package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoconfirmacaoequipe;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaoequipe.ValorLogicoSolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaoequipe.ValoresLogicosSolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao.ValorLogicoSolicitacaoNotificacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoConfirmacaoEquipe(calculo = ValoresLogicosSolicitacaoConfirmacaoEquipe.NOTIFICACAO)
public class ValorLogicoSolicitacaoConfirmacaoEquipeNotificacao
        extends
        ValorLogicoSolicitacaoNotificacao {

    public ValorLogicoSolicitacaoConfirmacaoEquipeNotificacao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public SolicitacaoConfirmacaoEquipe getSolicitacaoConfirmacaoEquipe() {
        return (SolicitacaoConfirmacaoEquipe) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
