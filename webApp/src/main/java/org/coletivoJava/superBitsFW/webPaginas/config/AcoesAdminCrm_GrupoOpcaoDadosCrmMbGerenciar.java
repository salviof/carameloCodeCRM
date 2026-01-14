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
public class AcoesAdminCrm_GrupoOpcaoDadosCrmMbGerenciar
		implements
			Serializable {

	public ItfAcaoFormulario getGrupoOpcaoDadosCrmMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_OPCAO_DADOS_CRM_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getGrupoOpcaoDadosCrmFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_OPCAO_DADOS_CRM_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getGrupoOpcaoDadosCrmFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_OPCAO_DADOS_CRM_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getGrupoOpcaoDadosCrmFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_OPCAO_DADOS_CRM_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getGrupoOpcaoDadosCrmFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_OPCAO_DADOS_CRM_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getGrupoOpcaoDadosCrmCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_OPCAO_DADOS_CRM_CTR_SALVAR_MERGE");
	}

	public ComoAcaoControllerEntidade getGrupoOpcaoDadosCrmCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.GRUPO_OPCAO_DADOS_CRM_CTR_REMOVER");
	}
}