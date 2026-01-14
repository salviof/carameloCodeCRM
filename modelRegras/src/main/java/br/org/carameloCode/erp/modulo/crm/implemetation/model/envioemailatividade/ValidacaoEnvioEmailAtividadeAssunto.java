package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValidadorEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValidadoresEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail.ValidacaoEnvioEmailAssunto;

@ValidadorEnvioEmailAtividade(validador = ValidadoresEnvioEmailAtividade.ASSUNTO)
public class ValidacaoEnvioEmailAtividadeAssunto extends ValidacaoGenerica<EnvioEmailAtividade> {

    public ValidacaoEnvioEmailAtividadeAssunto(ItfCampoInstanciado pCampo) {
        super(pCampo);
        validacaoGenerica = new ValidacaoEnvioEmailAssunto(pCampo);
    }
    private final ValidacaoEnvioEmailAssunto validacaoGenerica;

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return validacaoGenerica.validar(pValor);
    }

    public EnvioEmailAtividade getEnvioEmailAtividade() {
        return getObjetoDoAtributo();
    }
}
