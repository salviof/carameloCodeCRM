package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;

@Named
@ApplicationScoped
public class AcoesAtendimentoCrm_MeusCompromissosMb implements Serializable {

	public ItfAcaoFormulario getMeusCompromissosMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_COMPROMISSOS_MB");
	}

	public ItfAcaoFormularioEntidade getMeusCompromissosFrmHoje() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_COMPROMISSOS_FRM_HOJE");
	}

	public ItfAcaoFormularioEntidade getMeusCompromissosFrmVer60Dias() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_COMPROMISSOS_FRM_VER_60_DIAS");
	}

	public ItfAcaoFormularioEntidade getMeusCompromissosFrmAgendaCompleta() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_COMPROMISSOS_FRM_AGENDA_COMPLETA");
	}
}