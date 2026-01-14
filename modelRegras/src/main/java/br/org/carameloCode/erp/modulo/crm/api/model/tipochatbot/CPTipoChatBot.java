package br.org.carameloCode.erp.modulo.crm.api.model.tipochatbot;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chatBot.TipoChatBot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoChatBot.class)
public enum CPTipoChatBot {
	_ID, _NOME, _LINK, _DADOSEXTRAS;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String link = "link";
	public static final String dadosextras = "dadosExtras";
}