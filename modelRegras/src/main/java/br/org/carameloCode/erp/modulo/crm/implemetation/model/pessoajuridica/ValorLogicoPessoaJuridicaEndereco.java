package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaEndereco;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.ENDERECO)
public class ValorLogicoPessoaJuridicaEndereco
        extends
        ValorLogicoPessoaEndereco {

    public ValorLogicoPessoaJuridicaEndereco(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private PessoaJuridica getPessoaJuridica() {
        return (PessoaJuridica) getCampoInst().getObjetoRaizDoAtributo();
    }
}
