package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaFisica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaQuantidadeAnexos;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.QUANTIDADEANEXOS)
public class ValorLogicoPessoaFisicaQuantidadeAnexos
        extends
        ValorLogicoPessoaQuantidadeAnexos {

    public ValorLogicoPessoaFisicaQuantidadeAnexos(ItfCampoInstanciado pCampo) {
        super(pCampo);

    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }

    public PessoaFisica getPessoa() {
        return (PessoaFisica) getCampoInst().getObjetoDoAtributo();
    }
}
