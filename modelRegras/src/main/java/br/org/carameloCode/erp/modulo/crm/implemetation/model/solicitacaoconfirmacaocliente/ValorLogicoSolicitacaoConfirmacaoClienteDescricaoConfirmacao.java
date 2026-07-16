package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoconfirmacaocliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaocliente.ValorLogicoSolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaocliente.ValoresLogicosSolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoConfirmacaoCliente(calculo = ValoresLogicosSolicitacaoConfirmacaoCliente.DESCRICAOCONFIRMACAO)
public class ValorLogicoSolicitacaoConfirmacaoClienteDescricaoConfirmacao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoConfirmacaoClienteDescricaoConfirmacao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        StringBuilder pretexto = new StringBuilder();
        if (getSolicitacaoConfirmacaoCliente().getTipoRespostaSelecionada() != null) {
            pretexto.append(getSolicitacaoConfirmacaoCliente().getTipoRespostaSelecionada().getNome());
            pretexto.append("; ");
            String texto;
            if (getSolicitacaoConfirmacaoCliente().getDescricaoConfirmacao() != null) {
                if (!getSolicitacaoConfirmacaoCliente().getDescricaoConfirmacao().contains("; ")) {
                    texto = pretexto.toString() + getSolicitacaoConfirmacaoCliente().getDescricaoConfirmacao();
                } else {
                    texto = getSolicitacaoConfirmacaoCliente().getDescricaoConfirmacao();
                }
            } else {
                texto = pretexto.toString();
            }
            getSolicitacaoConfirmacaoCliente().setDescricaoConfirmacao(texto);
        }

        return getSolicitacaoConfirmacaoCliente().getDescricaoConfirmacao();
    }

    public SolicitacaoConfirmacaoCliente getSolicitacaoConfirmacaoCliente() {
        return (SolicitacaoConfirmacaoCliente) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
