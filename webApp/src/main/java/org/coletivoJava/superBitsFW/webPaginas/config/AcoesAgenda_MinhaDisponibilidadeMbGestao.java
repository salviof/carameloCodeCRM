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
public class AcoesAgenda_MinhaDisponibilidadeMbGestao implements Serializable {

	public ItfAcaoFormulario getMinhaDisponibilidadeMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getMinhaDisponibilidadeFrmListarMinasDisponibilidades() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_FRM_LISTAR_MINAS_DISPONIBILIDADES");
	}

	public ItfAcaoFormularioEntidade getMinhaDisponibilidadeFrmListarTodas() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_FRM_LISTAR_TODAS");
	}

	public ItfAcaoFormularioEntidade getMinhaDisponibilidadeFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getMinhaDisponibilidadeFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_FRM_VISUALIZAR");
	}

	public ItfAcaoFormularioEntidade getMinhaDisponibilidadeFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_FRM_NOVO");
	}

	public ComoAcaoControllerEntidade getMinhaDisponibilidadeCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_CTR_SALVAR_MERGE");
	}
}