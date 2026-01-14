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
public class AcoesAtendimentoCrm_ProspectoMbGerenciar implements Serializable {

	public ItfAcaoFormulario getProspectoMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmEditarPessoaJuridica() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_PESSOA_JURIDICA");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmConverterPessoaFisicoJuridico() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_CONVERTER_PESSOA_FISICO_JURIDICO");
	}

	public ComoAcaoControllerEntidade getProspectoCtrConverterPessoaFisicoJuridico() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_CONVERTER_PESSOA_FISICO_JURIDICO");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmIntegracao() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_INTEGRACAO");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmEditarEnderecoPessoaJuridica() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_ENDERECO_PESSOA_JURIDICA");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmEditarPessoaFisica() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_PESSOA_FISICA");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmEditarEnderecoPessoaFisica() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_ENDERECO_PESSOA_FISICA");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmTags() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_TAGS");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmChatEquipe() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_CHAT_EQUIPE");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmChatCliente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_CHAT_CLIENTE");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmArquivos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_ARQUIVOS");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmNovoCadastroRapido() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_NOVO_CADASTRO_RAPIDO");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_VISUALIZAR");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmAlterarRelacionamento() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_ALTERAR_RELACIONAMENTO");
	}

	public ComoAcaoControllerEntidade getProspectoCtrAlterarRelacionamento() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_ALTERAR_RELACIONAMENTO");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmVerAtividades() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_VER_ATIVIDADES");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmExcluirEmpresa() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_EXCLUIR_EMPRESA");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmEditarDadosDinamicos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_DADOS_DINAMICOS");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmEditarDadosDinamicosPf() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_DADOS_DINAMICOS_PF");
	}

	public ComoAcaoControllerEntidade getProspectoCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_REMOVER");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmEncontrarLogomarca() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_ENCONTRAR_LOGOMARCA");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmOpcoesDoProspecto() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmAcessoPessoaNegado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_ACESSO_PESSOA_NEGADO");
	}

	public ComoAcaoControllerEntidade getProspectoCtrSolicitarAcesso() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_SOLICITAR_ACESSO");
	}

	public ComoAcaoControllerEntidade getProspectoCtrSalvarLogoMarcaEncontrada() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_LOGO_MARCA_ENCONTRADA");
	}

	public ComoAcaoControllerEntidade getProspectoCtrReduzirLogo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_REDUZIR_LOGO");
	}

	public ComoAcaoControllerEntidade getProspectoCtrSalvarNovosProspesctosNavegacao() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_NOVOS_PROSPESCTOS_NAVEGACAO");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmPreAnalise() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_PRE_ANALISE");
	}

	public ComoAcaoControllerEntidade getProspectoCtrNovaPreAnalise() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_NOVA_PRE_ANALISE");
	}

	public ComoAcaoControllerEntidade getProspectoCtrNovaPreAnaliseSeo() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_NOVA_PRE_ANALISE_SEO");
	}

	public ComoAcaoControllerEntidade getProspectoCtrPreAnaliseGetTipoAuditoria() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_PRE_ANALISE_GET_TIPO_AUDITORIA");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmServicos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_SERVICOS");
	}

	public ItfAcaoFormularioEntidade getProspectoFrmOrcamentos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_FRM_ORCAMENTOS");
	}

	public ComoAcaoControllerEntidade getProspectoCtrSalvarServicos() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_SERVICOS");
	}

	public ComoAcaoControllerEntidade getProspectoCtrSalvarArquivosExcluirArquivosOrfaos() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_ARQUIVOS_EXCLUIR_ARQUIVOS_ORFAOS");
	}

	public ComoAcaoControllerEntidade getProspectoCtrSalvarArquivos() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_ARQUIVOS");
	}

	public ComoAcaoControllerEntidade getProspectoCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_MERGE");
	}

	public ComoAcaoControllerEntidade getProspectoCtrSalvarMergePessoaFisica() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_MERGE_PESSOA_FISICA");
	}

	public ComoAcaoControllerEntidade getProspectoCtrCriarUsuariosAcessoAreaCliente() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_CRIAR_USUARIOS_ACESSO_AREA_CLIENTE");
	}

	public ComoAcaoControllerEntidade getProspectoCtrSalvarMergePessoaGenerico() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_MERGE_PESSOA_GENERICO");
	}

	public ComoAcaoControllerEntidade getProspectoCtrAlterarStatusPrivado() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_ALTERAR_STATUS_PRIVADO");
	}

	public ComoAcaoControllerEntidade getProspectoCtrAtualizarMautic() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_ATUALIZAR_MAUTIC");
	}

	public ComoAcaoControllerEntidade getProspectoCtrAbandonarProspecto() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_ABANDONAR_PROSPECTO");
	}

	public ComoAcaoControllerEntidade getProspectoCtrSalvarEExecutarAtividade() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_E_EXECUTAR_ATIVIDADE");
	}

	public ComoAcaoControllerEntidade getProspectoCtrSalvarECadastrarDetalhes() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_E_CADASTRAR_DETALHES");
	}

	public ComoAcaoControllerEntidade getProspectoCtrAtividadeSalvarDadosDinamicos() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_ATIVIDADE_SALVAR_DADOS_DINAMICOS");
	}

	public ComoAcaoControllerEntidade getProspectoCtrAtividadeRealizarPreAnalise() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_ATIVIDADE_REALIZAR_PRE_ANALISE");
	}

	public ComoAcaoControllerEntidade getProspectoCtrPassarABola() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.PROSPECTO_CTR_PASSAR_A_BOLA");
	}
}