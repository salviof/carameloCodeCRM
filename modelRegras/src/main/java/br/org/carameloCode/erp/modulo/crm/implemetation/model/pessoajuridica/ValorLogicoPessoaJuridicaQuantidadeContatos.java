package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaQuantidadeContatos;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.QUANTIDADECONTATOS)
public class ValorLogicoPessoaJuridicaQuantidadeContatos
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoPessoaQuantidadeContatos valorLogicoContatos;

    public ValorLogicoPessoaJuridicaQuantidadeContatos(ItfCampoInstanciado pCampo) {
        super(pCampo);
        valorLogicoContatos = new ValorLogicoPessoaQuantidadeContatos(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return valorLogicoContatos.getValor();
    }
}
