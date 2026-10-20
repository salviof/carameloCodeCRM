package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoiamodeloemailmkt;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiamodeloemailmkt.ValorLogicoExecucaoIAModeloEmailMkt;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiamodeloemailmkt.ValoresLogicosExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailMkt;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoia.ValorLogicoExtErpcaramExecucaoIAPersona;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAModeloEmailMkt(calculo = ValoresLogicosExecucaoIAModeloEmailMkt.PERSONA)
public class ValorLogicoExtErpcaramExecucaoIAModeloEmailMktPersona
        extends
        ValorLogicoExtErpcaramExecucaoIAPersona {

    public ValorLogicoExtErpcaramExecucaoIAModeloEmailMktPersona(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getExecucao().setPersona(getExecucao().getModeloEmailMarketing().getPersonaCoautoria());
        return getExecucao().getPersona();
    }

    public ExecucaoIAModeloEmailMkt getExecucao() {
        return (ExecucaoIAModeloEmailMkt) getCampoInst().getObjetoRaizDoAtributo();
    }

}
