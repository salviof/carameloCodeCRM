package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValidadorEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValidadoresEnvioEmail;

@ValidadorEnvioEmail(validador = ValidadoresEnvioEmail.EMAILSOLICITANTE)
public class ValidacaoEnvioEmailEmailSolicitante extends ValidacaoGenerica<EnvioEmail> {

    public ValidacaoEnvioEmailEmailSolicitante(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public EnvioEmail getEnvioEmail() {
        return getObjetoDoAtributo();
    }
}
