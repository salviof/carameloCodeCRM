package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.PesquisaLead;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.CPPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValorLogicoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValoresLogicosPesquisaLead;

@ValorLogicoPesquisaLead(calculo = ValoresLogicosPesquisaLead.MOMENTOATUAL)
public class ValorLogicoPesquisaLeadMomentoAtual
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaLeadMomentoAtual(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        Date dataFinal = (Date) getPesquisaLead().getCPinst(CPPesquisaLead.datafinal).getValor();

        if (UtilCRCDataHora.isDiaIgual(new Date(), dataFinal)) {
            getPesquisaLead().setMomentoAtual(true);
        } else {
            getPesquisaLead().setMomentoAtual(false);
        }

        return getPesquisaLead().isMomentoAtual();
    }

    public PesquisaLead getPesquisaLead() {
        return (PesquisaLead) getCampoInst().getObjetoDoAtributo();
    }
}
