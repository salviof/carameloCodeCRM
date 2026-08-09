package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;

@Named
@ApplicationScoped
public class AcoesClienteContato_SolicitacaoMbGestao implements Serializable {

	public ItfAcaoFormulario getSolicitacaoMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.SOLICITACAO_MB_GESTAO");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrAbrirFormularioResolucao() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmEnviarArquivo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.SOLICITACAO_FRM_ENVIAR_ARQUIVO");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrEnviarArquivo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.SOLICITACAO_CTR_ENVIAR_ARQUIVO");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmAceite() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.SOLICITACAO_FRM_ACEITE");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrResponderAceite() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.SOLICITACAO_CTR_RESPONDER_ACEITE");
	}
}