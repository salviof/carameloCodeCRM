package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatividadecrm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValorLogicoTipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValoresLogicosTipoAtividadeCRM;

@ValorLogicoTipoAtividadeCRM(calculo = ValoresLogicosTipoAtividadeCRM.ACAOFORMULARIOEXECUCAO)
public class ValorLogicoTipoAtividadeCRMAcaoFormularioExecucao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoAtividadeCRMAcaoFormularioExecucao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
