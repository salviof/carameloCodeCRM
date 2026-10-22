package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoiaatividade;

import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiaatividade.ValorLogicoExecucaoIAAtividade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiaatividade.ValoresLogicosExecucaoIAAtividade;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAAtividade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoia.ValorLogicoExtErpcaramExecucaoIAPersona;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAAtividade(calculo = ValoresLogicosExecucaoIAAtividade.PERSONA)
public class ValorLogicoExtErpcaramExecucaoIAAtividadePersona
        extends
        ValorLogicoExtErpcaramExecucaoIAPersona {

    public ValorLogicoExtErpcaramExecucaoIAAtividadePersona(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getExecucao().setPersona(getExecucao().getAtividade().getTipoAtividade().getPersona());
        return getExecucao().getPersona();
    }

    public ExecucaoIAAtividade getExecucao() {
        return (ExecucaoIAAtividade) getCampoInst().getObjetoRaizDoAtributo();
    }

}
