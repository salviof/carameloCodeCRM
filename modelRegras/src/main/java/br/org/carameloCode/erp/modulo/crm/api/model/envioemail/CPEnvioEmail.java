package br.org.carameloCode.erp.modulo.crm.api.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EnvioEmail.class)
public enum CPEnvioEmail {
	_DATADISPARO, _DESCRICAOENVIO, _MODELOEMAIL, _CONTATOS, _CONTATOSDISPONIVEIS, _ARQUIVOSANEXADOS, _ARQUIVOSDISPONIVEIS, _STATUSENVIO, _FOIENVIADO, _ENVIARCOPIACOLABORADORES, _ENVIARCOPIAUSUARIOENVIOU, _ULTIMORASCUNHO, _DATAHORAAGENDAMENTODISPARO, _FOILIDOPELODESTINATARIO, _HTMLMAILCAMPOPARAJSON, _HTMLMAILCAMPOEMCOPIAJSON, _LOGSDELEITURA, _EMREVISAO, _FALHASENVIO, _REVISOR, _HISTORICO, _TIPODEENVIO;

	public static final String datadisparo = "datadisparo";
	public static final String descricaoenvio = "descricaoEnvio";
	public static final String modeloemail = "modeloEmail";
	public static final String contatos = "contatos";
	public static final String contatosdisponiveis = "contatosDisponiveis";
	public static final String arquivosanexados = "arquivosAnexados";
	public static final String arquivosdisponiveis = "arquivosDisponiveis";
	public static final String statusenvio = "statusEnvio";
	public static final String foienviado = "foiEnviado";
	public static final String enviarcopiacolaboradores = "enviarCopiaColaboradores";
	public static final String enviarcopiausuarioenviou = "enviarCopiaUsuarioEnviou";
	public static final String ultimorascunho = "ultimoRascunho";
	public static final String datahoraagendamentodisparo = "dataHoraAgendamentoDisparo";
	public static final String foilidopelodestinatario = "foiLidoPeloDestinatario";
	public static final String htmlmailcampoparajson = "htmlMailCampoParaJson";
	public static final String htmlmailcampoemcopiajson = "htmlMailCampoEmCopiaJson";
	public static final String logsdeleitura = "logsDeLeitura";
	public static final String emrevisao = "emRevisao";
	public static final String falhasenvio = "falhasEnvio";
	public static final String revisor = "revisor";
	public static final String historico = "historico";
	public static final String tipodeenvio = "tipoDeEnvio";
}