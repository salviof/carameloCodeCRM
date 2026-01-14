package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;

@Named
@ApplicationScoped
public class AcoesPaginasDoSistema_PaginaNativaJsonWebviewMbGestao
		implements
			Serializable {

	public ItfAcaoFormulario getPaginaNativaJsonWebviewMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoPaginasDoSistema.PAGINA_NATIVA_JSON_WEBVIEW_MB_GESTAO");
	}

	public ComoAcaoControllerEntidade getPaginaNativaJsonWebviewCtrRegistrarDispositivo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoPaginasDoSistema.PAGINA_NATIVA_JSON_WEBVIEW_CTR_REGISTRAR_DISPOSITIVO");
	}
}