package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoatividadecliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAtividadeCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadecliente.ValorLogicoSolicitacaoAtividadeCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadecliente.ValoresLogicosSolicitacaoAtividadeCliente;

@ValorLogicoSolicitacaoAtividadeCliente(calculo = ValoresLogicosSolicitacaoAtividadeCliente.LINKCONVITE)
public class ValorLogicoSolicitacaoAtividadeClienteLinkConvite
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoAtividadeClienteLinkConvite(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private boolean valordefinido;

    @Override
    public Object getValor(Object... pEntidade) {
        if (!valordefinido) {
            //String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB, getSolicitacao().getTipoAtividade(), getSolicitacao().getTipoAtividade());
            //getSolicitacao().setLinkConvite(url);
            valordefinido = true;
        }
        return getSolicitacao().getLinkConvite();
    }

    public SolicitacaoAtividadeCliente getSolicitacao() {
        return (SolicitacaoAtividadeCliente) getCampoInst().getObjetoDoAtributo();
    }
}
