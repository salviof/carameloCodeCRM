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
public class AcoesAdminCrm_IntegracaoErpMbGestao implements Serializable {

	public ItfAcaoFormulario getIntegracaoErpMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.INTEGRACAO_ERP_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getIntegracaoErpFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.INTEGRACAO_ERP_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getIntegracaoErpFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.INTEGRACAO_ERP_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getIntegracaoErpFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.INTEGRACAO_ERP_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getIntegracaoErpFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.INTEGRACAO_ERP_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getIntegracaoErpCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.INTEGRACAO_ERP_CTR_SALVAR_MERGE");
	}
}