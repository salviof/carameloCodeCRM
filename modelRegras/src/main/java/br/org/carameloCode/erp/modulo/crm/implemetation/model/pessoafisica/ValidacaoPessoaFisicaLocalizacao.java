package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadorPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadoresPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValidacaoPessoaLocalizacao;

@ValidadorPessoaFisica(validador = ValidadoresPessoaFisica.LOCALIZACAO)
public class ValidacaoPessoaFisicaLocalizacao extends ValidacaoPessoaLocalizacao {

    public ValidacaoPessoaFisicaLocalizacao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
