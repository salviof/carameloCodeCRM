package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadocrmlogica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlogica.ValorLogicoTipoDadoCRMLogica;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlogica.ValoresLogicosTipoDadoCRMLogica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRMLogica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoDadoCRMLogica(calculo = ValoresLogicosTipoDadoCRMLogica.VALORPADRAO)
public class ValorLogicoTipoDadoCRMLogicaValorPadrao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoDadoCRMLogicaValorPadrao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getTipoDadoCRMLogica().getValorPadrao();
    }

    public TipoDadoCRMLogica getTipoDadoCRMLogica() {
        return (TipoDadoCRMLogica) getCampoInst().getObjetoRaizDoAtributo();
    }
}
