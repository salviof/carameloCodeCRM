package br.org.carameloCode.erp.modulo.crm.implemetation.model.execucaoiamodeloemailatividade;

import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiamodeloemailatividade.ValorLogicoExecucaoIAModeloEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiamodeloemailatividade.ValoresLogicosExecucaoIAModeloEmailAtividade;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailAtividade;
import br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia.ValorLogicoExecucaoIAEntidadeVinculada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAModeloEmailAtividade(calculo = ValoresLogicosExecucaoIAModeloEmailAtividade.ENTIDADEVINCULADA)
public class ValorLogicoExecucaoIAModeloEmailAtividadeEntidadeVinculada
        extends
        ValorLogicoExecucaoIAEntidadeVinculada {

    public ValorLogicoExecucaoIAModeloEmailAtividadeEntidadeVinculada(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public ExecucaoIAModeloEmailAtividade getExecucaoIAModeloEmailAtividade() {
        return (ExecucaoIAModeloEmailAtividade) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
