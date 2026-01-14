package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;

@Named
@ApplicationScoped
public class AcoesAtendimentoCrm_ExecucaoAtividadeMb implements Serializable {

	public ItfAcaoFormulario getExecucaoAtividadeMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmVerAtividadeRealizada() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_VER_ATIVIDADE_REALIZADA");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrIniciarAtividade() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_INICIAR_ATIVIDADE");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrConcluirAtividade() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CONCLUIR_ATIVIDADE");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrCriarChatBot() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CRIAR_CHAT_BOT");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrEnviarwhatsapp() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_ENVIARWHATSAPP");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrGerarDocumentos() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_GERAR_DOCUMENTOS");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrEmailAplicarModeloEmailDeAtividade() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_APLICAR_MODELO_EMAIL_DE_ATIVIDADE");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrEmailSalvarEstadoAtual() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_SALVAR_ESTADO_ATUAL");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrEmailSalvarRascunho() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_SALVAR_RASCUNHO");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrEmailSalvarFormatado() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_SALVAR_FORMATADO");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrEmailEnviar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_ENVIAR");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrEmailEnviarEConcluir() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_ENVIAR_E_CONCLUIR");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrEmailAplicarModelo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_EMAIL_APLICAR_MODELO");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrConcluirMaisTarde() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CONCLUIR_MAIS_TARDE");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrCancelar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CANCELAR");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrCancelarAtividadeAnterior() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CANCELAR_ATIVIDADE_ANTERIOR");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrContinuarAnteriorExcluindoEsta() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CONTINUAR_ANTERIOR_EXCLUINDO_ESTA");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrSalvarDadosDinamicos() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_SALVAR_DADOS_DINAMICOS");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrSalvarProspectoDadosDinamicos() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_SALVAR_PROSPECTO_DADOS_DINAMICOS");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrSalvarDadosDinamicosConcluindoAtividade() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_SALVAR_DADOS_DINAMICOS_CONCLUINDO_ATIVIDADE");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrAgendar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_AGENDAR");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrEnviarConvite() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_ENVIAR_CONVITE");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadeSimples() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_SIMPLES");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadeAgendar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_AGENDAR");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadeComEmail() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_COM_EMAIL");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadeDadosObrigatorios() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_DADOS_OBRIGATORIOS");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadeAdicionarLogomarca() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ADICIONAR_LOGOMARCA");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadePluginDeAtividade() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_PLUGIN_DE_ATIVIDADE");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadeAdicionarServicos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ADICIONAR_SERVICOS");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadeGeradora() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_GERADORA");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadeAcessoNegado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ACESSO_NEGADO");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadePorConvidado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_POR_CONVIDADO");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadeConvite() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_CONVITE");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmContato() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_CONTATO");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadeSobrescreverAtividade() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_SOBRESCREVER_ATIVIDADE");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmDocumentoRelatorio() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_RELATORIO");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmDocumentoGerar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_GERAR");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmDocumentoFormatar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_FORMATAR");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmDocumentoEnviar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_ENVIAR");
	}

	public ItfAcaoFormularioEntidade getExecucaoAtividadeFrmAtividadeEscolherAtividade() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrAtividadePorTelefone() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_ATIVIDADE_POR_TELEFONE");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrAtividadePorTelefoneAtivo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_ATIVIDADE_POR_TELEFONE_ATIVO");
	}

	public ComoAcaoControllerEntidade getExecucaoAtividadeCtrAtividadePorTelefoneReceptivo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_ATIVIDADE_POR_TELEFONE_RECEPTIVO");
	}
}