package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValorLogicoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValoresLogicosPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaFisica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValorLogicoPessoaEndereco;

@ValorLogicoPessoaFisica(calculo = ValoresLogicosPessoaFisica.ENDERECO)
public class ValorLogicoPessoaFisicaEndereco extends ValorLogicoPessoaEndereco {

    public ValorLogicoPessoaFisicaEndereco(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private PessoaFisica getPessoaFisica() {
        return (PessoaFisica) getCampoInst().getObjetoRaizDoAtributo();
    }
}
