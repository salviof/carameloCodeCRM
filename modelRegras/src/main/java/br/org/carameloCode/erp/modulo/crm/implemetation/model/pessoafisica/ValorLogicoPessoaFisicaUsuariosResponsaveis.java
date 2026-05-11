package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaFisica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.USUARIOSRESPONSAVEIS)
public class ValorLogicoPessoaFisicaUsuariosResponsaveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaFisicaUsuariosResponsaveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        return getPessoaFisica().getUsuariosResponsaveis();
    }

    public PessoaFisica getPessoaFisica() {
        return (PessoaFisica) getCampoInst().getObjetoRaizDoAtributo();
    }
}
