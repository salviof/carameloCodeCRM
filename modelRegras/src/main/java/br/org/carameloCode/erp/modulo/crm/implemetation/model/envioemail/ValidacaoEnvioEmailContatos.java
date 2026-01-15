package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValidadorEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValidadoresEnvioEmail;

@ValidadorEnvioEmail(validador = ValidadoresEnvioEmail.CONTATOS)
public class ValidacaoEnvioEmailContatos extends ValidacaoGenerica<EnvioEmail> {

    public ValidacaoEnvioEmailContatos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        List<ContatoProspecto> contatos = (List<ContatoProspecto>) pValor;

        Map<Pessoa, Long> contEmp = contatos.stream().filter(ct -> ((ct instanceof ContatoProspecto) && (ct.getProspecto() != null)))
                .collect(Collectors.groupingBy(ContatoProspecto::getProspecto, Collectors.counting()));

        if (contEmp.size() > 1) {
            throw new ErroValidacao("Utilize a opção e-mail transacional, para enviar um e-mail para mais de uma empresa");
        }
        if (contEmp.size() == 1) {
            getEnvioEmail().setProspecto(contEmp.keySet().stream().findFirst().get());
        }

        return null;
    }

    public EnvioEmail getEnvioEmail() {
        return getObjetoDoAtributo();
    }
}
