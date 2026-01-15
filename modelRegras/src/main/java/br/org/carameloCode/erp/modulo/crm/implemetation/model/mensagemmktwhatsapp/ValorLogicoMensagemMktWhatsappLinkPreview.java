package br.org.carameloCode.erp.modulo.crm.implemetation.model.mensagemmktwhatsapp;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.MensagemMktWhatsapp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp.ValorLogicoMensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp.ValoresLogicosMensagemMktWhatsapp;

@ValorLogicoMensagemMktWhatsapp(calculo = ValoresLogicosMensagemMktWhatsapp.LINKPREVIEW)
public class ValorLogicoMensagemMktWhatsappLinkPreview
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMensagemMktWhatsappLinkPreview(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getMensagem().getTipo() != null) {
            getMensagem().setLinkPreview(getMensagem().getTipo().getUrlMensagemPreview());
        }
        return getMensagem().getLinkPreview();
    }

    public MensagemMktWhatsapp getMensagem() {
        return (MensagemMktWhatsapp) getCampoInst().getObjetoDoAtributo();
    }
}
