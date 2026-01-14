package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.FabStatusEnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.ICONETIPOEMAIL)
public class ValorLogicoEnvioEmailIconeTipoEmail
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailIconeTipoEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public EnvioEmail getEnvioEmail() {
        return (EnvioEmail) getCampoInst().getObjetoDoAtributo();
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getEnvioEmail().getStatusEnvio() == null) {
            getEnvioEmail().setIconeTipoEmail("fa fa-pencil-square-o");
            return getEnvioEmail().getIconeTipoEmail();
        }
        if (getEnvioEmail().getStatusEnvio().equals(FabStatusEnvioEmail.RASCUNHO.getRegistro())) {
            getEnvioEmail().setIconeTipoEmail("fa fa-pencil-square-o");
            return getEnvioEmail().getIconeTipoEmail();
        }
        if (getEnvioEmail().getStatusEnvio().equals(FabStatusEnvioEmail.FORMATADO.getRegistro())) {
            if (getEnvioEmail().getDataHoraAgendamentoDisparo() != null) {
                getEnvioEmail().setIconeTipoEmail("fa fa-clock-o");
                return getEnvioEmail().getIconeTipoEmail();
            } else {
                getEnvioEmail().setIconeTipoEmail("fa fa-check");
                return getEnvioEmail().getIconeTipoEmail();
            }
        }
        if (getEnvioEmail().getStatusEnvio().equals(FabStatusEnvioEmail.ENVIADO.getRegistro())) {
            getEnvioEmail().setIconeTipoEmail("fa fa-paper-plane-o");
            return getEnvioEmail().getIconeTipoEmail();
        }
        if (getEnvioEmail().getStatusEnvio().equals(FabStatusEnvioEmail.CONFIRMADO.getRegistro())) {
            getEnvioEmail().setIconeTipoEmail("fa fa-envelope-open-o");
            return getEnvioEmail().getIconeTipoEmail();
        }

        return getEnvioEmail().getIconeTipoEmail();
    }

}
