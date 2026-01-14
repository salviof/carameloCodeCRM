package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaAtendenteLogadoResponsavelPrincipal;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.ATENDENTELOGADORESPONSAVELPRINCIPAL)
public class ValorLogicoPessoaFisicaAtendenteLogadoResponsavelPrincipal
        extends
        ValorLogicoPessoaAtendenteLogadoResponsavelPrincipal {

    public ValorLogicoPessoaFisicaAtendenteLogadoResponsavelPrincipal(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
