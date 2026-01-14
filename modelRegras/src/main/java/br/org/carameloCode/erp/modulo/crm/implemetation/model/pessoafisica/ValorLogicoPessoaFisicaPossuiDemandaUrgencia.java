package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaPossuiDemandaUrgencia;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.POSSUIDEMANDAURGENCIA)
public class ValorLogicoPessoaFisicaPossuiDemandaUrgencia
        extends
        ValorLogicoPessoaPossuiDemandaUrgencia {

    public ValorLogicoPessoaFisicaPossuiDemandaUrgencia(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
