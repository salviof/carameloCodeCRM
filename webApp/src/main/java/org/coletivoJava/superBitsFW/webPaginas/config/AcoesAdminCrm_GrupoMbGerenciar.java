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
public class AcoesAdminCrm_GrupoMbGerenciar implements Serializable {

	public ItfAcaoFormulario getGrupoMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getGrupoFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getGrupoFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getGrupoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getGrupoFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getGrupoCtrAlterarStatus() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_CTR_ALTERAR_STATUS");
	}

	public ComoAcaoControllerEntidade getGrupoCtrAlterarStatusGrupoSk() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_CTR_ALTERAR_STATUS_GRUPO_SK");
	}

	public ItfAcaoFormularioEntidade getGrupoFrmEditarPermissoes() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_FRM_EDITAR_PERMISSOES");
	}

	public ItfAcaoFormularioEntidade getGrupoFrmEditarPermissoesSk() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_FRM_EDITAR_PERMISSOES_SK");
	}

	public ComoAcaoControllerEntidade getGrupoCtrSalvarPermissoes() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_CTR_SALVAR_PERMISSOES");
	}

	public ItfAcaoFormularioEntidade getGrupoFrmListarUsuarios() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_FRM_LISTAR_USUARIOS");
	}

	public ComoAcaoControllerEntidade getGrupoCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_CTR_SALVAR_MERGE");
	}
}