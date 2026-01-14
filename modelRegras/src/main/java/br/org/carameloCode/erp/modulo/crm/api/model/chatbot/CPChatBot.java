package br.org.carameloCode.erp.modulo.crm.api.model.chatbot;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chatBot.ChatBot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ChatBot.class)
public enum CPChatBot {
	_ID, _NOME, _LINK, _LINKPUBLICACAO, _ATIVIDADE, _TIPO, _CODIGOSESSAO, _CODIGOACESSO, _DATAHORAACESSO, _DATAHORAALTERACAO, _STATUSPRINCIPAL;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String link = "link";
	public static final String linkpublicacao = "linkPublicacao";
	public static final String atividade = "atividade";
	public static final String tipo = "tipo";
	public static final String codigosessao = "codigoSessao";
	public static final String codigoacesso = "codigoAcesso";
	public static final String datahoraacesso = "dataHoraAcesso";
	public static final String datahoraalteracao = "dataHoraAlteracao";
	public static final String statusprincipal = "statusPrincipal";
}