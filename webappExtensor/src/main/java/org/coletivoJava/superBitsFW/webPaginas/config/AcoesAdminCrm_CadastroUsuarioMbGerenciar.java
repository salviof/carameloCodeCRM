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
public class AcoesAdminCrm_CadastroUsuarioMbGerenciar implements Serializable {

	public ItfAcaoFormulario getCadastroUsuarioMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CADASTRO_USUARIO_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getCadastroUsuarioFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CADASTRO_USUARIO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getCadastroUsuarioFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CADASTRO_USUARIO_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getCadastroUsuarioFrmEditarSenha() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CADASTRO_USUARIO_FRM_EDITAR_SENHA");
	}

	public ItfAcaoFormularioEntidade getCadastroUsuarioFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CADASTRO_USUARIO_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getCadastroUsuarioFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CADASTRO_USUARIO_FRM_VISUALIZAR");
	}

	public ItfAcaoFormularioEntidade getCadastroUsuarioFrmTagsAcompanhamento() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CADASTRO_USUARIO_FRM_TAGS_ACOMPANHAMENTO");
	}

	public ComoAcaoControllerEntidade getCadastroUsuarioCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CADASTRO_USUARIO_CTR_SALVAR_MERGE");
	}

	public ComoAcaoControllerEntidade getCadastroUsuarioCtrAlterarStatus() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CADASTRO_USUARIO_CTR_ALTERAR_STATUS");
	}
}