package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoBloqueio.FabTipoBloqueio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.TIPOBLOQUEIO)
public class ValorLogicoAtividadeCRMTipoBloqueio
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMTipoBloqueio(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        List<FabTipoBloqueio> bloq = (List<FabTipoBloqueio>) getAtividade().getCPinst(CPAtividadeCRM.listabloqueiosrestantes).getValor();

        if (bloq.isEmpty()) {
            getAtividade().setTipoBloqueio(null);
        } else {
            FabTipoBloqueio tipobloqueioAtual = bloq.get(0);
            getAtividade().setTipoBloqueio(tipobloqueioAtual);

        }
        return getAtividade().getTipoBloqueio();
    }

    public AtividadeCRM getAtividade() {
        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }

}
