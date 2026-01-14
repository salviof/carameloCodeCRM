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
public class AcoesAdminCrm_CategoriaDocumentoEquipeMbGestao
		implements
			Serializable {

	public ItfAcaoFormulario getCategoriaDocumentoEquipeMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_EQUIPE_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getCategoriaDocumentoEquipeFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_EQUIPE_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getCategoriaDocumentoEquipeFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_EQUIPE_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getCategoriaDocumentoEquipeFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_EQUIPE_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getCategoriaDocumentoEquipeFrmNovoSubDiretorio() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_EQUIPE_FRM_NOVO_SUB_DIRETORIO");
	}

	public ItfAcaoFormularioEntidade getCategoriaDocumentoEquipeFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_EQUIPE_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getCategoriaDocumentoEquipeCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_EQUIPE_CTR_SALVAR_MERGE");
	}
}