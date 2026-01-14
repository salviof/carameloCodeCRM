package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.UMEMAILRESPOSTA)
public class ValorLogicoEnvioEmailAtividadeUmEmailResposta
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailAtividadeUmEmailResposta(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getEnvioEmail().setUmEmailResposta(false);
        return getEnvioEmail().isUmEmailResposta();
    }

    public EnvioEmailAtividade getEnvioEmail() {
        return (EnvioEmailAtividade) getCampoInst().getObjetoDoAtributo();
    }
}
