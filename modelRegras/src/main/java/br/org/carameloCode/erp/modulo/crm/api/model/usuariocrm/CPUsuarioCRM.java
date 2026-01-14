package br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = UsuarioCRM.class)
public enum CPUsuarioCRM {
	_PROSPECTOS, _ORIGENS, _DATAHORALEMBRARMAISTARDEATIVIDADESINACABADAS, _AREATRABALHOPADRAO, _AREASDISPONIVEIS, _PROSPECTOSRELACIONAMENTOCOMPESO, _PROSPECTOSRELACIONAMENTOSEMPESO, _MEUSPROSPECTOSATIVOS, _MEUSPROSPECTOSEMNEGOCIACAO, _MEUSPROSPECTOSCOMATIVADADEPENDENTES, _ATIVIDADENAOFINALIZADA, _ATIVIDADEPARAHOJE, _ATIVIDADEAGENDADANAOREALIZADA, _ATIVIDADEAGENDADA, _PODEACESSARTODOSPROSPECTOS, _ASSINATURAEMAIL, _ATIVIDADESPRINCIPAIS, _TAGSMONITORADAS, _ESTATISTICAS, _CONTATOVINCULADO, _CAIXAPOSTALPRINCIPAL, _CAIXASPOSTAIS, _CONTATOSPRIVADOS, _METADADOSATENDENTE, _ORCAMENTOS, _ESCOPOAGENDACLIENTES, _SOLICITACOESAGUARDANDO, _SOLICITACOESLIBERARCARD, _SOLICITACOESFEITAS, _CODIGOMATRIX, _CALCULOSMETA;

	public static final String prospectos = "prospectos";
	public static final String origens = "origens";
	public static final String datahoralembrarmaistardeatividadesinacabadas = "dataHoraLembrarMaisTardeAtividadesInacabadas";
	public static final String areatrabalhopadrao = "areatrabalhoPadrao";
	public static final String areasdisponiveis = "areasDisponiveis";
	public static final String prospectosrelacionamentocompeso = "prospectosRelacionamentoComPeso";
	public static final String prospectosrelacionamentosempeso = "prospectosRelacionamentoSemPeso";
	public static final String meusprospectosativos = "meusProspectosAtivos";
	public static final String meusprospectosemnegociacao = "meusProspectosEmNegociacao";
	public static final String meusprospectoscomativadadependentes = "meusProspectosComAtivadadePendentes";
	public static final String atividadenaofinalizada = "atividadeNaoFinalizada";
	public static final String atividadeparahoje = "atividadeParaHoje";
	public static final String atividadeagendadanaorealizada = "atividadeAgendadaNaoRealizada";
	public static final String atividadeagendada = "atividadeAgendada";
	public static final String podeacessartodosprospectos = "podeAcessarTodosProspectos";
	public static final String assinaturaemail = "assinaturaEmail";
	public static final String atividadesprincipais = "atividadesPrincipais";
	public static final String tagsmonitoradas = "tagsMonitoradas";
	public static final String estatisticas = "estatisticas";
	public static final String contatovinculado = "contatoVinculado";
	public static final String caixapostalprincipal = "caixaPostalPrincipal";
	public static final String caixaspostais = "caixasPostais";
	public static final String contatosprivados = "contatosPrivados";
	public static final String metadadosatendente = "metadadosAtendente";
	public static final String orcamentos = "orcamentos";
	public static final String escopoagendaclientes = "escopoAgendaClientes";
	public static final String solicitacoesaguardando = "solicitacoesAguardando";
	public static final String solicitacoesliberarcard = "solicitacoesLiberarCard";
	public static final String solicitacoesfeitas = "solicitacoesFeitas";
	public static final String codigomatrix = "codigoMatrix";
	public static final String calculosmeta = "calculosMeta";
}