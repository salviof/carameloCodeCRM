package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.pesquisaAtividade.PesquisaAtividade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValorLogicoPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValoresLogicosPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.CPPesquisaLead;

@ValorLogicoPesquisaAtividade(calculo = ValoresLogicosPesquisaAtividade.MOMENTOATUAL)
public class ValorLogicoPesquisaAtividadeMomentoAtual
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaAtividadeMomentoAtual(ItfCampoInstanciado pCampo) {
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

    private PesquisaAtividade getPesquisaLead() {
        return (PesquisaAtividade) getCampoInst().getObjetoDoAtributo();
    }
}
