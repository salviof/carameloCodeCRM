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
public class AcoesAdminCrm_TipoDadoDinamicoLogicoMbGerencia
		implements
			Serializable {

	public ItfAcaoFormulario getTipoDadoDinamicoLogicoMbGerencia() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_LOGICO_MB_GERENCIA");
	}

	public ItfAcaoFormularioEntidade getTipoDadoDinamicoLogicoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_LOGICO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getTipoDadoDinamicoLogicoFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_LOGICO_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getTipoDadoDinamicoLogicoFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_LOGICO_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getTipoDadoDinamicoLogicoFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_LOGICO_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getTipoDadoDinamicoLogicoCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_LOGICO_CTR_SALVAR_MERGE");
	}
}