package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValidadorEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValidadoresEnvioEmail;

@ValidadorEnvioEmail(validador = ValidadoresEnvioEmail.ASSUNTO)
public class ValidacaoEnvioEmailAssunto extends ValidacaoGenerica<EnvioEmail> {

    public ValidacaoEnvioEmailAssunto(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (pValor != null) {
            if (pValor.toString().split("[\\pP\\s&&[^']]+").length > 10) {
                throw new ErroValidacao("Para maior compreenção, utilize até 10 palavras no assunto");

            }
            if (pValor.toString().length() > 200) {
                throw new ErroValidacao("Utilize menos de 200 caracteres no assunto");
            }

        }

        return null;
    }

    public EnvioEmail getEnvioEmail() {
        return getObjetoDoAtributo();
    }
}
