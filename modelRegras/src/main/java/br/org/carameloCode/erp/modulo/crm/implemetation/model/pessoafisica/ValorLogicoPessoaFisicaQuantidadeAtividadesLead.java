package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaQuantidadeAtividadesLead;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.QUANTIDADEATIVIDADESLEAD)
public class ValorLogicoPessoaFisicaQuantidadeAtividadesLead
        extends
        ValorLogicoPessoaQuantidadeAtividadesLead {

    public ValorLogicoPessoaFisicaQuantidadeAtividadesLead(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
