package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.FORMULARIOEXECUCAO)
public class ValorLogicoAtividadeCRMFormularioExecucao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMFormularioExecucao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        String xhtml = ((ItfAcaoFormulario) getAtividade().getCPinst(CPAtividadeCRM.acaoformularioexecucao).getValor()).getXhtml();
        getAtividade().setFormularioExecucao(xhtml);
        return getAtividade().getFormularioExecucao();

    }

    public AtividadeCRM getAtividade() {

        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
