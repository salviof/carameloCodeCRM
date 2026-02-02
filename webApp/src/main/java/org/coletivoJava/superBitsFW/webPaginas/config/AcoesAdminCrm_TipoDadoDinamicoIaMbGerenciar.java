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
public class AcoesAdminCrm_TipoDadoDinamicoIaMbGerenciar
		implements
			Serializable {

	public ItfAcaoFormulario getTipoDadoDinamicoIaMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_IA_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getTipoDadoDinamicoIaFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_IA_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getTipoDadoDinamicoIaFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_IA_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getTipoDadoDinamicoIaFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_IA_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getTipoDadoDinamicoIaFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_IA_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getTipoDadoDinamicoIaCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_IA_CTR_SALVAR_MERGE");
	}
}