package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatopessoafisica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoaFisica.ContatoPessoaFisica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoafisica.ValidadorContatoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoafisica.ValidadoresContatoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto.ValidacaoContatoProspectoEmail;

@ValidadorContatoPessoaFisica(validador = ValidadoresContatoPessoaFisica.EMAIL)
public class ValidacaoContatoPessoaFisicaEmail extends ValidacaoGenerica<ContatoPessoaFisica> {

    public ValidacaoContatoPessoaFisicaEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ValidacaoContatoProspectoEmail(getCampoInstanciado()).validar(pValor);
    }

    public ContatoPessoaFisica getContatoPessoaFisica() {
        return getObjetoDoAtributo();
    }
}
