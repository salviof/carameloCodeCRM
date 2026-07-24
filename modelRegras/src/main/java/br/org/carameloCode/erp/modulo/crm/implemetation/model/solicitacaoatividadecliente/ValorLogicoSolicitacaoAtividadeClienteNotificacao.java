package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoatividadecliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadecliente.ValorLogicoSolicitacaoAtividadeCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadecliente.ValoresLogicosSolicitacaoAtividadeCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAtividadeCliente;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao.ValorLogicoSolicitacaoNotificacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoAtividadeCliente(calculo = ValoresLogicosSolicitacaoAtividadeCliente.NOTIFICACAO)
public class ValorLogicoSolicitacaoAtividadeClienteNotificacao
        extends
        ValorLogicoSolicitacaoNotificacao {

    public ValorLogicoSolicitacaoAtividadeClienteNotificacao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public SolicitacaoAtividadeCliente getSolicitacaoAtividadeCliente() {
        return (SolicitacaoAtividadeCliente) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
