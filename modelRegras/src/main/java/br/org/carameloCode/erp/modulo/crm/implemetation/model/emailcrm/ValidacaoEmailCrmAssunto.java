package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailcrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.EmailCrm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValidadorEmailCrm;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValidadoresEmailCrm;

@ValidadorEmailCrm(validador = ValidadoresEmailCrm.ASSUNTO)
public class ValidacaoEmailCrmAssunto extends ValidacaoGenerica<EmailCrm> {

    public ValidacaoEmailCrmAssunto(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return null;
    }

    public EmailCrm getEmailCrm() {
        return getObjetoDoAtributo();
    }
}
