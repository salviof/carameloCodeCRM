package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaIntegracoesLink;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.INTEGRACOESLINK)
public class ValorLogicoPessoaFisicaIntegracoesLink
        extends
        ValorLogicoPessoaIntegracoesLink {

    public ValorLogicoPessoaFisicaIntegracoesLink(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
