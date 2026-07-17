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

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovaNotificacaoCliente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVA_NOTIFICACAO_CLIENTE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovaNotificacaoEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVA_NOTIFICACAO_EQUIPE");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrSolicitarCriacaoOrcamento() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICITAR_CRIACAO_ORCAMENTO");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrSolicitarAberturaChamado() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_CTR_SOLICITAR_ABERTURA_CHAMADO");
	}

	public ComoAcaoControllerEntidade getSolicitacaoCtrAbrirFormularioResolucao() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmRevisarSolicitacao() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_REVISAR_SOLICITACAO");
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

	public ItfAcaoFormularioEntidade getSolicitacaoFrmListarMeusPedidosAbertos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmListarMeusPedidosAbertosCliente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_CLIENTE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmListarMeusPedidosAbertosEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE");
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

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovoConfirmacaoEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_CONFIRMACAO_EQUIPE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmEnviarConfirmacaoEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_CONFIRMACAO_EQUIPE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovoArquivoEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_ARQUIVO_EQUIPE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmEnviarArquivoEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_ARQUIVO_EQUIPE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovoArquivoCliente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_ARQUIVO_CLIENTE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovoConfirmacaoCliente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_CONFIRMACAO_CLIENTE");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovoChamado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_CHAMADO");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmEnviarChamado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_CHAMADO");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovoPedidoAtualizarArquivo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_PEDIDO_ATUALIZAR_ARQUIVO");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmAtualizarArquivo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_ATUALIZAR_ARQUIVO");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmRenegociaPrazo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_RENEGOCIA_PRAZO");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmNovoOrcamento() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_ORCAMENTO");
	}

	public ItfAcaoFormularioEntidade getSolicitacaoFrmEnviarOrcamento() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_ORCAMENTO");
	}
}