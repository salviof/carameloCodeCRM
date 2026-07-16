package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoconfirmacaocliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaocliente.ValorLogicoSolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaocliente.ValoresLogicosSolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoCliente;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.ArrayList;
import java.util.List;

@ValorLogicoSolicitacaoConfirmacaoCliente(calculo = ValoresLogicosSolicitacaoConfirmacaoCliente.RESPOSTASPOSSIVEIS)
public class ValorLogicoSolicitacaoConfirmacaoClienteRespostasPossiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoConfirmacaoClienteRespostasPossiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getSolicitacaoConfirmacaoCliente().getTipoComunicacao() != null) {
            List<ComoTipoRespostaComunicacao> respostas = getSolicitacaoConfirmacaoCliente().getTipoComunicacao().getTipoRespostasPossiveis();
            getSolicitacaoConfirmacaoCliente().setRespostasPossiveis((List) respostas);
        } else {
            getSolicitacaoConfirmacaoCliente().setRespostasPossiveis(new ArrayList<>());
        }
        return getSolicitacaoConfirmacaoCliente().getRespostasPossiveis();
    }

    public SolicitacaoConfirmacaoCliente getSolicitacaoConfirmacaoCliente() {
        return (SolicitacaoConfirmacaoCliente) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
