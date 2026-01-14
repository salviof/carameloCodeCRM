package br.org.carameloCode.erp.modulo.crm.implemetation.model.parametromensagemwtzap;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.ParametroMensagemWtzap;
import br.org.carameloCode.erp.modulo.crm.util.UtilCRMDadoOrigemCaminhoRelativo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.FabTipoWidgetFormulario;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.parametromensagemwtzap.ValidadorParametroMensagemWtzap;
import br.org.carameloCode.erp.modulo.crm.api.model.parametromensagemwtzap.ValidadoresParametroMensagemWtzap;

@ValidadorParametroMensagemWtzap(validador = ValidadoresParametroMensagemWtzap.DADORELATIVO)
public class ValidacaoParametroMensagemWtzapDadoRelativo extends ValidacaoGenerica<ParametroMensagemWtzap> {

    public ValidacaoParametroMensagemWtzapDadoRelativo(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (!UtilCRCStringValidador.isNuloOuEmbranco(pValor)) {
            getParametroMensagemWtzap().setTipoDado(null);
            UtilCRMDadoOrigemCaminhoRelativo.validarCaminhoRelativoContato(pValor.toString());
        }

        if (UtilCRCStringValidador.isNuloOuEmbranco(pValor) && getParametroMensagemWtzap().getTipoDado() == null) {
            throw new ErroValidacao("Especofique uma origem via dado dinamico ou caminho do atributo");
        }

        return FabTipoWidgetFormulario.CAMPO.getCampos(getParametroMensagemWtzap().getCPinst("tipoDado"));
    }

    public ParametroMensagemWtzap getParametroMensagemWtzap() {
        return getObjetoDoAtributo();
    }
}
