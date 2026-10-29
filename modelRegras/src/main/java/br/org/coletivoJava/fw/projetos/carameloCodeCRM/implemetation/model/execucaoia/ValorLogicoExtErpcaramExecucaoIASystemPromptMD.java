package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoia;

import br.org.carameloCode.erp.modulo.ia.contexto.instrucaoia.CPInstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia.ValorLogicoExecucaoIASystemPromptMD;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoia.ValorLogicoExecucaoIA;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoia.ValoresLogicosExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIAPlaceHolders;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIA(calculo = ValoresLogicosExecucaoIA.SYSTEMPROMPTMD)
public class ValorLogicoExtErpcaramExecucaoIASystemPromptMD
        extends
        ValorLogicoExecucaoIASystemPromptMD {

    public ValorLogicoExtErpcaramExecucaoIASystemPromptMD(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }
}
