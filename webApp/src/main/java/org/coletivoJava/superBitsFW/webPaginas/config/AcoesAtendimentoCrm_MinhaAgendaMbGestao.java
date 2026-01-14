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
public class AcoesAtendimentoCrm_MinhaAgendaMbGestao implements Serializable {

	public ItfAcaoFormulario getMinhaAgendaMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getMinhaAgendaFrmEscopoReservaCliente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_ESCOPO_RESERVA_CLIENTE");
	}

	public ComoAcaoControllerEntidade getMinhaAgendaCtrEscopoReservaClienteSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_ESCOPO_RESERVA_CLIENTE_SALVAR_MERGE");
	}

	public ItfAcaoFormularioEntidade getMinhaAgendaFrmVisaoGeral() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_VISAO_GERAL");
	}

	public ItfAcaoFormularioEntidade getMinhaAgendaFrmNovaReserva() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_NOVA_RESERVA");
	}

	public ItfAcaoFormularioEntidade getMinhaAgendaFrmEditarReservaNoLocal() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_EDITAR_RESERVA_NO_LOCAL");
	}

	public ItfAcaoFormularioEntidade getMinhaAgendaFrmEditarReservaConferencia() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_EDITAR_RESERVA_CONFERENCIA");
	}

	public ComoAcaoControllerEntidade getMinhaAgendaCtrSalvarReservaMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_SALVAR_RESERVA_MERGE");
	}

	public ItfAcaoFormularioEntidade getMinhaAgendaFrmDefinirUrlReuniao() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_DEFINIR_URL_REUNIAO");
	}

	public ComoAcaoControllerEntidade getMinhaAgendaCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_REMOVER");
	}

	public ComoAcaoControllerEntidade getMinhaAgendaCtrEnviarLinkReuniao() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_ENVIAR_LINK_REUNIAO");
	}

	public ItfAcaoFormularioEntidade getMinhaAgendaFrmVerReserva() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_VER_RESERVA");
	}

	public ItfAcaoFormularioEntidade getMinhaAgendaFrmOfertarReserva() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_OFERTAR_RESERVA");
	}

	public ComoAcaoControllerEntidade getMinhaAgendaCtrOfertarReserva() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_OFERTAR_RESERVA");
	}

	public ComoAcaoControllerEntidade getMinhaAgendaCtrNotificarOferta() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_NOTIFICAR_OFERTA");
	}

	public ComoAcaoControllerEntidade getMinhaAgendaCtrNotificarCompromisso() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_NOTIFICAR_COMPROMISSO");
	}

	public ItfAcaoFormularioEntidade getMinhaAgendaFrmAgendarNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_AGENDAR_NOVO");
	}

	public ComoAcaoControllerEntidade getMinhaAgendaCtrAgendar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_CTR_AGENDAR");
	}
}