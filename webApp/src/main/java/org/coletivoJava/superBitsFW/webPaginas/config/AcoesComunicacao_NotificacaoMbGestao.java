package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;

@Named
@ApplicationScoped
public class AcoesComunicacao_NotificacaoMbGestao implements Serializable {

	public ItfAcaoFormulario getNotificacaoMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.NOTIFICACAO_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getNotificacaoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.NOTIFICACAO_FRM_LISTAR");
	}

	public ComoAcaoControllerEntidade getNotificacaoCtrRegistrarNotificacao() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_REGISTRAR_NOTIFICACAO");
	}

	public ComoAcaoControllerEntidade getNotificacaoCtrEnviarNotificacaoRegistrada() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_ENVIAR_NOTIFICACAO_REGISTRADA");
	}

	public ComoAcaoController getNotificacaoCtrProcessarNotificacoesAguardandoEnvioAutoExec() {
		return (ComoAcaoController) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_PROCESSAR_NOTIFICACOES_AGUARDANDO_ENVIO_AUTO_EXEC");
	}

	public ComoAcaoController getNotificacaoCtrProcessarNotificacoesAguardandoLeituraAutoExec() {
		return (ComoAcaoController) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_PROCESSAR_NOTIFICACOES_AGUARDANDO_LEITURA_AUTO_EXEC");
	}

	public ComoAcaoController getNotificacaoCtrProcessarNotificacoesAguardandoLimpezaAutoExec() {
		return (ComoAcaoController) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.NOTIFICACAO_CTR_PROCESSAR_NOTIFICACOES_AGUARDANDO_LIMPEZA_AUTO_EXEC");
	}
}