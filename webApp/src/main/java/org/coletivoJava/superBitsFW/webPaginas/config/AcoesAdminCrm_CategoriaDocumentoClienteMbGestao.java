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
public class AcoesAdminCrm_CategoriaDocumentoClienteMbGestao
		implements
			Serializable {

	public ItfAcaoFormulario getCategoriaDocumentoClienteMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_CLIENTE_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getCategoriaDocumentoClienteFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_CLIENTE_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getCategoriaDocumentoClienteFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_CLIENTE_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getCategoriaDocumentoClienteFrmNovoSubDiretorio() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_CLIENTE_FRM_NOVO_SUB_DIRETORIO");
	}

	public ItfAcaoFormularioEntidade getCategoriaDocumentoClienteFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_CLIENTE_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getCategoriaDocumentoClienteFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_CLIENTE_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getCategoriaDocumentoClienteCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_CLIENTE_CTR_SALVAR_MERGE");
	}
}