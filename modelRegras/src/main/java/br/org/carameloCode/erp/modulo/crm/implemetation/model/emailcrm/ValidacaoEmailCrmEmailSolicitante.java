package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailcrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.EmailCrm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValidadorEmailCrm;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValidadoresEmailCrm;

@ValidadorEmailCrm(validador = ValidadoresEmailCrm.EMAILSOLICITANTE)
public class ValidacaoEmailCrmEmailSolicitante extends ValidacaoGenerica<EmailCrm> {

    public ValidacaoEmailCrmEmailSolicitante(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public EmailCrm getEmailCrm() {
        return getObjetoDoAtributo();
    }
}
