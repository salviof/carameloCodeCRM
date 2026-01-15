package br.org.carameloCode.erp.modulo.crm.api.model.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = Solicitacao.class)
public enum CPSolicitacao {
	_ID, _NOME, _TIPOSOLICITACAO, _DATAHORASOLICITACAO, _DATAHORADATAPROGRAMADA, _DATAULTIMARESPOSTA, _USUARIOSOLICITANTE, _USUARIOSOLICITADO, _TIPOENTITYSOLICIATACAO, _PESSOA, _LINKCONVITE, _OBESERVACAO, _FOIFINALIZADA, _FOIATENDIDA, _FOIREAGEDADO, _FOIRECEBIDA, _DESTINATARIO, _RESPOSTA, _RESPOSTAS, _TRANSPORTES;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String tiposolicitacao = "tipoSolicitacao";
	public static final String datahorasolicitacao = "dataHoraSolicitacao";
	public static final String datahoradataprogramada = "dataHoraDataProgramada";
	public static final String dataultimaresposta = "dataUltimaResposta";
	public static final String usuariosolicitante = "usuarioSolicitante";
	public static final String usuariosolicitado = "usuarioSolicitado";
	public static final String tipoentitysoliciatacao = "tipoEntitySoliciatacao";
	public static final String pessoa = "pessoa";
	public static final String linkconvite = "linkConvite";
	public static final String obeservacao = "obeservacao";
	public static final String foifinalizada = "foiFinalizada";
	public static final String foiatendida = "foiAtendida";
	public static final String foireagedado = "foiReagedado";
	public static final String foirecebida = "foiRecebida";
	public static final String destinatario = "destinatario";
	public static final String resposta = "resposta";
	public static final String respostas = "respostas";
	public static final String transportes = "transportes";
}