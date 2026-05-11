package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValorLogicoTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValoresLogicosTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.notificacao.implemetation.model.tiponotificacao.ValorLogicoTipoNotificacaoEntidadesDisponiveis;

@ValorLogicoTiponotificacaoCRM(calculo = ValoresLogicosTiponotificacaoCRM.ENTIDADESDISPONIVEIS)
public class ValorLogicoTiponotificacaoCRMEntidadesDisponiveis
        extends
        ValorLogicoTipoNotificacaoEntidadesDisponiveis {

    public ValorLogicoTiponotificacaoCRMEntidadesDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
