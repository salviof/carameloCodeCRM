package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValidadorPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValidadoresPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValidacaoPessoaTelefonePrincipal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorPessoaJuridica(validador = ValidadoresPessoaJuridica.TELEFONEPRINCIPAL)
public class ValidacaoPessoaJuridicaTelefonePrincipal
        extends
        ValidacaoPessoaTelefonePrincipal {

    public ValidacaoPessoaJuridicaTelefonePrincipal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public PessoaJuridica getPessoaJuridica() {
        return (PessoaJuridica) getObjetoDoAtributo();
    }
}
