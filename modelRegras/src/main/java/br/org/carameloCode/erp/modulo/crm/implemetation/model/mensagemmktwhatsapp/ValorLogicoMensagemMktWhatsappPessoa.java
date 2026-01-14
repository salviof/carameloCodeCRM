package br.org.carameloCode.erp.modulo.crm.implemetation.model.mensagemmktwhatsapp;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.MensagemMktWhatsapp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp.ValorLogicoMensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp.ValoresLogicosMensagemMktWhatsapp;

@ValorLogicoMensagemMktWhatsapp(calculo = ValoresLogicosMensagemMktWhatsapp.PESSOA)
public class ValorLogicoMensagemMktWhatsappPessoa
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMensagemMktWhatsappPessoa(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getMensagem().getContato() != null) {
            getMensagem().setPessoa(getMensagem().getContato().getProspecto());
        }
        return getMensagem().getPessoa();
    }

    public MensagemMktWhatsapp getMensagem() {
        return (MensagemMktWhatsapp) getCampoInst().getObjetoDoAtributo();
    }
}
