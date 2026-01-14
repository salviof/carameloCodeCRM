package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail.ValorLogicoEnvioEmailArquivos;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.ARQUIVOS)
public class ValorLogicoEnvioEmailAtividadeArquivos
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoEnvioEmailArquivos logicaPadrao;

    public ValorLogicoEnvioEmailAtividadeArquivos(ItfCampoInstanciado pCampo) {
        super(pCampo);
        logicaPadrao = new ValorLogicoEnvioEmailArquivos(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return logicaPadrao.getValor(pEntidade);
    }

}
