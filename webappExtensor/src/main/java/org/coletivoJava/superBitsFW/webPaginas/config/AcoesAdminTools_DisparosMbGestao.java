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
public class AcoesAdminTools_DisparosMbGestao implements Serializable {

	public ItfAcaoFormulario getDisparosMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.DISPAROS_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getDisparosFrmListarPorTipoCanal() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.DISPAROS_FRM_LISTAR_POR_TIPO_CANAL");
	}

	public ComoAcaoControllerEntidade getDisparosCtrProgramar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.DISPAROS_CTR_PROGRAMAR");
	}

	public ComoAcaoControllerEntidade getDisparosCtrDispararProgramado() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoNotificacaoPadraoSB.DISPAROS_CTR_DISPARAR_PROGRAMADO");
	}
}