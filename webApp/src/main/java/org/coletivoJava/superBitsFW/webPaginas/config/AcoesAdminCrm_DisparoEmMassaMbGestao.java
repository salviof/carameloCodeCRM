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
public class AcoesAdminCrm_DisparoEmMassaMbGestao implements Serializable {

	public ItfAcaoFormulario getDisparoEmMassaMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DISPARO_EM_MASSA_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getDisparoEmMassaFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DISPARO_EM_MASSA_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getDisparoEmMassaFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DISPARO_EM_MASSA_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getDisparoEmMassaFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DISPARO_EM_MASSA_FRM_EDITAR");
	}

	public ComoAcaoControllerEntidade getDisparoEmMassaCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DISPARO_EM_MASSA_CTR_SALVAR_MERGE");
	}

	public ComoAcaoControllerEntidade getDisparoEmMassaCtrDisparar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DISPARO_EM_MASSA_CTR_DISPARAR");
	}

	public ComoAcaoControllerEntidade getDisparoEmMassaCtrExcluir() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DISPARO_EM_MASSA_CTR_EXCLUIR");
	}
}