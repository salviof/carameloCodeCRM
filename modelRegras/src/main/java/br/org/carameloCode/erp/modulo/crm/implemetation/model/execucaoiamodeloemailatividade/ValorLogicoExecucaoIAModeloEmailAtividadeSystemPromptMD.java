package br.org.carameloCode.erp.modulo.crm.implemetation.model.execucaoiamodeloemailatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiamodeloemailatividade.ValorLogicoExecucaoIAModeloEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiamodeloemailatividade.ValoresLogicosExecucaoIAModeloEmailAtividade;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailAtividade;
import br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia.ValorLogicoExecucaoIASystemPromptMD;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAModeloEmailAtividade(calculo = ValoresLogicosExecucaoIAModeloEmailAtividade.SYSTEMPROMPTMD)
public class ValorLogicoExecucaoIAModeloEmailAtividadeSystemPromptMD
        extends
        ValorLogicoExecucaoIASystemPromptMD {

    public ValorLogicoExecucaoIAModeloEmailAtividadeSystemPromptMD(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public ExecucaoIAModeloEmailAtividade getExecucaoIAModeloEmailAtividade() {
        return (ExecucaoIAModeloEmailAtividade) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
