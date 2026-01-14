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
public class AcoesAtendimentoCrm_DocumentosPessoaMbGestao
		implements
			Serializable {

	public ItfAcaoFormulario getDocumentosPessoaMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getDocumentosPessoaFrmListarPastasCliente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_FRM_LISTAR_PASTAS_CLIENTE");
	}

	public ItfAcaoFormularioEntidade getDocumentosPessoaFrmListarArquivosPastaCliente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_FRM_LISTAR_ARQUIVOS_PASTA_CLIENTE");
	}

	public ItfAcaoFormularioEntidade getDocumentosPessoaFrmListarArquivosPastaEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_FRM_LISTAR_ARQUIVOS_PASTA_EQUIPE");
	}

	public ItfAcaoFormularioEntidade getDocumentosPessoaFrmListarPastasEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_FRM_LISTAR_PASTAS_EQUIPE");
	}

	public ItfAcaoFormularioEntidade getDocumentosPessoaFrmNovaSubPasta() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_FRM_NOVA_SUB_PASTA");
	}

	public ItfAcaoFormularioEntidade getDocumentosPessoaFrmCompartilhar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_FRM_COMPARTILHAR");
	}

	public ComoAcaoControllerEntidade getDocumentosPessoaCtrCompartilhar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_CTR_COMPARTILHAR");
	}

	public ComoAcaoControllerEntidade getDocumentosPessoaCtrSalvarMergeCliente() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_CTR_SALVAR_MERGE_CLIENTE");
	}

	public ComoAcaoControllerEntidade getDocumentosPessoaCtrSalvarMergeEquipe() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_CTR_SALVAR_MERGE_EQUIPE");
	}
}