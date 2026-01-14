package br.org.carameloCode.erp.modulo.crm.implemetation.model.parametromensagemwtzap;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.ParametroMensagemWtzap;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.FabTipoWidgetFormulario;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.parametromensagemwtzap.ValidadorParametroMensagemWtzap;
import br.org.carameloCode.erp.modulo.crm.api.model.parametromensagemwtzap.ValidadoresParametroMensagemWtzap;

@ValidadorParametroMensagemWtzap(validador = ValidadoresParametroMensagemWtzap.TIPODADO)
public class ValidacaoParametroMensagemWtzapTipoDado extends ValidacaoGenerica<ParametroMensagemWtzap> {

    public ValidacaoParametroMensagemWtzapTipoDado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (pValor != null) {
            getParametroMensagemWtzap().setDadoRelativo(null);
        }
        if (pValor == null && getParametroMensagemWtzap().getDadoRelativo() == null) {
            throw new ErroValidacao("Especifique uma origem via dado dinamico ou caminho do atributo");
        }

        return FabTipoWidgetFormulario.getCampos(getParametroMensagemWtzap().getCPinst("dadoRelativo"));
    }

    public ParametroMensagemWtzap getParametroMensagemWtzap() {
        return getObjetoDoAtributo();
    }
}
