package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaUsuariosRespDisponiveis;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.USUARIOSRESPDISPONIVEIS)
public class ValorLogicoPessoaFisicaUsuariosRespDisponiveis
        extends
        ValorLogicoPessoaUsuariosRespDisponiveis {

    public ValorLogicoPessoaFisicaUsuariosRespDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
