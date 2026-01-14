package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.emailcrm.ValorLogicoEmailCrmUmEmailModoRascunho;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.UMEMAILMODORASCUNHO)
public class ValorLogicoEnvioEmailUmEmailModoRascunho
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailUmEmailModoRascunho(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return new ValorLogicoEmailCrmUmEmailModoRascunho(getCampoInst()).getValor(pEntidade);
    }

}
