package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaContatoPrincipal;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.CONTATOPRINCIPAL)
public class ValorLogicoPessoaFisicaContatoPrincipal
        extends
        ValorLogicoPessoaContatoPrincipal {

    public ValorLogicoPessoaFisicaContatoPrincipal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
