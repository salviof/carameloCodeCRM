package br.org.carameloCode.erp.modulo.crm.api.model.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = Solicitacao.class)
public enum CPSolicitacao {
	_ID, _NOME, _TIPOSOLICITACAO, _DATAHORASOLICITACAO, _DATAHORADATAPROGRAMADA, _DATAULTIMARESPOSTA, _USUARIOSOLICITANTE, _USUARIOSOLICITADO, _TIPOENTITYSOLICIATACAO, _STATUS, _PESSOA, _LINKCONVITE, _OBSERVACAO, _FOIFINALIZADA, _FOIATENDIDA, _FOIREAGEDADO, _FOIRECEBIDA, _EMATRASO, _CODIGOSELO, _NOTIFICACAO, _DESTINATARIO, _RESPOSTA, _RESPOSTAS, _TRANSPORTES, _UMACOMUNICACAOPERSONALIZADA, _URLRESPOSTAPERSONALIZADA;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String tiposolicitacao = "tipoSolicitacao";
	public static final String datahorasolicitacao = "dataHoraSolicitacao";
	public static final String datahoradataprogramada = "dataHoraDataProgramada";
	public static final String dataultimaresposta = "dataUltimaResposta";
	public static final String usuariosolicitante = "usuarioSolicitante";
	public static final String usuariosolicitado = "usuarioSolicitado";
	public static final String tipoentitysoliciatacao = "tipoEntitySoliciatacao";
	public static final String status = "status";
	public static final String pessoa = "pessoa";
	public static final String linkconvite = "linkConvite";
	public static final String observacao = "observacao";
	public static final String foifinalizada = "foiFinalizada";
	public static final String foiatendida = "foiAtendida";
	public static final String foireagedado = "foiReagedado";
	public static final String foirecebida = "foiRecebida";
	public static final String ematraso = "emAtraso";
	public static final String codigoselo = "codigoSelo";
	public static final String notificacao = "notificacao";
	public static final String destinatario = "destinatario";
	public static final String resposta = "resposta";
	public static final String respostas = "respostas";
	public static final String transportes = "transportes";
	public static final String umacomunicacaopersonalizada = "umaComunicacaoPersonalizada";
	public static final String urlrespostapersonalizada = "urlRespostaPersonalizada";
}