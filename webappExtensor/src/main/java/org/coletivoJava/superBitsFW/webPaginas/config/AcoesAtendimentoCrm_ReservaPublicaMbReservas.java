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
public class AcoesAtendimentoCrm_ReservaPublicaMbReservas
		implements
			Serializable {

	public ItfAcaoFormulario getReservaPublicaMbReservas() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_MB_RESERVAS");
	}

	public ItfAcaoFormularioEntidade getReservaPublicaFrmSemHorariosDisponiveis() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_SEM_HORARIOS_DISPONIVEIS");
	}

	public ItfAcaoFormularioEntidade getReservaPublicaFrmListarHorarios() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_LISTAR_HORARIOS");
	}

	public ItfAcaoFormularioEntidade getReservaPublicaFrmMeusDados() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_MEUS_DADOS");
	}

	public ItfAcaoFormularioEntidade getReservaPublicaFrmReservaConcluida() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_RESERVA_CONCLUIDA");
	}

	public ComoAcaoControllerEntidade getReservaPublicaCtrCancelar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_CTR_CANCELAR");
	}

	public ComoAcaoControllerEntidade getReservaPublicaCtrConfirmar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_CTR_CONFIRMAR");
	}

	public ComoAcaoControllerEntidade getReservaPublicaCtrReservar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_CTR_RESERVAR");
	}

	public ComoAcaoControllerEntidade getReservaPublicaCtrPublicar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_CTR_PUBLICAR");
	}
}