package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.LOCALIZACAO)
public class ValorLogicoPessoaJuridicaLocalizacao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaJuridicaLocalizacao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        return getPessoaJuridica().getLocalizacao();
    }

    public PessoaJuridica getPessoaJuridica() {
        return (PessoaJuridica) getCampoInst().getObjetoDoAtributo();
    }
}
