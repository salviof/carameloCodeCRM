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
public class AcoesAdminCrm_ModeloEmailMarketingMbGestao implements Serializable {

	public ItfAcaoFormulario getModeloEmailMarketingMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getModeloEmailMarketingFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getModeloEmailMarketingFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getModeloEmailMarketingFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getModeloEmailMarketingFrmEditarComIa() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_FRM_EDITAR_COM_IA");
	}

	public ComoAcaoControllerEntidade getModeloEmailMarketingCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_CTR_SALVAR_MERGE");
	}

	public ComoAcaoControllerEntidade getModeloEmailMarketingCtrEnviarParaMautic() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MODELO_EMAIL_MARKETING_CTR_ENVIAR_PARA_MAUTIC");
	}
}