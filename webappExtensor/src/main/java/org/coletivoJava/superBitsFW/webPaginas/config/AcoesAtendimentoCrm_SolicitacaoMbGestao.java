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
public class AcoesAtendimentoCrm_SolicitacaoMbGestao implements Serializable {

	public ItfAcaoFormulario getSolicitacaoMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmConcederAcesso() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_CONCEDER_ACESSO");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmListarSolicitacoesPessoa() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_SOLICITACOES_PESSOA");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmListarMinhasPendenciasAbertas() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MINHAS_PENDENCIAS_ABERTAS");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmListarMeusEdidosAbrtos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_EDIDOS_ABRTOS");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrSoliciatarAcessoPessoa() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_ACESSO_PESSOA");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrConcederAcesso() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_CTR_CONCEDER_ACESSO");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrNegarAcesso() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_CTR_NEGAR_ACESSO");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrSoliciatarArquivoEquipe() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_ARQUIVO_EQUIPE");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrEnviarArquivoEquipe() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_CTR_ENVIAR_ARQUIVO_EQUIPE");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrSoliciatarArquivoCliente() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_ARQUIVO_CLIENTE");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrEnviarArquivoCliente() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_CTR_ENVIAR_ARQUIVO_CLIENTE");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrSoliciatarConfirmacaoEquipe() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_CONFIRMACAO_EQUIPE");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrSoliciatarConfirmacaoCliente() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICIATAR_CONFIRMACAO_CLIENTE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovaSolicitacaoEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVA_SOLICITACAO_EQUIPE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovoConfirmacaoEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_CONFIRMACAO_EQUIPE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovoArquivoEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_ARQUIVO_EQUIPE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovoArquivoCliente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_ARQUIVO_CLIENTE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmEnviarArquivoEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_ARQUIVO_EQUIPE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovoConfirmacaoCliente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_CONFIRMACAO_CLIENTE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmAtualizarArquivo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_ATUALIZAR_ARQUIVO");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmRenegociaPrazo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_RENEGOCIA_PRAZO");
	}
}