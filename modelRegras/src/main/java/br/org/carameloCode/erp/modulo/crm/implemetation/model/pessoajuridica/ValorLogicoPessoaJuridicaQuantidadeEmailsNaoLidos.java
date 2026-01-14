package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaQuantidadeEmailsNaoLidos;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.QUANTIDADEEMAILSNAOLIDOS)
public class ValorLogicoPessoaJuridicaQuantidadeEmailsNaoLidos
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoPessoaQuantidadeEmailsNaoLidos valorLogicoEmailsNaoLidos;

    public ValorLogicoPessoaJuridicaQuantidadeEmailsNaoLidos(ItfCampoInstanciado pCampo) {
        super(pCampo);
        valorLogicoEmailsNaoLidos = new ValorLogicoPessoaQuantidadeEmailsNaoLidos(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return valorLogicoEmailsNaoLidos.getValor();
    }
}
