package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoacessocard;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoacessocard.ValorLogicoSolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoacessocard.ValoresLogicosSolicitacaoAcessoCard;

@ValorLogicoSolicitacaoAcessoCard(calculo = ValoresLogicosSolicitacaoAcessoCard.LINKCONVITE)
public class ValorLogicoSolicitacaoAcessoCardLinkConvite
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoAcessoCardLinkConvite(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private boolean valordefinido;

    @Override
    public Object getValor(Object... pEntidade) {
        if (!valordefinido) {
            String url = SBCore.getServicoVisualizacao()
                    .getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_CONCEDER_ACESSO, getSolicitacao(), getSolicitacao().getPessoa());
            getSolicitacao().setLinkConvite(url);
            valordefinido = true;
        }
        return getSolicitacao().getLinkConvite();
    }

    public Solicitacao getSolicitacao() {
        return (Solicitacao) getCampoInst().getObjetoDoAtributo();
    }

}
