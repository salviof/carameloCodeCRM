package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.UMEMAILPRIVADO)
public class ValorLogicoEnvioEmailAtividadeUmEmailPrivado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailAtividadeUmEmailPrivado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (!getEnvioEmail().isUmEmailPrivado()) {
            getEnvioEmail().setUmEmailPrivado(getEnvioEmail().getProspecto().isUmPerfilPrivado());
        }

        return getEnvioEmail().isUmEmailPrivado();
    }

    public EnvioEmailAtividade getEnvioEmail() {
        return (EnvioEmailAtividade) getCampoInst().getObjetoDoAtributo();
    }
}
