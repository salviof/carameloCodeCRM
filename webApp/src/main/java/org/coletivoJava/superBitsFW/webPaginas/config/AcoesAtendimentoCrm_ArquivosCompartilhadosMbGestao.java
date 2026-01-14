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
public class AcoesAtendimentoCrm_ArquivosCompartilhadosMbGestao
		implements
			Serializable {

	public ItfAcaoFormulario getArquivosCompartilhadosMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.ARQUIVOS_COMPARTILHADOS_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getArquivosCompartilhadosFrmListarPessoas() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.ARQUIVOS_COMPARTILHADOS_FRM_LISTAR_PESSOAS");
	}

	public ItfAcaoFormularioEntidade getArquivosCompartilhadosFrmListarPastas() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.ARQUIVOS_COMPARTILHADOS_FRM_LISTAR_PASTAS");
	}

	public ItfAcaoFormularioEntidade getArquivosCompartilhadosFrmListarArquivosPasta() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.ARQUIVOS_COMPARTILHADOS_FRM_LISTAR_ARQUIVOS_PASTA");
	}

	public ComoAcaoControllerEntidade getArquivosCompartilhadosCtrAtualizarArquivo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.ARQUIVOS_COMPARTILHADOS_CTR_ATUALIZAR_ARQUIVO");
	}

	public ComoAcaoControllerEntidade getArquivosCompartilhadosCtrRemoverArquivo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.ARQUIVOS_COMPARTILHADOS_CTR_REMOVER_ARQUIVO");
	}
}