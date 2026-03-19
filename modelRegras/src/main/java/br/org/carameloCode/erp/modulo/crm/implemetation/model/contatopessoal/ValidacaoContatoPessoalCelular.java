package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatopessoal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoal.ContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal.ValidadorContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal.ValidadoresContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.cotatopessoajuridica.ValidacaoCotatoPessoaJuridicaCelular;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorContatoPessoal(validador = ValidadoresContatoPessoal.CELULAR)
public class ValidacaoContatoPessoalCelular
        extends
        ValidacaoCotatoPessoaJuridicaCelular {

    public ValidacaoContatoPessoalCelular(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public ContatoPessoal getContatoPessoal() {
        return (ContatoPessoal) getObjetoDoAtributo();
    }
}
