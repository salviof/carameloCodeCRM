package br.org.carameloCode.erp.modulo.crm.implemetation.model.cotatopessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoPessoaJuridica.CotatoPessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.cotatopessoajuridica.ValidadorCotatoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.cotatopessoajuridica.ValidadoresCotatoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto.ValidacaoContatoProspectoEmail;

@ValidadorCotatoPessoaJuridica(validador = ValidadoresCotatoPessoaJuridica.EMAIL)
public class ValidacaoCotatoPessoaJuridicaEmail extends ValidacaoGenerica<CotatoPessoaJuridica> {

    public ValidacaoCotatoPessoaJuridicaEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ValidacaoContatoProspectoEmail(getCampoInstanciado()).validar(pValor);
    }

    public CotatoPessoaJuridica getCotatoPessoaJuridica() {
        return getObjetoDoAtributo();
    }
}
