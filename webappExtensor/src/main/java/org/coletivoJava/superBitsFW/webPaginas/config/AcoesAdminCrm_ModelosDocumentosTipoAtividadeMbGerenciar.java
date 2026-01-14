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
public class AcoesAdminCrm_ModelosDocumentosTipoAtividadeMbGerenciar
		implements
			Serializable {

	public ItfAcaoFormulario getModelosDocumentosTipoAtividadeMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getModelosDocumentosTipoAtividadeFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getModelosDocumentosTipoAtividadeFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getModelosDocumentosTipoAtividadeFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getModelosDocumentosTipoAtividadeFrmEditarDadosLead() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_FRM_EDITAR_DADOS_LEAD");
	}

	public ComoAcaoControllerEntidade getModelosDocumentosTipoAtividadeCtrMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_MERGE");
	}

	public ComoAcaoControllerEntidade getModelosDocumentosTipoAtividadeCtrGerarTeste() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_GERAR_TESTE");
	}

	public ComoAcaoControllerEntidade getModelosDocumentosTipoAtividadeCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_REMOVER");
	}

	public ComoAcaoControllerEntidade getModelosDocumentosTipoAtividadeCtrAtualizarProspectoTeste() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_ATUALIZAR_PROSPECTO_TESTE");
	}
}