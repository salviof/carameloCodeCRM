package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValidadorEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValidadoresEmailRecebido;

@ValidadorEmailRecebido(validador = ValidadoresEmailRecebido.EMAILSOLICITANTE)
public class ValidacaoEmailRecebidoEmailSolicitante extends ValidacaoGenerica<EmailRecebido> {

    public ValidacaoEmailRecebidoEmailSolicitante(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return null;
    }

    public EmailRecebido getEmailRecebido() {
        return getObjetoDoAtributo();
    }
}
