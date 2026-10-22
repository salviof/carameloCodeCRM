package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoiaatividade;

import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiaatividade.ValorLogicoExecucaoIAAtividade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiaatividade.ValoresLogicosExecucaoIAAtividade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoia.ValorLogicoExtErpcaramExecucaoIAEntidadeVinculada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAAtividade(calculo = ValoresLogicosExecucaoIAAtividade.ENTIDADEVINCULADA)
public class ValorLogicoExtErpcaramExecucaoIAAtividadeEntidadeVinculada
        extends
        ValorLogicoExtErpcaramExecucaoIAEntidadeVinculada {

    public ValorLogicoExtErpcaramExecucaoIAAtividadeEntidadeVinculada(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }
}
