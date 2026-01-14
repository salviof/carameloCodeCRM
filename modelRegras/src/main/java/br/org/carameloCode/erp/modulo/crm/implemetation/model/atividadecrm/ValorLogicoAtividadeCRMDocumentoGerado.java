package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.DOCUMENTOGERADO)
public class ValorLogicoAtividadeCRMDocumentoGerado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMDocumentoGerado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getAtiviade().setDocumentoGerado(!getAtiviade().getDocumentosGerados().isEmpty());
        return getAtiviade().isDocumentoGerado();
    }

    public AtividadeCRM getAtiviade() {
        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
