package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;

@Named
@ApplicationScoped
public class AcoesAdminCrm_ExecucaoIaMbGestao implements Serializable {

	public ItfAcaoFormulario getExecucaoIaMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMIA.EXECUCAO_IA_MB_GESTAO");
	}

	public ComoAcaoControllerEntidade getExecucaoIaCtrExecutarDialogo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMIA.EXECUCAO_IA_CTR_EXECUTAR_DIALOGO");
	}

	public ComoAcaoControllerEntidade getExecucaoIaCtrAtualizarModeloDocumento() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMIA.EXECUCAO_IA_CTR_ATUALIZAR_MODELO_DOCUMENTO");
	}

	public ComoAcaoControllerEntidade getExecucaoIaCtrCriarDialogoAtividade() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMIA.EXECUCAO_IA_CTR_CRIAR_DIALOGO_ATIVIDADE");
	}

	public ComoAcaoControllerEntidade getExecucaoIaCtrCriarDialogoModeloEmailMarketing() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMIA.EXECUCAO_IA_CTR_CRIAR_DIALOGO_MODELO_EMAIL_MARKETING");
	}

	public ComoAcaoControllerEntidade getExecucaoIaCtrCriarDialogoModeloEmail() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMIA.EXECUCAO_IA_CTR_CRIAR_DIALOGO_MODELO_EMAIL");
	}
}