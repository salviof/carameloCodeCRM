package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaCEP;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.CEP)
public class ValorLogicoPessoaFisicaCEP extends ValorLogicoPessoaCEP {

    public ValorLogicoPessoaFisicaCEP(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
