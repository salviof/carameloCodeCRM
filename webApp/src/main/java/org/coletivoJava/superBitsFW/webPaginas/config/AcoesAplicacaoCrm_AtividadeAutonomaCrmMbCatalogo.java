package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;

@Named
@ApplicationScoped
public class AcoesAplicacaoCrm_AtividadeAutonomaCrmMbCatalogo
		implements
			Serializable {

	public ItfAcaoFormulario getAtividadeAutonomaCrmMbCatalogo() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_MB_CATALOGO");
	}

	public ComoAcaoControllerEntidade getAtividadeAutonomaCrmCtrAlterarRelacionamento() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_ALTERAR_RELACIONAMENTO");
	}

	public ComoAcaoControllerEntidade getAtividadeAutonomaCrmCtrAlteracaoRelacionamentoAcoesAutomaticas() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_ALTERACAO_RELACIONAMENTO_ACOES_AUTOMATICAS");
	}

	public ComoAcaoControllerEntidade getAtividadeAutonomaCrmCtrAlteracaoRelacionamentoAlterarResponsavel() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_ALTERACAO_RELACIONAMENTO_ALTERAR_RESPONSAVEL");
	}

	public ComoAcaoControllerEntidade getAtividadeAutonomaCrmCtrAlteracaoRelacionamentoAlterarTags() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_ALTERACAO_RELACIONAMENTO_ALTERAR_TAGS");
	}

	public ComoAcaoControllerEntidade getAtividadeAutonomaCrmCtrConclusaoAcoesAutomaticas() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_ACOES_AUTOMATICAS");
	}

	public ComoAcaoControllerEntidade getAtividadeAutonomaCrmCtrConclusaoAgendarNovaAtividade() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_AGENDAR_NOVA_ATIVIDADE");
	}

	public ComoAcaoControllerEntidade getAtividadeAutonomaCrmCtrConclusaoAlterarTags() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_ALTERAR_TAGS");
	}

	public ComoAcaoControllerEntidade getAtividadeAutonomaCrmCtrConclusaoAcaoDeMudancaRelacionamento() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_ACAO_DE_MUDANCA_RELACIONAMENTO");
	}

	public ComoAcaoControllerEntidade getAtividadeAutonomaCrmCtrConclusaoDispararTransacional() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_DISPARAR_TRANSACIONAL");
	}
}