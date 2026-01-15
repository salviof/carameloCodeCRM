package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.ICONEALERTA)
public class ValorLogicoEnvioEmailIconeAlerta
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailIconeAlerta(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        try {
            if (getEmailEnviado().getId() != 0) {
                EnvioEmail email = UtilSBPersistencia.loadEntidade(getEmailEnviado(), UtilSBPersistencia.getEMDoContexto());
                if (!email.getFalhasEnvio().isEmpty() && !email.isFoiEnviado()) {
                    email.setIconeAlerta("fa  fa-exclamation-triangle text-danger");
                } else {
                    email.setIconeAlerta(null);
                }
            }
        } catch (Throwable t) {

        }
        return getEmailEnviado().getIconeAlerta();
    }

    private EnvioEmail getEmailEnviado() {
        return (EnvioEmail) getCampoInst().getObjetoDoAtributo();
    }
}
