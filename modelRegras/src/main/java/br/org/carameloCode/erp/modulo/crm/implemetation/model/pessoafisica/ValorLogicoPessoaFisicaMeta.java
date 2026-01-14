package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaMeta;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.META)
public class ValorLogicoPessoaFisicaMeta extends ValorLogicoCalculoGenerico {

    private final ValorLogicoPessoaMeta valorLogico;

    public ValorLogicoPessoaFisicaMeta(ItfCampoInstanciado pCampo) {
        super(pCampo);
        valorLogico = new ValorLogicoPessoaMeta(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return valorLogico.getValor(pEntidade);
    }

}
