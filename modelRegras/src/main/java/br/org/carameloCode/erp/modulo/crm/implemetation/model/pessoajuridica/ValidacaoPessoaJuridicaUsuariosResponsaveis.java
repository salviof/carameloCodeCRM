package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValidadorPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValidadoresPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValidacaoPessoaUsuariosResponsaveis;

@ValidadorPessoaJuridica(validador = ValidadoresPessoaJuridica.USUARIOSRESPONSAVEIS)
public class ValidacaoPessoaJuridicaUsuariosResponsaveis extends ValidacaoPessoaUsuariosResponsaveis {

    public ValidacaoPessoaJuridicaUsuariosResponsaveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public PessoaJuridica getPessoaJuridica() {
        return (PessoaJuridica) getObjetoDoAtributo();
    }
}
