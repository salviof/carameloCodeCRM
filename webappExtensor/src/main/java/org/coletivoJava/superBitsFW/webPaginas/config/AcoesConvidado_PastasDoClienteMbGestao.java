package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;

@Named
@ApplicationScoped
public class AcoesConvidado_PastasDoClienteMbGestao implements Serializable {

	public ItfAcaoFormulario getPastasDoClienteMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMConvidado.PASTAS_DO_CLIENTE_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getPastasDoClienteFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMConvidado.PASTAS_DO_CLIENTE_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getPastasDoClienteFrmVer() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMConvidado.PASTAS_DO_CLIENTE_FRM_VER");
	}
}