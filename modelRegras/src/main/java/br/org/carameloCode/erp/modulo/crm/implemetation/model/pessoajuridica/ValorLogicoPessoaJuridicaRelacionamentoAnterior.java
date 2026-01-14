package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaRelacionamentoAnterior;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.RELACIONAMENTOANTERIOR)
public class ValorLogicoPessoaJuridicaRelacionamentoAnterior
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaJuridicaRelacionamentoAnterior(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return new ValorLogicoPessoaRelacionamentoAnterior(getCampoInst()).getValor(pEntidade);
    }

}
