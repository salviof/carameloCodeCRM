package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValidadorEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValidadoresEnvioEmailAtividade;

@ValidadorEnvioEmailAtividade(validador = ValidadoresEnvioEmailAtividade.EMAILSOLICITANTE)
public class ValidacaoEnvioEmailAtividadeEmailSolicitante extends ValidacaoGenerica<EnvioEmailAtividade> {

    public ValidacaoEnvioEmailAtividadeEmailSolicitante(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public EnvioEmailAtividade getEnvioEmailAtividade() {
        return getObjetoDoAtributo();
    }
}
