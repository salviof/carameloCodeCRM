package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailcrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.EmailCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValorLogicoEmailCrm;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValoresLogicosEmailCrm;

@ValorLogicoEmailCrm(calculo = ValoresLogicosEmailCrm.UMEMAILMODORASCUNHO)
public class ValorLogicoEmailCrmUmEmailModoRascunho
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailCrmUmEmailModoRascunho(ItfCampoInstanciado pCampo) {
        super(pCampo);

    }

    @Override
    public Object getValor(Object... pEntidade) {
        getEmail().setUmEmailModoRascunho((getEmail() instanceof EnvioEmail || getEmail() instanceof EnvioEmailAtividade)
                && !((EnvioEmail) getEmail()).isFoiEnviado());
        return getEmail().isUmEmailModoRascunho();
    }

    public EmailCrm getEmail() {
        return (EmailCrm) getCampoInst().getObjetoDoAtributo();
    }
}
