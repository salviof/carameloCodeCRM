package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatopessoal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoal.ContatoPessoal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal.ValidadorContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal.ValidadoresContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto.ValidacaoContatoProspectoEmail;

@ValidadorContatoPessoal(validador = ValidadoresContatoPessoal.EMAIL)
public class ValidacaoContatoPessoalEmail extends ValidacaoGenerica<ContatoPessoal> {

    public ValidacaoContatoPessoalEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ValidacaoContatoProspectoEmail(getCampoInstanciado()).validar(pValor);
    }

    public ContatoPessoal getContatoPessoal() {
        return getObjetoDoAtributo();
    }
}
