package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadorPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadoresPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValidacaoPessoaResponsavel;

@ValidadorPessoaFisica(validador = ValidadoresPessoaFisica.RESPONSAVEL)
public class ValidacaoPessoaFisicaResponsavel extends ValidacaoPessoaResponsavel {

    public ValidacaoPessoaFisicaResponsavel(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
