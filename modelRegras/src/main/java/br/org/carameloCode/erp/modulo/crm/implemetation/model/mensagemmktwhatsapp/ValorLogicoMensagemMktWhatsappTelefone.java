package br.org.carameloCode.erp.modulo.crm.implemetation.model.mensagemmktwhatsapp;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp.ValorLogicoMensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp.ValoresLogicosMensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.MensagemMktWhatsapp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoMensagemMktWhatsapp(calculo = ValoresLogicosMensagemMktWhatsapp.TELEFONE)
public class ValorLogicoMensagemMktWhatsappTelefone
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoMensagemMktWhatsappTelefone(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

    @Override
    public Object getValor(Object... pEntidade) {
        MensagemMktWhatsapp mensagem = getMensagemMktWhatsapp();

        if (mensagem.getTelefone() == null && mensagem.getTipo() != null && mensagem.getTipo().getTelefonePadrao() != null) {
            mensagem.setTelefone(mensagem.getTipo().getTelefonePadrao());
        }

        return mensagem.getTelefone();
    }


	public MensagemMktWhatsapp getMensagemMktWhatsapp() {
		return (MensagemMktWhatsapp) getCampoInst().getObjetoRaizDoAtributo();
	}
}