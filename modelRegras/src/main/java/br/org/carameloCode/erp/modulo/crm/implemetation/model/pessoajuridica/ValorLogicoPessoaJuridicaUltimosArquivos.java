package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaUltimosArquivos;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.ULTIMOSARQUIVOS)
public class ValorLogicoPessoaJuridicaUltimosArquivos
        extends
        ValorLogicoPessoaUltimosArquivos {

    public ValorLogicoPessoaJuridicaUltimosArquivos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
