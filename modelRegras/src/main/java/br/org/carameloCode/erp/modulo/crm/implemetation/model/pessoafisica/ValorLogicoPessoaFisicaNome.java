package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaNome;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.NOME)
public class ValorLogicoPessoaFisicaNome extends ValorLogicoPessoaNome {

    public ValorLogicoPessoaFisicaNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
