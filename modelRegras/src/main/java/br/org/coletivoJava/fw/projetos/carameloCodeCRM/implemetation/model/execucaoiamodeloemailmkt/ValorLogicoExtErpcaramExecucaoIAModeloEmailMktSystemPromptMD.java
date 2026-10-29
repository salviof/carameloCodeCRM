package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoiamodeloemailmkt;

import br.org.carameloCode.erp.modulo.ia.contexto.instrucaoia.CPInstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIAPlaceHolders;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiamodeloemailmkt.ValorLogicoExecucaoIAModeloEmailMkt;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiamodeloemailmkt.ValoresLogicosExecucaoIAModeloEmailMkt;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoia.ValorLogicoExtErpcaramExecucaoIASystemPromptMD;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAModeloEmailMkt(calculo = ValoresLogicosExecucaoIAModeloEmailMkt.SYSTEMPROMPTMD)
public class ValorLogicoExtErpcaramExecucaoIAModeloEmailMktSystemPromptMD
        extends
        ValorLogicoExtErpcaramExecucaoIASystemPromptMD {

    public ValorLogicoExtErpcaramExecucaoIAModeloEmailMktSystemPromptMD(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public InstrucaoIAPlaceHolders gerarPlaceHolder() {
        if (getExecucaoIA().getTipoExecucao().isHabilitarPLaceHolders()) {
            InstrucaoIAPlaceHolders placeHolders = new InstrucaoIAPlaceHolders();

            placeHolders.setNome("Place Holders");
            placeHolders.getCPinst(CPInstrucaoIA.conteudo).getValor();
            return placeHolders;
        }
        return null;

    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }
}
