package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaUsuariosResponsaveis;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.USUARIOSRESPONSAVEIS)
public class ValorLogicoPessoaJuridicaUsuariosResponsaveis
        extends
        ValorLogicoPessoaUsuariosResponsaveis {

    public ValorLogicoPessoaJuridicaUsuariosResponsaveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public PessoaJuridica getPessoaJuridica() {
        return (PessoaJuridica) getCampoInst().getObjetoRaizDoAtributo();
    }
}
