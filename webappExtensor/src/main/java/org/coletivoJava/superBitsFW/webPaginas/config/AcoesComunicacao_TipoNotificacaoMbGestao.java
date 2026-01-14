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
public class AcoesComunicacao_TipoNotificacaoMbGestao implements Serializable {

	public ItfAcaoFormulario getTipoNotificacaoMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.TIPO_NOTIFICACAO_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getTipoNotificacaoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.TIPO_NOTIFICACAO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getTipoNotificacaoFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.TIPO_NOTIFICACAO_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getTipoNotificacaoFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.TIPO_NOTIFICACAO_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getTipoNotificacaoCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.TIPO_NOTIFICACAO_CTR_SALVAR_MERGE");
	}
}