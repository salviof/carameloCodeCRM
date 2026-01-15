package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaFisica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.UMPERFILPUBLICO)
public class ValorLogicoPessoaFisicaUmPerfilPublico
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaFisicaUmPerfilPublico(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return ((PessoaFisica) getCampoInst().getObjetoDoAtributo()).isUmPerfilPublico();
    }

}
