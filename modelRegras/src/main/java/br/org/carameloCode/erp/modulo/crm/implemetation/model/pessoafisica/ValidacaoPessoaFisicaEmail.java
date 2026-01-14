package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadorPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadoresPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValidacaoPessoaEmail;

@ValidadorPessoaFisica(validador = ValidadoresPessoaFisica.EMAIL)
public class ValidacaoPessoaFisicaEmail extends ValidacaoPessoaEmail {

    public ValidacaoPessoaFisicaEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
