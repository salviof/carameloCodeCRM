package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatopessoafisica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoaFisica.ContatoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoafisica.ValidadorContatoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoafisica.ValidadoresContatoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.cotatopessoajuridica.ValidacaoCotatoPessoaJuridicaCelular;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorContatoPessoaFisica(validador = ValidadoresContatoPessoaFisica.CELULAR)
public class ValidacaoContatoPessoaFisicaCelular
        extends
        ValidacaoCotatoPessoaJuridicaCelular {

    public ValidacaoContatoPessoaFisicaCelular(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public ContatoPessoaFisica getContatoPessoaFisica() {
        return (ContatoPessoaFisica) getObjetoDoAtributo();
    }
}
