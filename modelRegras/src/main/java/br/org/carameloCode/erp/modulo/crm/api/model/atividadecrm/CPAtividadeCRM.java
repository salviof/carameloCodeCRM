package br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AtividadeCRM.class)
public enum CPAtividadeCRM {
	_ID, _TIPOENTIDADE, _DATAHORAREALIZACAO, _DATAHORAINICIOATIVIDADE, _DATAHORAPREVISAOEXECUCAO, _NOMEATIVIDADE, _SOLICITACAO, _TIPOATIVIDADE, _USUARIOATIVIDADE, _PROSPECTOEMPRESA, _DADOSCOLETADOS, _DADOSNAOCOLETADOS, _DADOSVINCULODIRETO, _ANOTACOES, _STATUSATIVIDADE, _DOCUMENTOSGERADOS, _CODIGOSCONVITE, _DADOSNAOCOLETADOSATUALIZADOS, _DOCUMENTOENVIADO, _DOCUMENTOGERADO, _FORMULARIOEXECUCAO, _ACAOFORMULARIOEXECUCAO, _EMAILVINCULADO, _EMAILSVINCULADOS, _ATIVIDADEMESMOTIPOABERTAUSUARIOLOGADO, _SOBRESCREVERACAOATUAL, _RELACIONAMENTOGERADO, _ORCAMENTO, _ORCAMENTOSDISPONIVEIS, _POSSIETAPASRESTANTES, _DEMANDAPLUGINFINALIZADA, _NOESTADOINTERACAOPLUGIN, _ETAPASRESTANTES, _TIPOBLOQUEIO, _LISTABLOQUEIOSRESTANTES, _ENVIOMENSAGENS, _CHATBOT, _POSSSUICOLETADADO, _DADOSREVISADOS, _PERMITIDOCONCLUIR;

	public static final String id = "id";
	public static final String tipoentidade = "tipoEntidade";
	public static final String datahorarealizacao = "dataHoraRealizacao";
	public static final String datahorainicioatividade = "dataHoraInicioAtividade";
	public static final String datahoraprevisaoexecucao = "dataHoraPrevisaoExecucao";
	public static final String nomeatividade = "nomeAtividade";
	public static final String solicitacao = "solicitacao";
	public static final String tipoatividade = "tipoAtividade";
	public static final String usuarioatividade = "usuarioAtividade";
	public static final String prospectoempresa = "prospectoEmpresa";
	public static final String dadoscoletados = "dadosColetados";
	public static final String dadosnaocoletados = "dadosNaoColetados";
	public static final String dadosvinculodireto = "dadosVinculoDireto";
	public static final String anotacoes = "anotacoes";
	public static final String statusatividade = "statusAtividade";
	public static final String documentosgerados = "documentosGerados";
	public static final String codigosconvite = "codigosConvite";
	public static final String dadosnaocoletadosatualizados = "dadosNaoColetadosAtualizados";
	public static final String documentoenviado = "documentoEnviado";
	public static final String documentogerado = "documentoGerado";
	public static final String formularioexecucao = "formularioExecucao";
	public static final String acaoformularioexecucao = "acaoFormularioExecucao";
	public static final String emailvinculado = "emailVinculado";
	public static final String emailsvinculados = "emailsVinculados";
	public static final String atividademesmotipoabertausuariologado = "atividadeMesmoTipoAbertaUsuarioLogado";
	public static final String sobrescreveracaoatual = "sobrescreverAcaoAtual";
	public static final String relacionamentogerado = "relacionamentoGerado";
	public static final String orcamento = "orcamento";
	public static final String orcamentosdisponiveis = "orcamentosDisponiveis";
	public static final String possietapasrestantes = "possiEtapasRestantes";
	public static final String demandapluginfinalizada = "demandaPluginFinalizada";
	public static final String noestadointeracaoplugin = "noEstadoInteracaoPlugin";
	public static final String etapasrestantes = "etapasRestantes";
	public static final String tipobloqueio = "tipoBloqueio";
	public static final String listabloqueiosrestantes = "listaBloqueiosRestantes";
	public static final String enviomensagens = "envioMensagens";
	public static final String chatbot = "chatBot";
	public static final String posssuicoletadado = "posssuiColetaDado";
	public static final String dadosrevisados = "dadosRevisados";
	public static final String permitidoconcluir = "permitidoConcluir";
}