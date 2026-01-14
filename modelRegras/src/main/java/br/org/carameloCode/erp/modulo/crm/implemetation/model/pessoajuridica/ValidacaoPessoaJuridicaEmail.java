package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValidadorPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValidadoresPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValidacaoPessoaEmail;

@ValidadorPessoaJuridica(validador = ValidadoresPessoaJuridica.EMAIL)
public class ValidacaoPessoaJuridicaEmail extends ValidacaoPessoaEmail {

    public ValidacaoPessoaJuridicaEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
