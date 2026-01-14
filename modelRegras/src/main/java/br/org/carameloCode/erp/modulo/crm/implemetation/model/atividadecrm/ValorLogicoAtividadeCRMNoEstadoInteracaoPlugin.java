package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.FabStatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoBloqueio.FabTipoBloqueio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.NOESTADOINTERACAOPLUGIN)
public class ValorLogicoAtividadeCRMNoEstadoInteracaoPlugin
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMNoEstadoInteracaoPlugin(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        FabTipoBloqueio bloqueioAtual = (FabTipoBloqueio) getAtividade().getCPinst(CPAtividadeCRM.tipobloqueio).getValor();
        if (bloqueioAtual == null) {
            getAtividade().setNoEstadoInteracaoPlugin(false);
        } else {
            boolean estadoInteracao = FabTipoBloqueio.PLUGIN_DEMANDA.equals(bloqueioAtual);
            if (!(getAtividade().getStatusAtividade().equals(FabStatusAtividade.EM_ANDAMENTO.getRegistro())
                    || getAtividade().getStatusAtividade().equals(FabStatusAtividade.AGENDADO.getRegistro()))) {
                estadoInteracao = false;
            }
            getAtividade().setNoEstadoInteracaoPlugin(estadoInteracao);
        }

        return getAtividade().isNoEstadoInteracaoPlugin();
    }

    public AtividadeCRM getAtividade() {

        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
