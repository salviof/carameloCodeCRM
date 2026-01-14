package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaPertenceAoUsuarioLogado;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.PERTENCEAOUSUARIOLOGADO)
public class ValorLogicoPessoaFisicaPertenceAoUsuarioLogado
        extends
        ValorLogicoPessoaPertenceAoUsuarioLogado {

    public ValorLogicoPessoaFisicaPertenceAoUsuarioLogado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
