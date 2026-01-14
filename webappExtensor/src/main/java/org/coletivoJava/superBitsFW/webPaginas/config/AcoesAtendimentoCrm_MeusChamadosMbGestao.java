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
public class AcoesAtendimentoCrm_MeusChamadosMbGestao implements Serializable {

	public ItfAcaoFormulario getMeusChamadosMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getMeusChamadosFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_NOVO");
	}

	public ComoAcaoControllerEntidade getMeusChamadosCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_SALVAR_MERGE");
	}

	public ComoAcaoControllerEntidade getMeusChamadosCtrCriarChamado() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_CRIAR_CHAMADO");
	}

	public ComoAcaoControllerEntidade getMeusChamadosCtrFecharChamado() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_FECHAR_CHAMADO");
	}

	public ItfAcaoFormularioEntidade getMeusChamadosFrmTodosStatus() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_TODOS_STATUS");
	}

	public ItfAcaoFormularioEntidade getMeusChamadosFrmChamadosEmAtendimento() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_EM_ATENDIMENTO");
	}

	public ItfAcaoFormularioEntidade getMeusChamadosFrmChamadosAtender() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER");
	}

	public ItfAcaoFormularioEntidade getMeusChamadosFrmChamadosDefinirAtendimento() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_DEFINIR_ATENDIMENTO");
	}

	public ItfAcaoFormularioEntidade getMeusChamadosFrmChamadosAguardandoAtendimento() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_AGUARDANDO_ATENDIMENTO");
	}

	public ComoAcaoControllerEntidade getMeusChamadosCtrDefinirResponsavel() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_DEFINIR_RESPONSAVEL");
	}

	public ComoAcaoControllerEntidade getMeusChamadosCtrAssumirChamado() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_ASSUMIR_CHAMADO");
	}

	public ComoAcaoControllerEntidade getMeusChamadosCtrNotificarCliente() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_NOTIFICAR_CLIENTE");
	}

	public ComoAcaoControllerEntidade getMeusChamadosCtrGerarCodigoAcesso() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_GERAR_CODIGO_ACESSO");
	}
}