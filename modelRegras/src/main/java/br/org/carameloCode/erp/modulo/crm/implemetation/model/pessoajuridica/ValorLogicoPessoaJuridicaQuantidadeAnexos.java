package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaQuantidadeAnexos;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.QUANTIDADEANEXOS)
public class ValorLogicoPessoaJuridicaQuantidadeAnexos
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoPessoaQuantidadeAnexos valorLogicoAnexos;

    public ValorLogicoPessoaJuridicaQuantidadeAnexos(ItfCampoInstanciado pCampo) {
        super(pCampo);
        valorLogicoAnexos = new ValorLogicoPessoaQuantidadeAnexos(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return valorLogicoAnexos.getValor();
    }
}
