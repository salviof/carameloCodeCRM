package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaQuantidadeAtividades;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.QUANTIDADEATIVIDADES)
public class ValorLogicoPessoaJuridicaQuantidadeAtividades
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoPessoaQuantidadeAtividades valorLogicoAtividades;

    public ValorLogicoPessoaJuridicaQuantidadeAtividades(ItfCampoInstanciado pCampo) {
        super(pCampo);
        valorLogicoAtividades = new ValorLogicoPessoaQuantidadeAtividades(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return valorLogicoAtividades.getValor();
    }
}
