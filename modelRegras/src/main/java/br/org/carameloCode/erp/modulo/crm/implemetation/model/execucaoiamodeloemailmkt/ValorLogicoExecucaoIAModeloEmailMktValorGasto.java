package br.org.carameloCode.erp.modulo.crm.implemetation.model.execucaoiamodeloemailmkt;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiamodeloemailmkt.ValorLogicoExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiamodeloemailmkt.ValoresLogicosExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia.ValorLogicoExecucaoIAValorGasto;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.execucaoiaatividade.ValorLogicoExecucaoIAAtividadeValorGasto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAModeloEmailMkt(calculo = ValoresLogicosExecucaoIAModeloEmailMkt.VALORGASTO)
public class ValorLogicoExecucaoIAModeloEmailMktValorGasto
        extends
        ValorLogicoExecucaoIAValorGasto {

    public ValorLogicoExecucaoIAModeloEmailMktValorGasto(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public ExecucaoIAModeloEmailMkt getExecucaoIAModeloEmailMkt() {
        return (ExecucaoIAModeloEmailMkt) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
