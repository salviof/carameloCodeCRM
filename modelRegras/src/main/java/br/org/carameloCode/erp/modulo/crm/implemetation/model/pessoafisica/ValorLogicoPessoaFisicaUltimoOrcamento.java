package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaUltimoOrcamento;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.ULTIMOORCAMENTO)
public class ValorLogicoPessoaFisicaUltimoOrcamento
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaFisicaUltimoOrcamento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return new ValorLogicoPessoaUltimoOrcamento(getCampoInst()).getValor(pEntidade);
    }

}
