package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;

@Named
@ApplicationScoped
public class AcoesAgenda_ReservasAdminMbGestao implements Serializable {

	public ItfAcaoFormulario getReservasAdminMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAdminAgenda.RESERVAS_ADMIN_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getReservasAdminFrmTiposReservas() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAdminAgenda.RESERVAS_ADMIN_FRM_TIPOS_RESERVAS");
	}

	public ItfAcaoFormularioEntidade getReservasAdminFrmListarReservasDisponiveis() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAdminAgenda.RESERVAS_ADMIN_FRM_LISTAR_RESERVAS_DISPONIVEIS");
	}

	public ItfAcaoFormularioEntidade getReservasAdminFrmListarReservasRegistradas() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAdminAgenda.RESERVAS_ADMIN_FRM_LISTAR_RESERVAS_REGISTRADAS");
	}
}