package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.emailcrm.ValorLogicoEmailCrmUmEmailModoRascunho;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.UMEMAILMODORASCUNHO)
public class ValorLogicoEnvioEmailAtividadeUmEmailModoRascunho
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailAtividadeUmEmailModoRascunho(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return new ValorLogicoEmailCrmUmEmailModoRascunho(getCampoInst()).getValor(pEntidade);
    }
}
