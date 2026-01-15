package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.UMEMAILRECEBIDO)
public class ValorLogicoEnvioEmailAtividadeUmEmailRecebido
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailAtividadeUmEmailRecebido(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getEnvioEmail().setUmEmailRecebido(false);
        return getEnvioEmail().isUmEmailRecebido();
    }

    public EnvioEmailAtividade getEnvioEmail() {
        return (EnvioEmailAtividade) getCampoInst().getObjetoDoAtributo();
    }
}
