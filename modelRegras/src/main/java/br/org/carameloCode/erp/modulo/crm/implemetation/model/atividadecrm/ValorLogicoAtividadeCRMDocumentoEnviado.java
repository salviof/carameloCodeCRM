package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.DOCUMENTOENVIADO)
public class ValorLogicoAtividadeCRMDocumentoEnviado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMDocumentoEnviado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getAtividade().getEmailVinculado() == null) {
            getAtividade().setDocumentoEnviado(false);

        } else {
            getAtividade().setDocumentoEnviado(getAtividade().getEmailComoEnvio().isFoiEnviado());
        }
        return getAtividade().isDocumentoEnviado();
    }

    public AtividadeCRM getAtividade() {

        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
