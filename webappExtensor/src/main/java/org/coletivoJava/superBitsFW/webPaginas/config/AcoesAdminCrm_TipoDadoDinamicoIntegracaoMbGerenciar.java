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
public class AcoesAdminCrm_TipoDadoDinamicoIntegracaoMbGerenciar
		implements
			Serializable {

	public ItfAcaoFormulario getTipoDadoDinamicoIntegracaoMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_INTEGRACAO_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getTipoDadoDinamicoIntegracaoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_INTEGRACAO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getTipoDadoDinamicoIntegracaoFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_INTEGRACAO_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getTipoDadoDinamicoIntegracaoFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_INTEGRACAO_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getTipoDadoDinamicoIntegracaoFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_INTEGRACAO_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getTipoDadoDinamicoIntegracaoCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_INTEGRACAO_CTR_SALVAR_MERGE");
	}
}