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
public class AcoesClienteContato_ReservasMbGestao implements Serializable {

	public ItfAcaoFormulario getReservasMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getReservasFrmSemAtendimento() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_FRM_SEM_ATENDIMENTO");
	}

	public ItfAcaoFormularioEntidade getReservasFrmEscolhaAtendente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_FRM_ESCOLHA_ATENDENTE");
	}

	public ItfAcaoFormularioEntidade getReservasFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getReservasFrmHorariosDisponiveis() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_FRM_HORARIOS_DISPONIVEIS");
	}

	public ItfAcaoFormularioEntidade getReservasFrmEscolherTipoDeReserva() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_FRM_ESCOLHER_TIPO_DE_RESERVA");
	}

	public ItfAcaoFormularioEntidade getReservasFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_FRM_VISUALIZAR");
	}

	public ItfAcaoFormularioEntidade getReservasFrmVisualizarRemoto() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_FRM_VISUALIZAR_REMOTO");
	}

	public ItfAcaoFormularioEntidade getReservasFrmVisualizarPresencial() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_FRM_VISUALIZAR_PRESENCIAL");
	}

	public ComoAcaoControllerEntidade getReservasCtrReservar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_CTR_RESERVAR");
	}

	public ItfAcaoFormularioEntidade getReservasFrmCancelar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_FRM_CANCELAR");
	}

	public ComoAcaoControllerEntidade getReservasCtrCancelar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_CTR_CANCELAR");
	}

	public ComoAcaoControllerEntidade getReservasCtrConfirmar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.RESERVAS_CTR_CONFIRMAR");
	}
}