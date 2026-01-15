package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrmemailrecepcao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.AtividadeCRMEmailRecepcao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmemailrecepcao.ValorLogicoAtividadeCRMEmailRecepcao;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmemailrecepcao.ValoresLogicosAtividadeCRMEmailRecepcao;

@ValorLogicoAtividadeCRMEmailRecepcao(calculo = ValoresLogicosAtividadeCRMEmailRecepcao.RELACIONAMENTOGERADO)
public class ValorLogicoAtividadeCRMEmailRecepcaoRelacionamentoGerado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMEmailRecepcaoRelacionamentoGerado(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getAticidade().getRelacionamentoGerado();
    }

    public AtividadeCRMEmailRecepcao getAticidade() {
        return (AtividadeCRMEmailRecepcao) getCampoInst().getObjetoDoAtributo();
    }
}
