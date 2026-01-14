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
public class AcoesAgenda_TipoReservaMb implements Serializable {

	public ItfAcaoFormulario getTipoReservaMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.TIPO_RESERVA_MB");
	}

	public ItfAcaoFormularioEntidade getTipoReservaFrmNovoRemoto() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.TIPO_RESERVA_FRM_NOVO_REMOTO");
	}

	public ItfAcaoFormularioEntidade getTipoReservaFrmNovoPresencial() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.TIPO_RESERVA_FRM_NOVO_PRESENCIAL");
	}

	public ItfAcaoFormularioEntidade getTipoReservaFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.TIPO_RESERVA_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getTipoReservaFrmEditarPresencial() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.TIPO_RESERVA_FRM_EDITAR_PRESENCIAL");
	}

	public ItfAcaoFormularioEntidade getTipoReservaFrmEditarRemoto() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.TIPO_RESERVA_FRM_EDITAR_REMOTO");
	}

	public ItfAcaoFormularioEntidade getTipoReservaFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.TIPO_RESERVA_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getTipoReservaCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.TIPO_RESERVA_CTR_SALVAR_MERGE");
	}
}