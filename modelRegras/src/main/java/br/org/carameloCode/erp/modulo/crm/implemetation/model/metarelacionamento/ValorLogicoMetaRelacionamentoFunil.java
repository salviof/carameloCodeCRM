package br.org.carameloCode.erp.modulo.crm.implemetation.model.metarelacionamento;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.metarelacionamento.ValorLogicoMetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.model.metarelacionamento.ValoresLogicosMetaRelacionamento;

@ValorLogicoMetaRelacionamento(calculo = ValoresLogicosMetaRelacionamento.FUNIL)
public class ValorLogicoMetaRelacionamentoFunil
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetaRelacionamentoFunil(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
