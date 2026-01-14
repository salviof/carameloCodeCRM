package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.ULTIMOSARQUIVOS)
public class ValorLogicoPessoaFisicaUltimosArquivos
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaFisicaUltimosArquivos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
