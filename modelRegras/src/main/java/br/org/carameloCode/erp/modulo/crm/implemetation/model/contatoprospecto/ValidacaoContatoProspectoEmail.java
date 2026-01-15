package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.util.UtilCRMValidacoesEspeciais;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValidadorContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValidadoresContatoProspecto;

@ValidadorContatoProspecto(validador = ValidadoresContatoProspecto.EMAIL)
public class ValidacaoContatoProspectoEmail extends ValidacaoGenerica<ContatoProspecto> {

    public ValidacaoContatoProspectoEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (!FabTipoValidacaoUnitaria.REGEX.getValidador(getCampoInstanciado()).isValorValido(pValor)) {
            throw new ErroValidacao("O e-mail não parece válido");
        }
        if (UtilCRCStringValidador.isNuloOuEmbranco(pValor)) {
            return null;
        }
        if (UtilCRCStringValidador.isNuloOuEmbranco(getContatoProspecto().getNome())) {
            if (!UtilCRCStringValidador.isNuloOuEmbranco(pValor)) {
                throw new ErroValidacao("Defina o nome do responsável por este e-mail");
            }
        }
        UtilCRMValidacoesEspeciais.validarEmailCliente(getContatoProspecto().getProspecto(), (String) pValor);

        if (getContatoProspecto().getUsuarioVinculado() != null) {
            String emailDOUsuario = getContatoProspecto().getUsuarioVinculado().getEmail();
            getContatoProspecto().getUsuarioVinculado().setEmail((String) pValor);
        }

        return new ArrayList<>();
    }

    public ContatoProspecto getContatoProspecto() {
        return getObjetoDoAtributo();
    }
}
