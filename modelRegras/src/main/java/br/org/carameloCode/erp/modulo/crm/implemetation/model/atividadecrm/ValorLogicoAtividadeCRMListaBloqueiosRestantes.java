package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoBloqueio.FabTipoBloqueio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.LISTABLOQUEIOSRESTANTES)
public class ValorLogicoAtividadeCRMListaBloqueiosRestantes
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMListaBloqueiosRestantes(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getAtiviade().getListaBloqueios() == null) {
            //      getAtiviade().setListaBloqueiosRestantes(CalculosAtividadeCRM.BLOQUEIOS_RESTANTES.getValor(this));

            List<FabTipoBloqueio> bloqueiosEncontrados = new ArrayList();

            for (FabTipoBloqueio tipoBloqueio : FabTipoBloqueio.class.getEnumConstants()) {
                if (!tipoBloqueio.bloqueioLiberado(getAtiviade())) {
                    bloqueiosEncontrados.add(tipoBloqueio);
                }
            }

            getAtiviade().setListaBloqueiosRestantes(bloqueiosEncontrados);
        }

        return getAtiviade().getListaBloqueios();
    }

    public AtividadeCRM getAtiviade() {
        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
