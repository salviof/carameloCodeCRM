package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValorLogicoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValoresLogicosPesquisaLead;

@ValorLogicoPesquisaLead(calculo = ValoresLogicosPesquisaLead.TEXTOINTERVALODATAS)
public class ValorLogicoPesquisaLeadTextoIntervaloDatas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaLeadTextoIntervaloDatas(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getPesquisaLead().getDatafinal() == null || getPesquisaLead().getDatainicial() == null) {
            getPesquisaLead().setTextoIntervaloDatas("Sempre");
            return getPesquisaLead().getTextoIntervaloDatas();
        }
        StringBuilder textoBuilder = new StringBuilder();
        int diainicial = UtilCRCDataHora.getDia(getPesquisaLead().getDatainicial());
        String mesInicio = UtilCRCDataHora.getMesTexto(getPesquisaLead().getDatainicial());
        String anoInicio = UtilCRCDataHora.getAnoAbreviado(getPesquisaLead().getDatainicial());
        String anoAgora = UtilCRCDataHora.getAnoAbreviado(new Date());

        if (diainicial > 1) {
            textoBuilder.append(diainicial);
            textoBuilder.append(" de ");
            textoBuilder.append(mesInicio);
            if (!anoAgora.equals(anoInicio)) {
                textoBuilder.append(" de ");
                textoBuilder.append(anoInicio);
            }
        } else {
            textoBuilder.append(mesInicio);
        }
        textoBuilder.append(" até ");
        if (getPesquisaLead().isMomentoAtual()) {
            textoBuilder.append("agora");
        } else {
            int diafinal = UtilCRCDataHora.getDia(getPesquisaLead().getDatafinal());
            String mesFinal = UtilCRCDataHora.getMesTexto(getPesquisaLead().getDatafinal());
            textoBuilder.append(diafinal);
            textoBuilder.append(" de ");
            textoBuilder.append(mesFinal);
        }
        getPesquisaLead().setTextoIntervaloDatas(textoBuilder.toString());
        return getPesquisaLead().getTextoIntervaloDatas();
    }

    public PesquisaLead getPesquisaLead() {
        return (PesquisaLead) getCampoInst().getObjetoDoAtributo();
    }

}
