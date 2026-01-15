package br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoAtividadeCRM.class)
public enum CPTipoAtividadeCRM {
	_ID, _NOMEATIVIDADE, _NOMEINICIOATIVIDA, _NOMEFIMATIVIDADE, _TIPOENTIDADE, _TEXTOAJUDA, _COR, _ICONE, _RELACIONAMENTOGERADO, _PROGRESSO, _REGRESSO, _TEMDISPARODEEMAIL, _EXECUCAODIRETASEMRELATORIO, _XHTMLALTERNATIVO, _DIASAGENDARNOVAATIVIDADE, _PRECISASERVICOSDEFINIDOS, _PRECISATERPREANALISE, _PRECISAENVIARDOCUMENTO, _PRECISATERIMAGEM, _PRECISATERANEXO, _PRECISATERSITE, _ATIVAMODOPOSVENDAS, _DISPONIVELAPENASPOSVENDAS, _EMAILTRANSACIONALMKT, _EMAILSTRANSACIONAISDISPONIVEIS, _ACAOFORMULARIOEXECUCAO, _MODELOEMAIL, _ATIVIDADEABERTAPELOUSUARIOLOGADO, _ATIVIDADEAGENDADA, _ATIVIDADEAGENDADAAPOSLEITURAEMAIL, _DIASAGENDAMENTOAPOSLEITURA, _MODELOSDOCUMENTOVINCULADOS, _USUARIOSAUTORIZADOS, _RESULTAEMRELACIONAMENTOANTERIOR, _ATIVIDADEPUBLICA, _TAGSATENDIMENTOADICIONADAS, _TAGSATENDIMENTOREMOVIDAS, _TIPOSDADOSCOLETARNAATIVIDADE, _ACAODEPLUGINVUNCULADO, _ACOESDEPLUGINSDISPONIVEIS, _APENASCLIENTE, _TIPOMENSAGEMWTZAP, _TIPOCHATBOT;

	public static final String id = "id";
	public static final String nomeatividade = "nomeAtividade";
	public static final String nomeinicioativida = "nomeInicioAtivida";
	public static final String nomefimatividade = "nomeFimAtividade";
	public static final String tipoentidade = "tipoEntidade";
	public static final String textoajuda = "textoAjuda";
	public static final String cor = "cor";
	public static final String icone = "icone";
	public static final String relacionamentogerado = "relacionamentoGerado";
	public static final String progresso = "progresso";
	public static final String regresso = "regresso";
	public static final String temdisparodeemail = "temDisparoDeEmail";
	public static final String execucaodiretasemrelatorio = "execucaoDiretaSemRelatorio";
	public static final String xhtmlalternativo = "xhtmlAlternativo";
	public static final String diasagendarnovaatividade = "diasAgendarNovaAtividade";
	public static final String precisaservicosdefinidos = "precisaServicosDefinidos";
	public static final String precisaterpreanalise = "precisaTerPreAnalise";
	public static final String precisaenviardocumento = "precisaEnviarDocumento";
	public static final String precisaterimagem = "precisaTerImagem";
	public static final String precisateranexo = "precisaTerAnexo";
	public static final String precisatersite = "precisaTerSite";
	public static final String ativamodoposvendas = "ativaModoPosVendas";
	public static final String disponivelapenasposvendas = "disponivelApenasPosVendas";
	public static final String emailtransacionalmkt = "emailTransacionalMkt";
	public static final String emailstransacionaisdisponiveis = "emailsTransacionaisDisponiveis";
	public static final String acaoformularioexecucao = "acaoFormularioExecucao";
	public static final String modeloemail = "modeloEmail";
	public static final String atividadeabertapelousuariologado = "atividadeAbertaPeloUsuarioLogado";
	public static final String atividadeagendada = "atividadeAgendada";
	public static final String atividadeagendadaaposleituraemail = "atividadeAgendadaAposLeituraEmail";
	public static final String diasagendamentoaposleitura = "diasAgendamentoAposLeitura";
	public static final String modelosdocumentovinculados = "modelosDocumentoVinculados";
	public static final String usuariosautorizados = "usuariosAutorizados";
	public static final String resultaemrelacionamentoanterior = "resultaEmRelacionamentoAnterior";
	public static final String atividadepublica = "atividadePublica";
	public static final String tagsatendimentoadicionadas = "tagsAtendimentoAdicionadas";
	public static final String tagsatendimentoremovidas = "tagsAtendimentoRemovidas";
	public static final String tiposdadoscoletarnaatividade = "tiposDadosColetarNaAtividade";
	public static final String acaodepluginvunculado = "acaoDePLuginVunculado";
	public static final String acoesdepluginsdisponiveis = "acoesDePluginsDisponiveis";
	public static final String apenascliente = "apenasCLiente";
	public static final String tipomensagemwtzap = "tipoMensagemWtzap";
	public static final String tipochatbot = "tipoChatBot";
}