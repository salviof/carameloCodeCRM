package br.org.carameloCode.erp.modulo.crm.api.model.chatbot;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chatBot.ChatBot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ChatBot.class)
public enum ValoresLogicosChatBot {
	LINK, LINKPUBLICACAO, CODIGOACESSO
}