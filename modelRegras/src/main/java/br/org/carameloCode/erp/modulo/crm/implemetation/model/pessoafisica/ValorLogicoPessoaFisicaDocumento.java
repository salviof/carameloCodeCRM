package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaFisica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaDocumento;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.DOCUMENTO)
public class ValorLogicoPessoaFisicaDocumento
        extends
        ValorLogicoPessoaDocumento {

    public ValorLogicoPessoaFisicaDocumento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public PessoaFisica getPessoaFisica() {
        return (PessoaFisica) getCampoInst().getObjetoDoAtributo();
    }
}
