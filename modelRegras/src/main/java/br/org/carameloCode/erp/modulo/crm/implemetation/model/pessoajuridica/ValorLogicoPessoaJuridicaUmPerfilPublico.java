package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.UMPERFILPUBLICO)
public class ValorLogicoPessoaJuridicaUmPerfilPublico
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaJuridicaUmPerfilPublico(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return ((PessoaJuridica) getCampoInst().getObjetoDoAtributo()).isUmPerfilPublico();
    }

}
