package br.org.carameloCode.erp.modulo.crm.implemetation.model.statusenvioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.StatusEnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.statusenvioemail.ValorLogicoStatusEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.statusenvioemail.ValoresLogicosStatusEnvioEmail;

@ValorLogicoStatusEnvioEmail(calculo = ValoresLogicosStatusEnvioEmail.ICONESTATUS)
public class ValorLogicoStatusEnvioEmailIconeStatus
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoStatusEnvioEmailIconeStatus(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getStatus().getEnumVinculado() == null) {
            return "";
        }
        switch (getStatus().getStatusFabrica()) {
            case RASCUNHO:
                getStatus().setIconeStatus("fa fa-pencil-square-o");
                break;
            case FORMATADO:
                getStatus().setIconeStatus("fa fa-clock-o");
                break;
            case ENVIADO:
                getStatus().setIconeStatus("fa fa-paper-plane-o");
                break;
            case CONFIRMADO:
                getStatus().setIconeStatus("fa fa-check");

                break;
            default:
                throw new AssertionError(getStatus().getStatusFabrica().name());

        }
        return getStatus().getIconeStatus();
    }

    public StatusEnvioEmail getStatus() {
        return (StatusEnvioEmail) getCampoInst().getObjetoDoAtributo();
    }
}
