package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrmemailenvio;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais.AtividadeCRMEmailEnvio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmemailenvio.ValorLogicoAtividadeCRMEmailEnvio;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmemailenvio.ValoresLogicosAtividadeCRMEmailEnvio;

@ValorLogicoAtividadeCRMEmailEnvio(calculo = ValoresLogicosAtividadeCRMEmailEnvio.DADOSREVISADOS)
public class ValorLogicoAtividadeCRMEmailEnvioDadosRevisados
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMEmailEnvioDadosRevisados(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public AtividadeCRMEmailEnvio getAticidade() {
        return (AtividadeCRMEmailEnvio) getCampoInst().getObjetoDoAtributo();
    }
}
