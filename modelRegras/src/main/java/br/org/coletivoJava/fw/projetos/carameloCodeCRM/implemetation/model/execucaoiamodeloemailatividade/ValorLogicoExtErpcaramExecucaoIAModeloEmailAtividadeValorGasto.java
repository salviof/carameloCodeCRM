package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoiamodeloemailatividade;

import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiamodeloemailatividade.ValorLogicoExecucaoIAModeloEmailAtividade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiamodeloemailatividade.ValoresLogicosExecucaoIAModeloEmailAtividade;
import br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia.ValorLogicoExecucaoIAValorGasto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAModeloEmailAtividade(calculo = ValoresLogicosExecucaoIAModeloEmailAtividade.VALORGASTO)
public class ValorLogicoExtErpcaramExecucaoIAModeloEmailAtividadeValorGasto
        extends
        ValorLogicoExecucaoIAValorGasto {

    public ValorLogicoExtErpcaramExecucaoIAModeloEmailAtividadeValorGasto(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }
}
