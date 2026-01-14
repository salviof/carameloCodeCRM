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
public class AcoesPlugin_OrcamentoAtvMbGestao implements Serializable {

	public ItfAcaoFormulario getOrcamentoAtvMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoOrcamento.ORCAMENTO_ATV_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getOrcamentoAtvFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoOrcamento.ORCAMENTO_ATV_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getOrcamentoAtvFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoOrcamento.ORCAMENTO_ATV_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getOrcamentoAtvFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoOrcamento.ORCAMENTO_ATV_FRM_VISUALIZAR");
	}

	public ItfAcaoFormularioEntidade getOrcamentoAtvFrmAprovarReprovar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoOrcamento.ORCAMENTO_ATV_FRM_APROVAR_REPROVAR");
	}

	public ComoAcaoControllerEntidade getOrcamentoAtvCtrAprovar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoOrcamento.ORCAMENTO_ATV_CTR_APROVAR");
	}

	public ComoAcaoControllerEntidade getOrcamentoAtvCtrReprovar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoOrcamento.ORCAMENTO_ATV_CTR_REPROVAR");
	}

	public ComoAcaoControllerEntidade getOrcamentoAtvCtrNovo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoOrcamento.ORCAMENTO_ATV_CTR_NOVO");
	}

	public ComoAcaoControllerEntidade getOrcamentoAtvCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoOrcamento.ORCAMENTO_ATV_CTR_SALVAR_MERGE");
	}
}