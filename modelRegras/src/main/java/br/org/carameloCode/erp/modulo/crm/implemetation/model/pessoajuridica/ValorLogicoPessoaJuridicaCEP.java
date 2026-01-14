package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaCEP;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.CEP)
public class ValorLogicoPessoaJuridicaCEP extends ValorLogicoPessoaCEP {

    public ValorLogicoPessoaJuridicaCEP(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
