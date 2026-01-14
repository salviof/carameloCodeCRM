package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaUsuarioResponsavel;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.USUARIORESPONSAVEL)
public class ValorLogicoPessoaJuridicaUsuarioResponsavel
        extends
        ValorLogicoPessoaUsuarioResponsavel {

    public ValorLogicoPessoaJuridicaUsuarioResponsavel(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
