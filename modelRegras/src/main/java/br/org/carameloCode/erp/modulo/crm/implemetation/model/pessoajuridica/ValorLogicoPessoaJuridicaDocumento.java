package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaDocumento;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.DOCUMENTO)
public class ValorLogicoPessoaJuridicaDocumento
        extends
        ValorLogicoPessoaDocumento {

    public ValorLogicoPessoaJuridicaDocumento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public PessoaJuridica getPessoaFisica() {
        return (PessoaJuridica) getCampoInst().getObjetoDoAtributo();
    }
}
