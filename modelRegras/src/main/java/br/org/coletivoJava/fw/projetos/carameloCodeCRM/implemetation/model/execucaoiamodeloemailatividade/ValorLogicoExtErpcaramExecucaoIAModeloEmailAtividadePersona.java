package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoiamodeloemailatividade;

import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiamodeloemailatividade.ValorLogicoExecucaoIAModeloEmailAtividade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiamodeloemailatividade.ValoresLogicosExecucaoIAModeloEmailAtividade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoia.ValorLogicoExtErpcaramExecucaoIAPersona;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAModeloEmailAtividade(calculo = ValoresLogicosExecucaoIAModeloEmailAtividade.PERSONA)
public class ValorLogicoExtErpcaramExecucaoIAModeloEmailAtividadePersona
        extends
        ValorLogicoExtErpcaramExecucaoIAPersona {

    public ValorLogicoExtErpcaramExecucaoIAModeloEmailAtividadePersona(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }
}
