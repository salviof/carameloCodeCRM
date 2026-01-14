package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.DEMANDAPLUGINFINALIZADA)
public class ValorLogicoAtividadeCRMDemandaPluginFinalizada
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMDemandaPluginFinalizada(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getAtividade().getTipoAtividade().getAcaoDePLuginVunculado() == null) {
            getAtividade().setDemandaPluginFinalizada(true);
        } else {
            getAtividade().setDemandaPluginFinalizada(getAtividade().isDemandaPluginFinalizada());
        }

        return getAtividade().isDemandaPluginFinalizada();
    }

    public AtividadeCRM getAtividade() {
        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
