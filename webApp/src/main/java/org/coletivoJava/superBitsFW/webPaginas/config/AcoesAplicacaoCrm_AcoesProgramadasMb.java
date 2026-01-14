package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;

@Named
@ApplicationScoped
public class AcoesAplicacaoCrm_AcoesProgramadasMb implements Serializable {

	public ItfAcaoFormulario getAcoesProgramadasMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_MB");
	}

	public ComoAcaoControllerEntidade getAcoesProgramadasCtrEnviarEmailProgramado() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_ENVIAR_EMAIL_PROGRAMADO");
	}

	public ComoAcaoControllerEntidade getAcoesProgramadasCtrInicializarTarefasAgendadas() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_INICIALIZAR_TAREFAS_AGENDADAS");
	}

	public ComoAcaoControllerEntidade getAcoesProgramadasCtrReceberEmail() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_RECEBER_EMAIL");
	}

	public ComoAcaoControllerEntidade getAcoesProgramadasCtrTestarCaixaDeEntrada() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_TESTAR_CAIXA_DE_ENTRADA");
	}

	public ComoAcaoController getAcoesProgramadasCtrReceberEmailsAutoExec() {
		return (ComoAcaoController) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_RECEBER_EMAILS_AUTO_EXEC");
	}

	public ComoAcaoControllerEntidade getAcoesProgramadasCtrMarcarEmailsLidos() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_MARCAR_EMAILS_LIDOS");
	}

	public ComoAcaoControllerEntidade getAcoesProgramadasCtrSalvarEmailRecebido() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_SALVAR_EMAIL_RECEBIDO");
	}

	public ComoAcaoControllerEntidade getAcoesProgramadasCtrAlterarRelacionamentoPorInercia() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_ALTERAR_RELACIONAMENTO_POR_INERCIA");
	}

	public ComoAcaoControllerEntidade getAcoesProgramadasCtrAlterarTodosRelacionamentosPorInercia() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_ALTERAR_TODOS_RELACIONAMENTOS_POR_INERCIA");
	}

	public ComoAcaoController getAcoesProgramadasCtrCarregarSolicitacoesAutoExec() {
		return (ComoAcaoController) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_CARREGAR_SOLICITACOES_AUTO_EXEC");
	}

	public ComoAcaoController getAcoesProgramadasCtrTypebotFormSincronizarAutoExec() {
		return (ComoAcaoController) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_TYPEBOT_FORM_SINCRONIZAR_AUTO_EXEC");
	}

	public ComoAcaoController getAcoesProgramadasCtrTypebotRespostaSincronizarAutoExec() {
		return (ComoAcaoController) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_TYPEBOT_RESPOSTA_SINCRONIZAR_AUTO_EXEC");
	}
}