package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoiamodeloemailatividade;

import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiamodeloemailatividade.ValorLogicoExecucaoIAModeloEmailAtividade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiamodeloemailatividade.ValoresLogicosExecucaoIAModeloEmailAtividade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoia.ValorLogicoExtErpcaramExecucaoIAEntidadeVinculada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAModeloEmailAtividade(calculo = ValoresLogicosExecucaoIAModeloEmailAtividade.ENTIDADEVINCULADA)
public class ValorLogicoExtErpcaramExecucaoIAModeloEmailAtividadeEntidadeVinculada
        extends
        ValorLogicoExtErpcaramExecucaoIAEntidadeVinculada {

    public ValorLogicoExtErpcaramExecucaoIAModeloEmailAtividadeEntidadeVinculada(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }
}
