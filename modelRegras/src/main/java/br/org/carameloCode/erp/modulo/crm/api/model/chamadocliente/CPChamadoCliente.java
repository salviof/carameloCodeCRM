package br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ChamadoCliente.class)
public enum CPChamadoCliente {
	_ID, _TITULO, _SATISFACAO, _STATUS, _ATENDENTERESPONSAVEL, _NOTIFICARVIASMS, _NOTIFICARVIAEMAIL, _ATENDENTESCONVIDADOS, _USUARIODISPONIVEIS, _USUARIOCLIENTE, _TIPOCHAMADO, _DESCRICAO, _RESUMODESCRICAO, _DATAHORACRIACAO, _DATAHORAPROGRAMADA, _SALAMATRIX, _ATIVO, _NOTIFICACOES, _EVENTOSDOCHAMADO, _PESSOA, _USUARIOCRIOU, _LINKURLACESSOCLIENTE, _DATAHORAULTIMOLINKACESSO, _DATAHORAPRIMEIROATENDIMENTO, _DATAHORAULTIMAINTERACAO;

	public static final String id = "id";
	public static final String titulo = "titulo";
	public static final String satisfacao = "satisfacao";
	public static final String status = "status";
	public static final String atendenteresponsavel = "atendenteResponsavel";
	public static final String notificarviasms = "notificarViaSMS";
	public static final String notificarviaemail = "notificarViaEmail";
	public static final String atendentesconvidados = "atendentesConvidados";
	public static final String usuariodisponiveis = "usuarioDisponiveis";
	public static final String usuariocliente = "usuarioCliente";
	public static final String tipochamado = "tipoChamado";
	public static final String descricao = "descricao";
	public static final String resumodescricao = "resumoDescricao";
	public static final String datahoracriacao = "dataHoraCriacao";
	public static final String datahoraprogramada = "dataHoraProgramada";
	public static final String salamatrix = "salaMatrix";
	public static final String ativo = "ativo";
	public static final String notificacoes = "notificacoes";
	public static final String eventosdochamado = "eventosDoChamado";
	public static final String pessoa = "pessoa";
	public static final String usuariocriou = "usuarioCriou";
	public static final String linkurlacessocliente = "linkUrlAcessoCliente";
	public static final String datahoraultimolinkacesso = "dataHoraUltimoLinkAcesso";
	public static final String datahoraprimeiroatendimento = "dataHoraPrimeiroAtendimento";
	public static final String datahoraultimainteracao = "dataHoraUltimaInteracao";
}