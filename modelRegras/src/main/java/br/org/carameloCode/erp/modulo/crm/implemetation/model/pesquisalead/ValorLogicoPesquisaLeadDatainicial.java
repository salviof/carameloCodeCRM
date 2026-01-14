package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.TipoPesquisaLead;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.CPPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValorLogicoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValoresLogicosPesquisaLead;

@ValorLogicoPesquisaLead(calculo = ValoresLogicosPesquisaLead.DATAINICIAL)
public class ValorLogicoPesquisaLeadDatainicial
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaLeadDatainicial(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getPesquisaLead().getDatainicial() == null) {
            TipoPesquisaLead tipoPesquisa = (TipoPesquisaLead) getPesquisaLead().getCampoInstanciadoByNomeOuAnotacao(CPPesquisaLead.tipopesquisa).getValor();
            switch (tipoPesquisa.getTipoPesquisa()) {
                case MEUS_LEADS:
                    getPesquisaLead().setDatainicial(null);
                    break;
                case ORIGEM_PUBLICAS:
                    if (getPesquisaLead().getDatainicial() == null) {
//                        getPesquisaLead().setDatainicial(UtilCRCDataHora.decrementarDias(new Date(), 360));
                    }
                    getPesquisaLead().setDatainicial(null);
                    break;
                case ORIGEM_PRIVADA:
                    getPesquisaLead().setDatainicial(null);
                    break;
                case LEADS_URGENTES:
                    getPesquisaLead().setDatainicial(null);
                    break;
                case PESQUISA_LIVRE:
                    //    if (getPesquisaLead().getDatainicial() == null) {
                    //         getPesquisaLead().setDatainicial(UtilCRCDataHora.decrementarDias(new Date(), 360));
                    //         getPesquisaLead().setDatafinal(UtilCRCDataHora.incrementaDias(new Date(), 1));
                    //      }

                    break;
                default:
                    throw new AssertionError(tipoPesquisa.getTipoPesquisa().name());

            }

        }
        return getPesquisaLead().getDatainicial();
    }

    public PesquisaLead getPesquisaLead() {
        return (PesquisaLead) getCampoInst().getObjetoDoAtributo();
    }

}
