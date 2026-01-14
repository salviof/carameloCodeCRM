package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaQuantidadeEmailsTrocados;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.QUANTIDADEEMAILSTROCADOS)
public class ValorLogicoPessoaJuridicaQuantidadeEmailsTrocados
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoPessoaQuantidadeEmailsTrocados valorLogicoQuantidadeEmailsTrocados;

    public ValorLogicoPessoaJuridicaQuantidadeEmailsTrocados(ItfCampoInstanciado pCampo) {
        super(pCampo);
        valorLogicoQuantidadeEmailsTrocados = new ValorLogicoPessoaQuantidadeEmailsTrocados(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return valorLogicoQuantidadeEmailsTrocados.getValor();
    }
}
