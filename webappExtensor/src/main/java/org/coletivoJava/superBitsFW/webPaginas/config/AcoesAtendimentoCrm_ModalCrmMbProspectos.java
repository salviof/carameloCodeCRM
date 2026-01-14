package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;

@Named
@ApplicationScoped
public class AcoesAtendimentoCrm_ModalCrmMbProspectos implements Serializable {

	public ItfAcaoFormulario getModalCrmMbProspectos() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MODAL_CRM_MB_PROSPECTOS");
	}

	public ItfAcaoFormularioEntidade getModalCrmFrmEdicaoRapida() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MODAL_CRM_FRM_EDICAO_RAPIDA");
	}

	public ItfAcaoFormularioEntidade getModalCrmFrmDadosDinamicos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MODAL_CRM_FRM_DADOS_DINAMICOS");
	}

	public ItfAcaoFormularioEntidade getModalCrmFrmFluxo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MODAL_CRM_FRM_FLUXO");
	}

	public ItfAcaoFormularioEntidade getModalCrmFrmCompartilharProspecto() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MODAL_CRM_FRM_COMPARTILHAR_PROSPECTO");
	}

	public ItfAcaoFormularioEntidade getModalCrmFrmExibirAtividades() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MODAL_CRM_FRM_EXIBIR_ATIVIDADES");
	}

	public ItfAcaoFormularioEntidade getModalCrmFrmContatos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MODAL_CRM_FRM_CONTATOS");
	}

	public ItfAcaoFormularioEntidade getModalCrmFrmContatoNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MODAL_CRM_FRM_CONTATO_NOVO");
	}

	public ItfAcaoFormularioEntidade getModalCrmFrmEmailRapido() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MODAL_CRM_FRM_EMAIL_RAPIDO");
	}

	public ItfAcaoFormularioEntidade getModalCrmFrmAnexos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MODAL_CRM_FRM_ANEXOS");
	}

	public ItfAcaoFormularioEntidade getModalCrmFrmOpcoesAvancado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MODAL_CRM_FRM_OPCOES_AVANCADO");
	}
}