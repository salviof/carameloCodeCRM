package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail.ValorLogicoEnvioEmailConteudoHtmlProcessado;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.CONTEUDOHTMLPROCESSADO)
public class ValorLogicoEnvioEmailAtividadeConteudoHtmlProcessado
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoEnvioEmailConteudoHtmlProcessado logicaPadrao;

    public ValorLogicoEnvioEmailAtividadeConteudoHtmlProcessado(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
        logicaPadrao = new ValorLogicoEnvioEmailConteudoHtmlProcessado(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return logicaPadrao.getValor(pEntidade);
    }

}
