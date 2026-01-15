package br.org.carameloCode.erp.modulo.crm.api.model.statuschatbot;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chatBot.StatusChatBot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = StatusChatBot.class)
public enum CPStatusChatBot {
	_ID, _NOME, _STATUSENUM, _ICONE;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String statusenum = "statusEnum";
	public static final String icone = "icone";
}