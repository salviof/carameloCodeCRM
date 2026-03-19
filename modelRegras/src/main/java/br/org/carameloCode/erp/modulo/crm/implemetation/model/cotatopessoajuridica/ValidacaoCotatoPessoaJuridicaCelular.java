package br.org.carameloCode.erp.modulo.crm.implemetation.model.cotatopessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoaJuridica.CotatoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.cotatopessoajuridica.ValidadorCotatoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.cotatopessoajuridica.ValidadoresCotatoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto.ValidacaoContatoProspectoCelular;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorCotatoPessoaJuridica(validador = ValidadoresCotatoPessoaJuridica.CELULAR)
public class ValidacaoCotatoPessoaJuridicaCelular
        extends
        ValidacaoContatoProspectoCelular {

    public ValidacaoCotatoPessoaJuridicaCelular(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public CotatoPessoaJuridica getCotatoPessoaJuridica() {
        return (CotatoPessoaJuridica) getObjetoDoAtributo();
    }
}
