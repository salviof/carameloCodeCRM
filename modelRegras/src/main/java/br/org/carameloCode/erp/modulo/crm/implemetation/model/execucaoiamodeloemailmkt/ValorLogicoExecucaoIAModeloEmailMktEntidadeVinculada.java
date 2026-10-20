package br.org.carameloCode.erp.modulo.crm.implemetation.model.execucaoiamodeloemailmkt;

import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiamodeloemailmkt.ValorLogicoExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiamodeloemailmkt.ValoresLogicosExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia.ValorLogicoExecucaoIAEntidadeVinculada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAModeloEmailMkt(calculo = ValoresLogicosExecucaoIAModeloEmailMkt.ENTIDADEVINCULADA)
public class ValorLogicoExecucaoIAModeloEmailMktEntidadeVinculada
        extends
        ValorLogicoExecucaoIAEntidadeVinculada {

    public ValorLogicoExecucaoIAModeloEmailMktEntidadeVinculada(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public ExecucaoIAModeloEmailMkt getExecucaoIAModeloEmailMkt() {
        return (ExecucaoIAModeloEmailMkt) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
