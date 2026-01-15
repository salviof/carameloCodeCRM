package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.UMEMAILRECEBIDO)
public class ValorLogicoEnvioEmailUmEmailRecebido
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailUmEmailRecebido(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getEnvioEmail().setUmEmailRecebido(false);
        return getEnvioEmail().isUmEmailRecebido();
    }

    public EnvioEmail getEnvioEmail() {
        return (EnvioEmail) getCampoInst().getObjetoDoAtributo();
    }
}
