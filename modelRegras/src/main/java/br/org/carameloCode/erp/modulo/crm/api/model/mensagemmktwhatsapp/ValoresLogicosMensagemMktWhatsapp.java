package br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.MensagemMktWhatsapp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = MensagemMktWhatsapp.class)
public enum ValoresLogicosMensagemMktWhatsapp {
	PESSOA, LINKPREVIEW
}