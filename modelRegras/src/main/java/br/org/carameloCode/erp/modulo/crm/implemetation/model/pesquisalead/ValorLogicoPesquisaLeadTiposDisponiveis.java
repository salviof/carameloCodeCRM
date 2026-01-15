package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.FabTipoPesquisaLeads;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.TipoPesquisaLead;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValorLogicoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValoresLogicosPesquisaLead;

@ValorLogicoPesquisaLead(calculo = ValoresLogicosPesquisaLead.TIPOSDISPONIVEIS)
public class ValorLogicoPesquisaLeadTiposDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaLeadTiposDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getPesquisa().getTiposDisponiveis() == null
                || getPesquisa().getTiposDisponiveis().isEmpty()) {
            List<TipoPesquisaLead> tiposPesquisa = UtilSBPersistencia.getListaTodos(TipoPesquisaLead.class, UtilSBPersistencia.getEMDoContexto());
            tiposPesquisa.remove(FabTipoPesquisaLeads.ORIGEM_PRIVADA.getRegistro());
            tiposPesquisa.remove(FabTipoPesquisaLeads.ORIGEM_PUBLICAS.getRegistro());
            getPesquisa().setTiposDisponiveis(tiposPesquisa);
        }

        return getPesquisa().getTiposDisponiveis();
    }

    public PesquisaLead getPesquisa() {
        return (PesquisaLead) getCampoInst().getObjetoDoAtributo();
    }
}
