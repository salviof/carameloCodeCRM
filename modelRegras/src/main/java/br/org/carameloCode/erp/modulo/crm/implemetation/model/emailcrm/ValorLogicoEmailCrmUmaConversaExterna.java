package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailcrm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValorLogicoEmailCrm;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValoresLogicosEmailCrm;

@ValorLogicoEmailCrm(calculo = ValoresLogicosEmailCrm.UMACONVERSAEXTERNA)
public class ValorLogicoEmailCrmUmaConversaExterna
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailCrmUmaConversaExterna(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
