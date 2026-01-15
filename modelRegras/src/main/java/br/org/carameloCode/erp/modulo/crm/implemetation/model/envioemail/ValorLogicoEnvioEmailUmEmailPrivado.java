package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.UMEMAILPRIVADO)
public class ValorLogicoEnvioEmailUmEmailPrivado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailUmEmailPrivado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (!getEnvioEmail().isUmEmailPrivado()) {
            if (getEnvioEmail().getProspecto() != null) {
                getEnvioEmail().setUmEmailPrivado(getEnvioEmail().getProspecto().isUmPerfilPrivado());
            }

        }

        return getEnvioEmail().isUmEmailPrivado();
    }

    public EnvioEmail getEnvioEmail() {
        return (EnvioEmail) getCampoInst().getObjetoDoAtributo();
    }
}
