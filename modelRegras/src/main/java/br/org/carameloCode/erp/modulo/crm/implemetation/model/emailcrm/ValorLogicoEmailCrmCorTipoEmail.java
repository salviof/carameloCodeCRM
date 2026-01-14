package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailcrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.EmailCrm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValorLogicoEmailCrm;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValoresLogicosEmailCrm;

@ValorLogicoEmailCrm(calculo = ValoresLogicosEmailCrm.CORTIPOEMAIL)
public class ValorLogicoEmailCrmCorTipoEmail extends ValorLogicoCalculoGenerico {

    public ValorLogicoEmailCrmCorTipoEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getEnvioEmail().getCorTipoEmail();
    }

    public EmailCrm getEnvioEmail() {
        return (EmailCrm) getCampoInst().getObjetoDoAtributo();
    }
}
