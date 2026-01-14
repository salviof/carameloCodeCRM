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
public class AcoesAtendimentoCrm_CalendarioGoogleMb implements Serializable {

	public ItfAcaoFormulario getCalendarioGoogleMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_MB");
	}

	public ItfAcaoFormularioEntidade getCalendarioGoogleFrmAutenticar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_AUTENTICAR");
	}

	public ItfAcaoFormularioEntidade getCalendarioGoogleFrmAutenticadoComSucesso() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_AUTENTICADO_COM_SUCESSO");
	}

	public ItfAcaoFormularioEntidade getCalendarioGoogleFrmAutenticadoComFalha() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_AUTENTICADO_COM_FALHA");
	}

	public ItfAcaoFormularioEntidade getCalendarioGoogleFrmSincronizacao() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_FRM_SINCRONIZACAO");
	}

	public ComoAcaoControllerEntidade getCalendarioGoogleCtrSincronizarGoogleServer() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoGoogleCalendario.CALENDARIO_GOOGLE_CTR_SINCRONIZAR_GOOGLE_SERVER");
	}
}