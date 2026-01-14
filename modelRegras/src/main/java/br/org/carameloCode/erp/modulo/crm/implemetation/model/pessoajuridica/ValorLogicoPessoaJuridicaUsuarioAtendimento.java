package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaUsuarioAtendimento;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.USUARIOATENDIMENTO)
public class ValorLogicoPessoaJuridicaUsuarioAtendimento
        extends
        ValorLogicoPessoaUsuarioAtendimento {

    public ValorLogicoPessoaJuridicaUsuarioAtendimento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public PessoaJuridica getPessoaJuridica() {
        return (PessoaJuridica) getCampoInst().getObjetoDoAtributo();
    }
}
