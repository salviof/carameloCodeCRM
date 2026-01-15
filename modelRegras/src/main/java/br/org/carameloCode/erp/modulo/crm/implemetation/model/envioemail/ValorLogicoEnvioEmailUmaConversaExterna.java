package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.UMACONVERSAEXTERNA)
public class ValorLogicoEnvioEmailUmaConversaExterna
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailUmaConversaExterna(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getEnvioMail().getProspecto() != null) {
            getEnvioMail().setUmaConversaExterna(true);
        } else {
            getEnvioMail().setUmaConversaExterna(false);
        }
        return getEnvioMail().isUmaConversaExterna();
    }

    public EnvioEmail getEnvioMail() {
        //getCampoInst().getValidacaoLogicaEstrategia()
        return (EnvioEmail) getCampoInst().getObjetoDoAtributo();
    }

}
