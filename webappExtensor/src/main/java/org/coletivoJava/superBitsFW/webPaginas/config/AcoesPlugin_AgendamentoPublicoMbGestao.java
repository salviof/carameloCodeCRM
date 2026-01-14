package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;

@Named
@ApplicationScoped
public class AcoesPlugin_AgendamentoPublicoMbGestao implements Serializable {

	public ItfAcaoFormulario getAgendamentoPublicoMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendamentoPublicoCRMPlugin.AGENDAMENTO_PUBLICO_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getAgendamentoPublicoFrmAtenderRemoto() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendamentoPublicoCRMPlugin.AGENDAMENTO_PUBLICO_FRM_ATENDER_REMOTO");
	}

	public ItfAcaoFormularioEntidade getAgendamentoPublicoFrmAtenderNoLocal() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgendamentoPublicoCRMPlugin.AGENDAMENTO_PUBLICO_FRM_ATENDER_NO_LOCAL");
	}
}