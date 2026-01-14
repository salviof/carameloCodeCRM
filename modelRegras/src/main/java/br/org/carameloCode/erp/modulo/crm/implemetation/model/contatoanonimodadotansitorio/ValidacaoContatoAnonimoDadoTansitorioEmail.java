package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoanonimodadotansitorio;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoFormLead.ContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.util.UtilCRMValidacoesEspeciais;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoanonimodadotansitorio.ValidadorContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoanonimodadotansitorio.ValidadoresContatoAnonimoDadoTansitorio;

@ValidadorContatoAnonimoDadoTansitorio(validador = ValidadoresContatoAnonimoDadoTansitorio.EMAIL)
public class ValidacaoContatoAnonimoDadoTansitorioEmail extends ValidacaoGenerica<ContatoAnonimoDadoTansitorio> {

    public ValidacaoContatoAnonimoDadoTansitorioEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (UtilCRCStringValidador.isNuloOuEmbranco(pValor)) {
            if (getCampoInstanciado().isObrigatorio()) {
                throw new ErroValidacao("O e-mail não pode ser nulo");
            }
        }
        if (!FabTipoValidacaoUnitaria.REGEX.getValidador(getCampoInstanciado()).isValorValido(pValor)) {
            throw new ErroValidacao("O e-mail não parece válido");
        }
        UtilCRMValidacoesEspeciais.validarEmail((String) pValor);

        getContatoAnonimoDadoTansitorio().setEmail((String) pValor);
        return null;

    }

    public ContatoAnonimoDadoTansitorio getContatoAnonimoDadoTansitorio() {
        return getObjetoDoAtributo();
    }
}
