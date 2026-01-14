package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaEstiloVisualizacao;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.ESTILOVISUALIZACAO)
public class ValorLogicoPessoaJuridicaEstiloVisualizacao
        extends
        ValorLogicoPessoaEstiloVisualizacao {

    public ValorLogicoPessoaJuridicaEstiloVisualizacao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public PessoaJuridica getProspecto() {

        return (PessoaJuridica) getCampoInst().getObjetoDoAtributo();
    }
}
