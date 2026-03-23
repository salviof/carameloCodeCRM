package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;

@Named
@ApplicationScoped
public class AcoesCarameloDev_ComponenteNativoMbGestao implements Serializable {

	public ItfAcaoFormulario getComponenteNativoMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoProjetoCRCComponenteNativo.COMPONENTE_NATIVO_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getComponenteNativoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoProjetoCRCComponenteNativo.COMPONENTE_NATIVO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getComponenteNativoFrmVerComponente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoProjetoCRCComponenteNativo.COMPONENTE_NATIVO_FRM_VER_COMPONENTE");
	}
}