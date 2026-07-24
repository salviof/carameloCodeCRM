package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaochamado;

import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaochamado.ValorLogicoSolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaochamado.ValoresLogicosSolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao.ValorLogicoSolicitacaoNotificacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoChamado(calculo = ValoresLogicosSolicitacaoChamado.NOTIFICACAO)
public class ValorLogicoSolicitacaoChamadoNotificacao
        extends
        ValorLogicoSolicitacaoNotificacao {

    public ValorLogicoSolicitacaoChamadoNotificacao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public SolicitacaoChamado getSolicitacaoChamado() {
        return (SolicitacaoChamado) getCampoInst().getObjetoRaizDoAtributo();
    }
}
