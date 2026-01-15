package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaFisica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadorPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadoresPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa.ValidacaoPessoaUsuariosResponsaveis;

@ValidadorPessoaFisica(validador = ValidadoresPessoaFisica.USUARIOSRESPONSAVEIS)
public class ValidacaoPessoaFisicaUsuariosResponsaveis extends ValidacaoPessoaUsuariosResponsaveis {

    public ValidacaoPessoaFisicaUsuariosResponsaveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public PessoaFisica getPessoaFisica() {
        return (PessoaFisica) getObjetoDoAtributo();
    }
}
