package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoatividadeequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAtividadeEquipe;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadeequipe.ValorLogicoSolicitacaoAtividadeEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadeequipe.ValoresLogicosSolicitacaoAtividadeEquipe;

@ValorLogicoSolicitacaoAtividadeEquipe(calculo = ValoresLogicosSolicitacaoAtividadeEquipe.LINKCONVITE)
public class ValorLogicoSolicitacaoAtividadeEquipeLinkConvite
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoAtividadeEquipeLinkConvite(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    private boolean valordefinido;

    @Override
    public Object getValor(Object... pEntidade) {
        if (!valordefinido) {
            String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB, getSolicitacao().getTipoAtividade(), getSolicitacao().getTipoAtividade());
            getSolicitacao().setLinkConvite(url);
            valordefinido = true;
        }
        return getSolicitacao().getLinkConvite();
    }

    public SolicitacaoAtividadeEquipe getSolicitacao() {
        return (SolicitacaoAtividadeEquipe) getCampoInst().getObjetoDoAtributo();
    }
}
