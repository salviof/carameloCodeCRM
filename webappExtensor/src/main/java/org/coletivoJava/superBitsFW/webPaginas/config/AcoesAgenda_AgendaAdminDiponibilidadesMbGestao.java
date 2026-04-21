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
public class AcoesAgenda_AgendaAdminDiponibilidadesMbGestao
		implements
			Serializable {

	public ItfAcaoFormulario getAgendaAdminDiponibilidadesMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAdminAgenda.AGENDA_ADMIN_DIPONIBILIDADES_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getAgendaAdminDiponibilidadesFrmListarDisponibilidades() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAdminAgenda.AGENDA_ADMIN_DIPONIBILIDADES_FRM_LISTAR_DISPONIBILIDADES");
	}

	public ItfAcaoFormularioEntidade getAgendaAdminDiponibilidadesFrmEditarDisponibilidade() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAdminAgenda.AGENDA_ADMIN_DIPONIBILIDADES_FRM_EDITAR_DISPONIBILIDADE");
	}

	public ItfAcaoFormularioEntidade getAgendaAdminDiponibilidadesFrmNovoDisponibilidade() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAdminAgenda.AGENDA_ADMIN_DIPONIBILIDADES_FRM_NOVO_DISPONIBILIDADE");
	}

	public ComoAcaoControllerEntidade getAgendaAdminDiponibilidadesCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAdminAgenda.AGENDA_ADMIN_DIPONIBILIDADES_CTR_SALVAR_MERGE");
	}
}