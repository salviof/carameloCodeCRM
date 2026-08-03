package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitarqatualizacaoeqp;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitarqatualizacaoeqp.ValorLogicoSolicitArqAtualizacaoEqp;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitarqatualizacaoeqp.ValoresLogicosSolicitArqAtualizacaoEqp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitArqAtualizacaoEqp;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitArqAtualizacaoEqp(calculo = ValoresLogicosSolicitArqAtualizacaoEqp.LINKCONVITE)
public class ValorLogicoSolicitArqAtualizacaoEqpLinkConvite
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitArqAtualizacaoEqpLinkConvite(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    private boolean valordefinido;

    @Override
    public Object getValor(Object... pEntidade) {
        if (!valordefinido) {
            String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO, getSolicitArqAtualizacaoEqp(), getSolicitArqAtualizacaoEqp().getPessoa());
            getSolicitArqAtualizacaoEqp().setLinkConvite(url);
            valordefinido = true;
        }
        return getSolicitArqAtualizacaoEqp().getLinkConvite();
    }

    public SolicitArqAtualizacaoEqp getSolicitArqAtualizacaoEqp() {
        return (SolicitArqAtualizacaoEqp) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
