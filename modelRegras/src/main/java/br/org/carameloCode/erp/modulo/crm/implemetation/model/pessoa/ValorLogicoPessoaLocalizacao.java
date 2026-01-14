package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.LOCALIZACAO)
public class ValorLogicoPessoaLocalizacao extends ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaLocalizacao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
