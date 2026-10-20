package br.org.carameloCode.erp.modulo.crm.implemetation.model.execucaoiaatividade;

import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiaatividade.ValorLogicoExecucaoIAAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiaatividade.ValoresLogicosExecucaoIAAtividade;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAAtividade;
import br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia.ValorLogicoExecucaoIAEntidadeVinculada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAAtividade(calculo = ValoresLogicosExecucaoIAAtividade.ENTIDADEVINCULADA)
public class ValorLogicoExecucaoIAAtividadeEntidadeVinculada
        extends
        ValorLogicoExecucaoIAEntidadeVinculada {

    public ValorLogicoExecucaoIAAtividadeEntidadeVinculada(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public ExecucaoIAAtividade getExecucaoIAAtividade() {
        return (ExecucaoIAAtividade) getCampoInst().getObjetoRaizDoAtributo();
    }

}
