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
public class AcoesAtendimentoCrm_EmailsMbGestao implements Serializable {

	public ItfAcaoFormulario getEmailsMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmEmailsDoProspecto() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_EMAILS_DO_PROSPECTO");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmListarUltmosEmails() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_ULTMOS_EMAILS");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmListarEnviados() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_ENVIADOS");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmListarRecebido() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_RECEBIDO");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmListarRascunhos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_RASCUNHOS");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmVisualizarEmail() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_VISUALIZAR_EMAIL");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmResponder() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_RESPONDER");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmResponderEnvandoModelo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_RESPONDER_ENVANDO_MODELO");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmResponderEditandoModelo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_RESPONDER_EDITANDO_MODELO");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmAgendarEnvio() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_AGENDAR_ENVIO");
	}

	public ComoAcaoControllerEntidade getEmailsCtrSolicitarRevisao() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_SOLICITAR_REVISAO");
	}

	public ComoAcaoControllerEntidade getEmailsCtrConcluirRevisao() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_CONCLUIR_REVISAO");
	}

	public ComoAcaoControllerEntidade getEmailsCtrAgendarEnvio() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_AGENDAR_ENVIO");
	}

	public ComoAcaoControllerEntidade getEmailsCtrBloquearRemetente() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_BLOQUEAR_REMETENTE");
	}

	public ComoAcaoControllerEntidade getEmailsCtrRelatarLeitura() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_RELATAR_LEITURA");
	}

	public ComoAcaoControllerEntidade getEmailsCtrRelatarSpan() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_RELATAR_SPAN");
	}

	public ComoAcaoControllerEntidade getEmailsCtrAtribuirPesssoa() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_ATRIBUIR_PESSSOA");
	}

	public ComoAcaoControllerEntidade getEmailsCtrAtribuirPrivado() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_ATRIBUIR_PRIVADO");
	}

	public ComoAcaoControllerEntidade getEmailsCtrEnviarSalvandoRascunho() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_ENVIAR_SALVANDO_RASCUNHO");
	}

	public ComoAcaoControllerEntidade getEmailsCtrEnviarAgora() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_ENVIAR_AGORA");
	}

	public ComoAcaoControllerEntidade getEmailsCtrSalvarRascunho() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_SALVAR_RASCUNHO");
	}

	public ComoAcaoControllerEntidade getEmailsCtrRemoverEmail() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_REMOVER_EMAIL");
	}

	public ComoAcaoControllerEntidade getEmailsCtrRemoverEmailEnviado() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_REMOVER_EMAIL_ENVIADO");
	}

	public ComoAcaoControllerEntidade getEmailsCtrRemoverEmailDeAtividade() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_REMOVER_EMAIL_DE_ATIVIDADE");
	}

	public ComoAcaoControllerEntidade getEmailsCtrRemoverEmailRecebido() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_CTR_REMOVER_EMAIL_RECEBIDO");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmComplementarResposta() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_COMPLEMENTAR_RESPOSTA");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmEncaminhar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_ENCAMINHAR");
	}

	public ItfAcaoFormularioEntidade getEmailsFrmConfiguracoes() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.EMAILS_FRM_CONFIGURACOES");
	}
}