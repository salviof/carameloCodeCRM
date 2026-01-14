package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;

@Named
@ApplicationScoped
public class AcoesAtendimentoCrm_MeusProspectosMbGerenciar
		implements
			Serializable {

	public ItfAcaoFormulario getMeusProspectosMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_PROSPECTOS_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getMeusProspectosFrmListarAtivos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR_ATIVOS");
	}

	public ItfAcaoFormularioEntidade getMeusProspectosFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getMeusProspectosFrmListarUrgentes() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR_URGENTES");
	}

	public ItfAcaoFormularioEntidade getMeusProspectosFrmListarAtividadesPendentes() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR_ATIVIDADES_PENDENTES");
	}
}