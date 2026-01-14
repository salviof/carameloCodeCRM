package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail.ValorLogicoEnvioEmailHtmlMailCampoParaJson;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.HTMLMAILCAMPOPARAJSON)
public class ValorLogicoEnvioEmailAtividadeHtmlMailCampoParaJson
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoEnvioEmailHtmlMailCampoParaJson logicapadrao;

    public ValorLogicoEnvioEmailAtividadeHtmlMailCampoParaJson(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
        logicapadrao = new ValorLogicoEnvioEmailHtmlMailCampoParaJson(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return logicapadrao.getValor(pEntidade);
    }

}
