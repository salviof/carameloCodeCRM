package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaAtivo;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.ATIVO)
public class ValorLogicoPessoaJuridicaAtivo extends ValorLogicoCalculoGenerico {

    private final ValorLogicoPessoaAtivo valorLogico;

    public ValorLogicoPessoaJuridicaAtivo(ItfCampoInstanciado pCampo) {
        super(pCampo);
        valorLogico = new ValorLogicoPessoaAtivo(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return valorLogico.getValor(pEntidade);
    }

}
