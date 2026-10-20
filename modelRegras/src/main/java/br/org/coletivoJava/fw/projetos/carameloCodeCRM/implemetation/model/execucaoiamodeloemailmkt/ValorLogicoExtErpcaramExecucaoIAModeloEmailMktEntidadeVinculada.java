package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoiamodeloemailmkt;

import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiamodeloemailmkt.ValorLogicoExecucaoIAModeloEmailMkt;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiamodeloemailmkt.ValoresLogicosExecucaoIAModeloEmailMkt;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoia.ValorLogicoExtErpcaramExecucaoIAEntidadeVinculada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAModeloEmailMkt(calculo = ValoresLogicosExecucaoIAModeloEmailMkt.ENTIDADEVINCULADA)
public class ValorLogicoExtErpcaramExecucaoIAModeloEmailMktEntidadeVinculada
        extends
        ValorLogicoExtErpcaramExecucaoIAEntidadeVinculada {

    public ValorLogicoExtErpcaramExecucaoIAModeloEmailMktEntidadeVinculada(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }
}
