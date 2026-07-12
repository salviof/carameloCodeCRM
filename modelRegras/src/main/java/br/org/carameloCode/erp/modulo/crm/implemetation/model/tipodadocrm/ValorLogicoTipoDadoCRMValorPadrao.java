package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadocrm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrm.ValorLogicoTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrm.ValoresLogicosTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoDadoCRM(calculo = ValoresLogicosTipoDadoCRM.VALORPADRAO)
public class ValorLogicoTipoDadoCRMValorPadrao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoDadoCRMValorPadrao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getTipoDadoCRM().getValorPadrao();
    }

    public TipoDadoCRM getTipoDadoCRM() {
        return (TipoDadoCRM) getCampoInst().getObjetoRaizDoAtributo();
    }
}
