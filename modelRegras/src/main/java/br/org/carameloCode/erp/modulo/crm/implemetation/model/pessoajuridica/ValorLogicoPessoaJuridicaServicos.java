package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaServicos;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.SERVICOS)
public class ValorLogicoPessoaJuridicaServicos
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaJuridicaServicos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return new ValorLogicoPessoaServicos(getCampoInst()).getValor(pEntidade);
    }

    public PessoaJuridica getPessoaJuridica() {
        return (PessoaJuridica) getCampoInst().getObjetoDoAtributo();
    }

}
