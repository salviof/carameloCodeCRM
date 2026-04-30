package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadorPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadoresPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValidacaoPessoaTelefonePrincipal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorPessoaFisica(validador = ValidadoresPessoaFisica.TELEFONEPRINCIPAL)
public class ValidacaoPessoaFisicaTelefonePrincipal
        extends
        ValidacaoPessoaTelefonePrincipal {

    public ValidacaoPessoaFisicaTelefonePrincipal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
