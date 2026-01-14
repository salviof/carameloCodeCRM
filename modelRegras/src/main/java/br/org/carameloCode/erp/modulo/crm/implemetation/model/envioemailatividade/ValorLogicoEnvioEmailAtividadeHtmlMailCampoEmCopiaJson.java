package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail.ValorLogicoEnvioEmailHtmlMailCampoEmCopiaJson;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.HTMLMAILCAMPOEMCOPIAJSON)
public class ValorLogicoEnvioEmailAtividadeHtmlMailCampoEmCopiaJson
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoEnvioEmailHtmlMailCampoEmCopiaJson logicaPadrao;

    public ValorLogicoEnvioEmailAtividadeHtmlMailCampoEmCopiaJson(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
        logicaPadrao = new ValorLogicoEnvioEmailHtmlMailCampoEmCopiaJson(getCampoInst());
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return logicaPadrao.getValor(pEntidade);
    }

}
