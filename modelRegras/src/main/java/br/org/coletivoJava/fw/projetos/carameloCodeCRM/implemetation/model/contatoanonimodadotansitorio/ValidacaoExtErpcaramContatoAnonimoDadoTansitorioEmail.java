package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.contatoanonimodadotansitorio;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contatoanonimodadotansitorio.ValidacaoContatoAnonimoDadoTansitorioEmail;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contato.ContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.util.UtilCRMValidacoesEspeciais;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.contatoanonimodadotansitorio.ValidadorContatoAnonimoDadoTansitorio;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.contatoanonimodadotansitorio.ValidadoresContatoAnonimoDadoTansitorio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.util.ArrayList;

@ValidadorContatoAnonimoDadoTansitorio(validador = ValidadoresContatoAnonimoDadoTansitorio.EMAIL)
public class ValidacaoExtErpcaramContatoAnonimoDadoTansitorioEmail
        extends
        ValidacaoContatoAnonimoDadoTansitorioEmail {

    public ValidacaoExtErpcaramContatoAnonimoDadoTansitorioEmail(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List validar(java.lang.Object pValor) throws ErroValidacao {
        super.validar(pValor);
        UtilCRMValidacoesEspeciais.validarEmail((String) pValor);

        getContatoAnonimoDadoTansitorio().setEmail((String) pValor);
        return new ArrayList();
    }

    public ContatoAnonimoDadoTansitorio getContatoAnonimoDadoTansitorio() {
        return getObjetoDoAtributo();
    }
}
