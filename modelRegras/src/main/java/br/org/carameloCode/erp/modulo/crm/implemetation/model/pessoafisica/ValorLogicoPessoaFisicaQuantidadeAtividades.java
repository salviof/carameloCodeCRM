package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaQuantidadeAtividades;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.QUANTIDADEATIVIDADES)
public class ValorLogicoPessoaFisicaQuantidadeAtividades
        extends
        ValorLogicoPessoaQuantidadeAtividades {

    public ValorLogicoPessoaFisicaQuantidadeAtividades(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
