package br.org.carameloCode.erp.modulo.crm.implemetation.model.execucaoiamodeloemailmkt;

import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiamodeloemailmkt.ValorLogicoExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiamodeloemailmkt.ValoresLogicosExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia.ValorLogicoExecucaoIADialogoJson;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAModeloEmailMkt(calculo = ValoresLogicosExecucaoIAModeloEmailMkt.DIALOGOJSON)
public class ValorLogicoExecucaoIAModeloEmailMktDialogoJson
        extends
        ValorLogicoExecucaoIADialogoJson {

    public ValorLogicoExecucaoIAModeloEmailMktDialogoJson(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public ExecucaoIAModeloEmailMkt getExecucaoIAModeloEmailMkt() {
        return (ExecucaoIAModeloEmailMkt) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
