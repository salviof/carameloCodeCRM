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
public class AcoesAgenda_EscopoAgendamentoPublicoMb implements Serializable {

	public ItfAcaoFormulario getEscopoAgendamentoPublicoMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_MB");
	}

	public ItfAcaoFormularioEntidade getEscopoAgendamentoPublicoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getEscopoAgendamentoPublicoFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getEscopoAgendamentoPublicoFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_FRM_EDITAR");
	}

	public ComoAcaoControllerEntidade getEscopoAgendamentoPublicoCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_CTR_SALVAR_MERGE");
	}

	public ComoAcaoControllerEntidade getEscopoAgendamentoPublicoCtrAtivar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_CTR_ATIVAR");
	}

	public ComoAcaoControllerEntidade getEscopoAgendamentoPublicoCtrDesativar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_CTR_DESATIVAR");
	}

	public ComoAcaoControllerEntidade getEscopoAgendamentoPublicoCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_CTR_REMOVER");
	}
}