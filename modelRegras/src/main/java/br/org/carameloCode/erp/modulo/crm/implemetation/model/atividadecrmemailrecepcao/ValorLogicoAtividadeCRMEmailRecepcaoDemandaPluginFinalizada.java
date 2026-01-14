package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrmemailrecepcao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tiposEspeciais.AtividadeCRMEmailRecepcao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmemailrecepcao.ValorLogicoAtividadeCRMEmailRecepcao;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmemailrecepcao.ValoresLogicosAtividadeCRMEmailRecepcao;

@ValorLogicoAtividadeCRMEmailRecepcao(calculo = ValoresLogicosAtividadeCRMEmailRecepcao.DEMANDAPLUGINFINALIZADA)
public class ValorLogicoAtividadeCRMEmailRecepcaoDemandaPluginFinalizada
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMEmailRecepcaoDemandaPluginFinalizada(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return null;
    }

    public AtividadeCRMEmailRecepcao getAticidade() {
        return (AtividadeCRMEmailRecepcao) getCampoInst().getObjetoDoAtributo();
    }
}
