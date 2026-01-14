package br.org.carameloCode.erp.modulo.crm.implemetation.model.mensagemmktwhatsapp;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.MensagemMktWhatsapp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.FabTipoWidgetFormulario;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp.ValidadorMensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp.ValidadoresMensagemMktWhatsapp;

@ValidadorMensagemMktWhatsapp(validador = ValidadoresMensagemMktWhatsapp.TIPO)
public class ValidacaoMensagemMktWhatsappTipo extends ValidacaoGenerica<MensagemMktWhatsapp> {

    public ValidacaoMensagemMktWhatsappTipo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return FabTipoWidgetFormulario.getCampos(getMensagemMktWhatsapp().getCPinst("linkPreview"));
    }

    public MensagemMktWhatsapp getMensagemMktWhatsapp() {
        return getObjetoDoAtributo();
    }
}
