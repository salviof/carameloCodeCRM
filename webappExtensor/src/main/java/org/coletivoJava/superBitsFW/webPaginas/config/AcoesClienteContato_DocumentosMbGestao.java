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
public class AcoesClienteContato_DocumentosMbGestao implements Serializable {

	public ItfAcaoFormulario getDocumentosMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.DOCUMENTOS_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getDocumentosFrmListarPastas() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.DOCUMENTOS_FRM_LISTAR_PASTAS");
	}

	public ItfAcaoFormularioEntidade getDocumentosFrmListarArquivosPasta() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.DOCUMENTOS_FRM_LISTAR_ARQUIVOS_PASTA");
	}

	public ComoAcaoControllerEntidade getDocumentosCtrBaixarArquivo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.DOCUMENTOS_CTR_BAIXAR_ARQUIVO");
	}

	public ComoAcaoControllerEntidade getDocumentosCtrVisualizarArquivo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.DOCUMENTOS_CTR_VISUALIZAR_ARQUIVO");
	}

	public ComoAcaoControllerEntidade getDocumentosCtrEnviarArquivo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.DOCUMENTOS_CTR_ENVIAR_ARQUIVO");
	}
}