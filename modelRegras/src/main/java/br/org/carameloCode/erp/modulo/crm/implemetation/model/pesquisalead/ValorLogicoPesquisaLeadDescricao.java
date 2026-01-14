package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.FabTipoPesquisaLeads;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.TipoPesquisaLead;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.CPPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValorLogicoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValoresLogicosPesquisaLead;

@ValorLogicoPesquisaLead(calculo = ValoresLogicosPesquisaLead.DESCRICAO)
public class ValorLogicoPesquisaLeadDescricao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaLeadDescricao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        FabTipoPesquisaLeads tipoPesquisa = ((TipoPesquisaLead) getPesquisaLead().getCPinst(CPPesquisaLead.tipopesquisa).getValor()).getTipoPesquisa();

        switch (tipoPesquisa) {
            case MEUS_LEADS:
                break;
            case ORIGEM_PUBLICAS:
                break;
            case ORIGEM_PRIVADA:
                break;
            case LEADS_URGENTES:
                break;
            case PESQUISA_LIVRE:
                break;
            default:
                throw new AssertionError(tipoPesquisa.name());

        }
        setValorPorReflexao(tipoPesquisa.toString());
        return getPesquisaLead().getTipoPesquisa();
    }

    public PesquisaLead getPesquisaLead() {
        return (PesquisaLead) getCampoInst().getObjetoDoAtributo();
    }

}
