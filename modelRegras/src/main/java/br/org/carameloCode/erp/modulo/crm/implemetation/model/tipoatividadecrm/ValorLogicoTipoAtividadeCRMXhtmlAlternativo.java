package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValorLogicoTipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValoresLogicosTipoAtividadeCRM;

@ValorLogicoTipoAtividadeCRM(calculo = ValoresLogicosTipoAtividadeCRM.XHTMLALTERNATIVO)
public class ValorLogicoTipoAtividadeCRMXhtmlAlternativo
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoAtividadeCRMXhtmlAlternativo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getTipoAtividadeCRM().getXhtmlAlternativo();
    }

    public TipoAtividadeCRM getTipoAtividadeCRM() {
        return (TipoAtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }

}
