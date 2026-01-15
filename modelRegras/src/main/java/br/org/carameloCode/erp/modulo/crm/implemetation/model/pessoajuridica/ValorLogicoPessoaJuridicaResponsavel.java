package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaResponsavel;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.RESPONSAVEL)
public class ValorLogicoPessoaJuridicaResponsavel
        extends
        ValorLogicoPessoaResponsavel {

    public ValorLogicoPessoaJuridicaResponsavel(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public PessoaJuridica getPessoaJuridica() {
        return (PessoaJuridica) getCampoInst().getObjetoDoAtributo();
    }

}
