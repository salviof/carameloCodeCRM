package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatocolaborador;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoColaborador.ContatoColaborador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.contatocolaborador.ValidadorContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.api.model.contatocolaborador.ValidadoresContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto.ValidacaoContatoProspectoEmail;

@ValidadorContatoColaborador(validador = ValidadoresContatoColaborador.EMAIL)
public class ValidacaoContatoColaboradorEmail extends ValidacaoGenerica<ContatoColaborador> {

    public ValidacaoContatoColaboradorEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ValidacaoContatoProspectoEmail(getCampoInstanciado()).validar(pValor);
    }

    public ContatoColaborador getContatoColaborador() {
        return getObjetoDoAtributo();
    }
}
