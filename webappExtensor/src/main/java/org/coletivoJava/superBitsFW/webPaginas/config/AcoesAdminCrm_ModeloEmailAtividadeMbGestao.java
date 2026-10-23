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
public class AcoesAdminCrm_ModeloEmailAtividadeMbGestao implements Serializable {

	public ItfAcaoFormulario getModeloEmailAtividadeMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_ATIVIDADE_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getModeloEmailAtividadeFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_ATIVIDADE_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getModeloEmailAtividadeFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_ATIVIDADE_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getModeloEmailAtividadeFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_ATIVIDADE_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getModeloEmailAtividadeCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_ATIVIDADE_CTR_SALVAR_MERGE");
	}

	public ComoAcaoControllerEntidade getModeloEmailAtividadeCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_ATIVIDADE_CTR_REMOVER");
	}
}