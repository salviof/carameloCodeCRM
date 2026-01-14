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
public class AcoesAtendimentoCrm_FormularioConvidadoMbConvite
		implements
			Serializable {

	public ItfAcaoFormulario getFormularioConvidadoMbConvite() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.FORMULARIO_CONVIDADO_MB_CONVITE");
	}

	public ItfAcaoFormularioEntidade getFormularioConvidadoFrmColetar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.FORMULARIO_CONVIDADO_FRM_COLETAR");
	}

	public ItfAcaoFormularioEntidade getFormularioConvidadoFrmObservacao() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.FORMULARIO_CONVIDADO_FRM_OBSERVACAO");
	}

	public ItfAcaoFormularioEntidade getFormularioConvidadoFrmObrigado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.FORMULARIO_CONVIDADO_FRM_OBRIGADO");
	}

	public ComoAcaoControllerEntidade getFormularioConvidadoCtrConcluir() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.FORMULARIO_CONVIDADO_CTR_CONCLUIR");
	}
}