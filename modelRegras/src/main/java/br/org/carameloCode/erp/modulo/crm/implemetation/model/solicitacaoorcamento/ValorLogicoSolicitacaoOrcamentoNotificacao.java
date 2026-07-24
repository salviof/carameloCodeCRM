package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoorcamento;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoorcamento.ValorLogicoSolicitacaoOrcamento;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoorcamento.ValoresLogicosSolicitacaoOrcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoOrcamento;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao.ValorLogicoSolicitacaoNotificacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoOrcamento(calculo = ValoresLogicosSolicitacaoOrcamento.NOTIFICACAO)
public class ValorLogicoSolicitacaoOrcamentoNotificacao
        extends
        ValorLogicoSolicitacaoNotificacao {

    public ValorLogicoSolicitacaoOrcamentoNotificacao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public SolicitacaoOrcamento getSolicitacaoOrcamento() {
        return (SolicitacaoOrcamento) getCampoInst().getObjetoRaizDoAtributo();
    }
}
