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
public class AcoesAdminCrm_DocumentoApresentacaoMb implements Serializable {

	public ItfAcaoFormulario getDocumentoApresentacaoMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_MB");
	}

	public ItfAcaoFormularioEntidade getDocumentoApresentacaoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getDocumentoApresentacaoFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getDocumentoApresentacaoFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_FRM_NOVO");
	}

	public ComoAcaoControllerEntidade getDocumentoApresentacaoCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_CTR_SALVAR_MERGE");
	}

	public ComoAcaoControllerEntidade getDocumentoApresentacaoCtrUploadApresentacoes() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_CTR_UPLOAD_APRESENTACOES");
	}

	public ComoAcaoControllerEntidade getDocumentoApresentacaoCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_CTR_REMOVER");
	}

	public ComoAcaoControllerEntidade getDocumentoApresentacaoCtrAtivarDesativar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_CTR_ATIVAR_DESATIVAR");
	}
}