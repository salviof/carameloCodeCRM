package br.org.carameloCode.erp.modulo.crm.implemetation.model.execucaoiaatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiaatividade.ValorLogicoExecucaoIAAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.execucaoiaatividade.ValoresLogicosExecucaoIAAtividade;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAAtividade;
import br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia.ValorLogicoExecucaoIADialogoJson;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAAtividade(calculo = ValoresLogicosExecucaoIAAtividade.DIALOGOJSON)
public class ValorLogicoExecucaoIAAtividadeDialogoJson
        extends
        ValorLogicoExecucaoIADialogoJson {

    public ValorLogicoExecucaoIAAtividadeDialogoJson(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public ExecucaoIAAtividade getExecucaoIAAtividade() {
        return (ExecucaoIAAtividade) getCampoInst().getObjetoRaizDoAtributo();
    }
}
