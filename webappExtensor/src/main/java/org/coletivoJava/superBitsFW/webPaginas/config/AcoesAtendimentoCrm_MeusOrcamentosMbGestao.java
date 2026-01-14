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
public class AcoesAtendimentoCrm_MeusOrcamentosMbGestao implements Serializable {

	public ItfAcaoFormulario getMeusOrcamentosMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getMeusOrcamentosFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getMeusOrcamentosFrmListarOrcamentosDaPessoa() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_LISTAR_ORCAMENTOS_DA_PESSOA");
	}

	public ItfAcaoFormularioEntidade getMeusOrcamentosFrmListarTodos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_LISTAR_TODOS");
	}

	public ItfAcaoFormularioEntidade getMeusOrcamentosFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getMeusOrcamentosFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getMeusOrcamentosFrmAcessoNegado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_ACESSO_NEGADO");
	}

	public ComoAcaoControllerEntidade getMeusOrcamentosCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_CTR_SALVAR_MERGE");
	}

	public ComoAcaoControllerEntidade getMeusOrcamentosCtrFinalizarOrcamento() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_CTR_FINALIZAR_ORCAMENTO");
	}

	public ComoAcaoControllerEntidade getMeusOrcamentosCtrCancelarOrcamento() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_CTR_CANCELAR_ORCAMENTO");
	}

	public ComoAcaoControllerEntidade getMeusOrcamentosCtrEnviarParaFaturamento() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_CTR_ENVIAR_PARA_FATURAMENTO");
	}
}