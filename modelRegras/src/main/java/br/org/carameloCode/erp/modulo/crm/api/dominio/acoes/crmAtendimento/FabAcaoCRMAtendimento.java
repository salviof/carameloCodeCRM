/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento;

import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.subpasta.SubPastaEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.subpasta.SubPastaPrivada;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.subpasta.SubpastaCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.codigoAcesso.CodigoConvite;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.fluxoAtividade.FluxoDeAtividades;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.EmailCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaFisica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.transitorio.DadosPesquisaGooglePlace;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.estadoFormulario.FabEstadoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.icones.FabIconeFontAwesome;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.CPChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.CPPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.subpastaprivada.CPSubPastaPrivada;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.autorizacao.PedidoAcesso;

/**
 *
 *
 * @author sfurbino
 */
@InfoModulosCRM(modulo = FabModulosCRM.ATENDIMENTO_CRM)
public enum FabAcaoCRMAtendimento implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Execucao de Atividades",
            entidade = AtividadeCRM.class,
            icone = "fa fa-play",
            precisaPermissao = true
    )
    EXECUCAO_ATIVIDADE_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Nova Atividade", icone = "fa fa-search")
    EXECUCAO_ATIVIDADE_FRM_VER_ATIVIDADE_REALIZADA,
    @InfoTipoAcaoController(nomeAcao = "Nova Atividade", icone = "fa fa-play")
    EXECUCAO_ATIVIDADE_CTR_INICIAR_ATIVIDADE,
    @InfoTipoAcaoController(nomeAcao = "Concluir Atividade", icone = "fa fa-check")
    EXECUCAO_ATIVIDADE_CTR_CONCLUIR_ATIVIDADE,
    @InfoTipoAcaoController(nomeAcao = "Criar Chatbot", icone = "fa fa-check")
    EXECUCAO_ATIVIDADE_CTR_CRIAR_CHAT_BOT,
    @InfoTipoAcaoController(nomeAcao = "Enviar whatsapp", icone = "fa fa-check")
    EXECUCAO_ATIVIDADE_CTR_ENVIARWHATSAPP,
    @InfoTipoAcaoController(nomeAcao = "Gerar Documento da Atividade", icone = "fa fa-gears")
    EXECUCAO_ATIVIDADE_CTR_GERAR_DOCUMENTOS,
    @InfoTipoAcaoController(nomeAcao = "Aplicar modelo E-mail", precisaPermissao = false, icone = "fa fa-gears")
    EXECUCAO_ATIVIDADE_CTR_EMAIL_APLICAR_MODELO_EMAIL_DE_ATIVIDADE,
    @InfoTipoAcaoController(nomeAcao = "Salvar estado atual", iconeFonteAnsowame = FabIconeFontAwesome.REG_SALVAR)
    EXECUCAO_ATIVIDADE_CTR_EMAIL_SALVAR_ESTADO_ATUAL,
    @InfoTipoAcaoController(nomeAcao = "Salvar como rascunho", iconeFonteAnsowame = FabIconeFontAwesome.REG_SALVAR)
    EXECUCAO_ATIVIDADE_CTR_EMAIL_SALVAR_RASCUNHO,
    @InfoTipoAcaoController(nomeAcao = "Salvar versão Oficial", iconeFonteAnsowame = FabIconeFontAwesome.REG_SALVAR)
    EXECUCAO_ATIVIDADE_CTR_EMAIL_SALVAR_FORMATADO,
    @InfoTipoAcaoController(nomeAcao = "Enviar!", iconeFonteAnsowame = FabIconeFontAwesome.COMUNICACAO_AVIAO_DE_PAPEL)
    EXECUCAO_ATIVIDADE_CTR_EMAIL_ENVIAR,
    @InfoTipoAcaoController(nomeAcao = "Enviar e Concluir!", iconeFonteAnsowame = FabIconeFontAwesome.COMUNICACAO_AVIAO_DE_PAPEL)
    EXECUCAO_ATIVIDADE_CTR_EMAIL_ENVIAR_E_CONCLUIR,
    @InfoTipoAcaoController(nomeAcao = "Aplicar Modelo", iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ENGRENAGEM)
    EXECUCAO_ATIVIDADE_CTR_EMAIL_APLICAR_MODELO,
    @InfoTipoAcaoController(nomeAcao = "Concluir mais tarde", icone = "fa fa-save")
    EXECUCAO_ATIVIDADE_CTR_CONCLUIR_MAIS_TARDE,
    @InfoTipoAcaoController(nomeAcao = "Cancelar Atividade", icone = "fa fa-save")
    EXECUCAO_ATIVIDADE_CTR_CANCELAR,
    @InfoTipoAcaoController(nomeAcao = "Excluir atividade Anterior", icone = "fa fa-times")
    EXECUCAO_ATIVIDADE_CTR_CANCELAR_ATIVIDADE_ANTERIOR,
    @InfoTipoAcaoController(nomeAcao = "Continuar atividade Anterior", descricao = "Cancela a criação de uma anova atividade, e retoma a atividade anterior", iconeFonteAnsowame = FabIconeFontAwesome.REG_ANTERIOR)
    EXECUCAO_ATIVIDADE_CTR_CONTINUAR_ANTERIOR_EXCLUINDO_ESTA,
    @InfoTipoAcaoController(nomeAcao = "Salvar dados com relatório Final", icone = "fa fa-save")
    EXECUCAO_ATIVIDADE_CTR_SALVAR_DADOS_DINAMICOS,
    @InfoTipoAcaoController(nomeAcao = "Salvar prospecto com dados dinamicos", icone = "fa fa-save")
    EXECUCAO_ATIVIDADE_CTR_SALVAR_PROSPECTO_DADOS_DINAMICOS,
    @InfoTipoAcaoController(nomeAcao = "Salvar Dados e concluir Atividade", icone = "fa fa-save")
    EXECUCAO_ATIVIDADE_CTR_SALVAR_DADOS_DINAMICOS_CONCLUINDO_ATIVIDADE,
    @InfoTipoAcaoController(nomeAcao = "Agendar", icone = "fa fa-calendar")
    EXECUCAO_ATIVIDADE_CTR_AGENDAR,
    @InfoTipoAcaoController(nomeAcao = "EnviarConvite", iconeFonteAnsowame = FabIconeFontAwesome.COMUNICACAO_AVIAO_DE_PAPEL)
    EXECUCAO_ATIVIDADE_CTR_ENVIAR_CONVITE,
    @InfoTipoAcaoFormulario(nomeAcao = "Nova Atividade Simples", icone = "fa fa-play")
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_SIMPLES,
    @InfoTipoAcaoFormulario(nomeAcao = "Agendar", icone = "fa fa-calendar")
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_AGENDAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Atividade com E-mail", iconeFonteAnsowame = FabIconeFontAwesome.COMUNICACAO_AVIAO_DE_PAPEL)
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_COM_EMAIL,
    @InfoTipoAcaoFormulario(nomeAcao = "Cadastro de dados dinâmicos", iconeFonteAnsowame = FabIconeFontAwesome.REG_AGRUPAR_REGISTROS)
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_DADOS_OBRIGATORIOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Cadastro de Logo da empresa", icone = "fa fa-photo")
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ADICIONAR_LOGOMARCA,
    @InfoTipoAcaoFormulario(nomeAcao = "Análise Spped Inght", icone = "fa fa-line-chart")
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_PLUGIN_DE_ATIVIDADE,
    @InfoTipoAcaoFormulario(nomeAcao = "Adicionar Serviços", icone = "fa fa-money")
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ADICIONAR_SERVICOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Atividade com mudança de Relacionamento", icone = "fa fa-play")
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_GERADORA,
    @InfoTipoAcaoFormulario(nomeAcao = "Atividade com mudança de Relacionamento", icone = "fa fa-lock")
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ACESSO_NEGADO,
    @InfoTipoAcaoFormulario(nomeAcao = "Atividade convidado", icone = "fa fa-play")
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_POR_CONVIDADO,
    @InfoTipoAcaoFormulario(nomeAcao = "Convidar Colaborador", icone = "fa fa-ticket", entidade = CodigoConvite.class,
            campos = {"[separador: Quem será convidado para executar esta atividade?]", "contato", "assunto", "conteudo"})
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_CONVITE,
    @InfoTipoAcaoFormulario(nomeAcao = "Atividade Contatos", icone = "fa fa-play")
    EXECUCAO_ATIVIDADE_FRM_CONTATO,
    @InfoTipoAcaoFormulario(nomeAcao = "Sobrescrever Atividade", icone = "fa fa-play")
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_SOBRESCREVER_ATIVIDADE,
    @InfoTipoAcaoFormulario(nomeAcao = "Nova Atividade EnvioDocumento", icone = "fa fa-play")
    EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_RELATORIO,
    @InfoTipoAcaoFormulario(nomeAcao = "Gerar Documentos", icone = "fa fa-play")
    EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_GERAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Formatar documento", icone = "fa fa-play")
    EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_FORMATAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Enviar Email de Atividade", icone = "fa fa-play")
    EXECUCAO_ATIVIDADE_FRM_DOCUMENTO_ENVIAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Escolher Atividade", icone = "fa fa-map-signs")
    EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE,
    @InfoTipoAcaoController(nomeAcao = "Atendimento Telefonico", icone = "fa fa-phone")
    EXECUCAO_ATIVIDADE_CTR_ATIVIDADE_POR_TELEFONE,
    @InfoTipoAcaoController(nomeAcao = "Ligar Para alguem", icone = "fa fa-phone")
    EXECUCAO_ATIVIDADE_CTR_ATIVIDADE_POR_TELEFONE_ATIVO,
    @InfoTipoAcaoController(nomeAcao = "Atender um Telefone", icone = "fa fa-phone")
    EXECUCAO_ATIVIDADE_CTR_ATIVIDADE_POR_TELEFONE_RECEPTIVO,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Atividade colaborativa", entidade = CodigoConvite.class, precisaPermissao = false)
    FORMULARIO_CONVIDADO_MB_CONVITE,
    @InfoTipoAcaoFormulario(nomeAcao = "preencher formulário", icone = "fa fa-play")
    FORMULARIO_CONVIDADO_FRM_COLETAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Observação", icone = "fa fa-play")
    FORMULARIO_CONVIDADO_FRM_OBSERVACAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Obrigado", icone = "fa fa-play")
    FORMULARIO_CONVIDADO_FRM_OBRIGADO,
    @InfoTipoAcaoController(nomeAcao = "Salvar colaboração", icone = "fa fa-play")
    FORMULARIO_CONVIDADO_CTR_CONCLUIR,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Gerenciar Atividades", entidade = AtividadeCRM.class)
    ATIVIDADE_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Minhas Atividades", iconeFonteAnsowame = FabIconeFontAwesome.PESSOA_CORACAO)
    ATIVIDADE_FRM_MINHAS_ATIVIDADES,
    @InfoTipoAcaoController(nomeAcao = "Gerar dicumento de Atividade")
    ATIVIDADE_CTR_GERAR_DOCUMENTOS,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Descobertas", descricao = "Gestão de Prospectos", icone = "fa fa-globe", entidade = DadosPesquisaGooglePlace.class, precisaPermissao = true)
    DESCOBRIDOR_PROSPECTO_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(icone = "fa fa-address-book-o", nomeAcao = "Descobrir novos Leads")
    DESCOBRIDOR_PROSPECTO_FRM_LISTAR_DESCOBERTAS,
    @InfoTipoAcaoFormulario(icone = "fa fa-address-card-o")
    DESCOBRIDOR_PROSPECTO_FRM_VER_PROSPECTOS_DA_DESCOBERTA,
    @InfoTipoAcaoFormulario(icone = "fa fa-address-card-o", nomeAcao = "Descobrir novos Leads")
    DESCOBRIDOR_PROSPECTO_FRM_PESQUISA,
    @InfoTipoAcaoController(icone = "fa fa-search")
    DESCOBRIDOR_PROSPECTO_CTR_PESQUISAR,
    @InfoTipoAcaoController(iconeFonteAnsowame = FabIconeFontAwesome.REG_SALVAR)
    DESCOBRIDOR_PROSPECTO_CTR_SALVAR_RESULTADO,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Gerenciar de Prospecto", descricao = "Gestão de Prospectos", icone = "fa fa-address-book-o", entidade = Pessoa.class, precisaPermissao = true)
    PROSPECTO_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Editar Empresa",
            descricao = "Exibe formulario de edição de Prospecto",
            icone = "fa fa-edit",
            entidade = PessoaJuridica.class,
            campos = {
                "[separador:Dados Principais]", "origem", "nome", "cnpj", "tipoEmpresa", "porte",
                "umPerfilPrivado",
                "[separador: Contato]", "site", "contatoPrincipal",
                "endereco", "telefonePrincipal", "outrosTelefones", "contatoPrincipal",
                "[separador:    Observações]",
                "observacao"

            })
    PROSPECTO_FRM_EDITAR_PESSOA_JURIDICA,
    @InfoTipoAcaoFormulario(nomeAcao = "Converter Fisica ou Juridica", descricao = "Converte pessoa fisica em juridica, e juridica em física", icone = "fa fa-retweet")
    PROSPECTO_FRM_CONVERTER_PESSOA_FISICO_JURIDICO,
    @InfoTipoAcaoController(icone = "fa fa-retweet", nomeAcao = "Converter agora")
    PROSPECTO_CTR_CONVERTER_PESSOA_FISICO_JURIDICO,
    @InfoTipoAcaoFormulario(icone = "fa fa-link", nomeAcao = "Integrações")
    PROSPECTO_FRM_INTEGRACAO,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Editar endereço empresa",
            descricao = "Exibe formulario de edição de endereço da pessoa",
            icone = "fa fa-edit",
            entidade = PessoaJuridica.class,
            campos = {
                "[separador: Endereço]",
                "localizacao.cep", "localizacao.bairro.cidade.unidadeFederativa",
                "localizacao.bairro.cidade", "localizacao.bairro", "localizacao.logradouro", "localizacao.complemento"})
    PROSPECTO_FRM_EDITAR_ENDERECO_PESSOA_JURIDICA,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Editar Empresa",
            descricao = "Exibe formulario de edição de Prospecto",
            icone = "fa fa-edit",
            entidade = PessoaFisica.class,
            campos = {
                "[separador:Dados Principais]", "origem", "nome", CPPessoaFisica.cpf, "porte",
                "umPerfilPrivado",
                "[separador: Contato]", "site", "contatoPrincipal",
                "endereco", "telefonePrincipal", "outrosTelefones", "contatoPrincipal",
                "[separador:    Observações]",
                "observacao",
                "[separador: Notion]",
                "linkNotionIntranet"
            })
    PROSPECTO_FRM_EDITAR_PESSOA_FISICA,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Editar endereço empresa",
            descricao = "Exibe formulario de edição de endereço da pessoa",
            icone = "fa fa-edit",
            entidade = PessoaFisica.class,
            campos = {
                "[separador: Endereço]",
                "localizacao.cep", "localizacao.bairro.cidade.unidadeFederativa",
                "localizacao.bairro.cidade", "localizacao.bairro", "localizacao.logradouro", "localizacao.complemento"})
    PROSPECTO_FRM_EDITAR_ENDERECO_PESSOA_FISICA,
    @InfoTipoAcaoFormulario(nomeAcao = "Tags",
            icone = "fa fa-tags",
            campos = {"tagsAtendimento"},
            estadoFormulario = FabEstadoFormulario.EDITAR
    )
    PROSPECTO_FRM_TAGS,
    @InfoTipoAcaoFormulario(nomeAcao = "Chat equipe", icone = "fa fa-comments-o")
    PROSPECTO_FRM_CHAT_EQUIPE,
    @InfoTipoAcaoFormulario(nomeAcao = "Chat cliente", icone = "fa fa-comments-o")
    PROSPECTO_FRM_CHAT_CLIENTE,
    @InfoTipoAcaoFormulario(nomeAcao = "Arquivos", icone = "fa fa-files-o")
    PROSPECTO_FRM_ARQUIVOS,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Novo cadastro Completo",
            descricao = "Exibe formulario de edição da empresa",
            icone = "fa fa-plus-circle",
            entidade = PessoaJuridica.class,
            campos = {
                "[separador:Dados Principais]", "origem", "nome", "cnpj", "tipoEmpresa", "umPerfilPrivado", "observacao",
                "[separador: Contato]", "site", "responsavel", "email", "endereco", "telefonePrincipal", "outrosTelefones", "contatoPrincipal"})
    PROSPECTO_FRM_NOVO,
    @InfoTipoAcaoFormulario(nomeAcao = "Novo cadastro Rápido",
            descricao = "Cria um novo prospecto", icone = "fa fa-bolt",
            entidade = PessoaJuridica.class,
            campos = {
                "[separador: Cadastro básico]",
                "nome", "site", "umPerfilPrivado", "telefonePrincipal",
                "[separador: Dados  Básicos de Marketing]",
                "origem", "tipoEmpresa", "porte",
                "[separador: Contato]", "responsavel", "contatoPrincipal.email", "contatoPrincipal.celular",
                "[separador: Obeservações]", "observacao"
            },
            iconeFonteAnsowame = FabIconeFontAwesome.REG_NOVO,
            xhtmlDaAcao = "novoProspecto.xhtml")
    PROSPECTO_FRM_NOVO_CADASTRO_RAPIDO,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Listar Empresas",
            descricao = "Exibe as informações de um Prospecto",
            icone = "fa fa-address-book-o",
            entidade = PessoaJuridica.class,
            campos = {"id", "nome", "email", "telefonePrincipal", "origem", "relacionamento"})
    PROSPECTO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Visualizar Empresa",
            descricao = "Exibe as informações de um Prospecto", icone = "fa fa-address-book-o", entidade = PessoaJuridica.class,
            campos = {"[separador:Dados Principais]",
                "origem", "nome", "cnpj", "tipoEmpresa", "porte", "umPerfilPrivado", "observacao",
                "[separador: Contato]", "site", "responsavel", "email", "endereco", "telefonePrincipal", "outrosTelefones", "contatoPrincipal"})
    PROSPECTO_FRM_VISUALIZAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Alterar Relacionamento", icone = "fa fa-exchange", precisaPermissao = true, entidade = PessoaJuridica.class,
            campos = {"[separador: Alterar Relacionamento]", "relacionamento"}
    )
    PROSPECTO_FRM_ALTERAR_RELACIONAMENTO,
    @InfoTipoAcaoController(nomeAcao = "Forçar Alteração", icone = "fa fa-exchange", precisaPermissao = true, entidade = PessoaJuridica.class)
    PROSPECTO_CTR_ALTERAR_RELACIONAMENTO,
    @InfoTipoAcaoFormulario(nomeAcao = "Atividades", icone = "fa fa-history", precisaPermissao = true)
    PROSPECTO_FRM_VER_ATIVIDADES,
    @InfoTipoAcaoFormulario(nomeAcao = "Excluir Empresa", iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER, precisaPermissao = true)
    PROSPECTO_FRM_EXCLUIR_EMPRESA,
    @InfoTipoAcaoFormulario(nomeAcao = "Dados de Atividade", icone = "fa fa-th-large", entidade = PessoaJuridica.class)
    PROSPECTO_FRM_EDITAR_DADOS_DINAMICOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Dados de Atividade PF", icone = "fa fa-th-large", entidade = PessoaFisica.class)
    PROSPECTO_FRM_EDITAR_DADOS_DINAMICOS_PF,
    @InfoTipoAcaoController(nomeAcao = "Exluir", iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER, precisaPermissao = true, comunicacao = FabTipoComunicacao.PERGUNTAR_SIM_OU_NAO, fraseComunicação = "Você tem certeza? esta ação é definitiva.")
    PROSPECTO_CTR_REMOVER,
    @InfoTipoAcaoFormulario(nomeAcao = "Encontrar Logo da Empresa", icone = "fa fa-picture-o")
    PROSPECTO_FRM_ENCONTRAR_LOGOMARCA,
    @InfoTipoAcaoFormulario(nomeAcao = "Infográfico da pessoa", icone = "fa fa-plus-square-o")
    PROSPECTO_FRM_OPCOES_DO_PROSPECTO,
    @InfoTipoAcaoFormulario(nomeAcao = "Acesso Negado", icone = "fa fa-ban")
    PROSPECTO_FRM_ACESSO_PESSOA_NEGADO,
    @InfoTipoAcaoController(nomeAcao = "Solicitar acesso", icone = "fa fa-handshake-o")
    PROSPECTO_CTR_SOLICITAR_ACESSO,
    @InfoTipoAcaoController(nomeAcao = "Aplicar Logo", icone = "fa fa-check")
    PROSPECTO_CTR_SALVAR_LOGO_MARCA_ENCONTRADA,
    @InfoTipoAcaoController(nomeAcao = "Reduzir Logo", icone = "fa fa-retweet")
    PROSPECTO_CTR_REDUZIR_LOGO,
    @InfoTipoAcaoController(nomeAcao = "Salvar prospectos empresas encontradas", descricao = "Salvar prospectos encontrados", icone = "fa fa-save", entidade = PessoaJuridica.class)
    PROSPECTO_CTR_SALVAR_NOVOS_PROSPESCTOS_NAVEGACAO,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Coletar Google Site Insights",
            icone = "fa fa-search-plus", precisaPermissao = true)
    PROSPECTO_FRM_PRE_ANALISE,
    @InfoTipoAcaoController(nomeAcao = "Coletar pré análise", iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ENGRENAGEM)
    PROSPECTO_CTR_NOVA_PRE_ANALISE_SEO,
    @InfoTipoAcaoController(nomeAcao = "Gerar tipo Auditoria Google", iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ENGRENAGEM)
    PROSPECTO_CTR_PRE_ANALISE_GET_TIPO_AUDITORIA,
    @InfoTipoAcaoFormulario(nomeAcao = "UltimoOrçamento", icone = "fa fa-money", precisaPermissao = true)
    PROSPECTO_FRM_SERVICOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Orçamentos", icone = "fa fa-money", precisaPermissao = true)
    PROSPECTO_FRM_ORCAMENTOS,
    @InfoTipoAcaoController(nomeAcao = "Salvar Soluções", descricao = "Salva os arquivos do prospecto", icone = "fa fa-save", entidade = PessoaJuridica.class)
    PROSPECTO_CTR_SALVAR_SERVICOS,
    @InfoTipoAcaoController(nomeAcao = "Exluir Arquivos do Prospecto", descricao = "Salva os arquivos do prospecto", icone = "fa fa-save", entidade = PessoaJuridica.class)
    PROSPECTO_CTR_SALVAR_ARQUIVOS_EXCLUIR_ARQUIVOS_ORFAOS,
    @InfoTipoAcaoController(nomeAcao = "Salvar Arquivos do Prospecto", descricao = "Salva os arquivos do prospecto", icone = "fa fa-save", entidade = PessoaJuridica.class)
    PROSPECTO_CTR_SALVAR_ARQUIVOS,
    @InfoTipoAcaoController(nomeAcao = "Salvar Lead Pessoa Jurídica",
            descricao = "Salva o novo prospecto e Inicia um novo Cadastro", icone = "fa fa-save", entidade = PessoaJuridica.class)
    PROSPECTO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoController(nomeAcao = "Salvar Lead Pessoa Física",
            descricao = "Salva o novo prospecto e Inicia um novo Cadastro", icone = "fa fa-save", entidade = PessoaJuridica.class)
    PROSPECTO_CTR_SALVAR_MERGE_PESSOA_FISICA,
    @InfoTipoAcaoController(nomeAcao = "Criar area do cliente",
            descricao = "Salva o novo prospecto e Inicia um novo Cadastro", icone = "fa fa-save", entidade = PessoaJuridica.class)
    PROSPECTO_CTR_CRIAR_USUARIOS_ACESSO_AREA_CLIENTE,
    @InfoTipoAcaoController(nomeAcao = "Salvar Lead",
            descricao = "Salva o novo prospecto e Inicia um novo Cadastro", icone = "fa fa-save", entidade = PessoaJuridica.class)
    PROSPECTO_CTR_SALVAR_MERGE_PESSOA_GENERICO,
    @InfoTipoAcaoController(nomeAcao = "Alterar Status privado",
            descricao = "Alterar status privado", icone = "fa fa-save", entidade = PessoaJuridica.class)

    PROSPECTO_CTR_ALTERAR_STATUS_PRIVADO,
    @InfoTipoAcaoController(nomeAcao = "Atualizar Mautic",
            descricao = "Atualiza a Pessoa no Mautic", icone = "fa fa-save", entidade = PessoaJuridica.class)
    PROSPECTO_CTR_ATUALIZAR_MAUTIC,
    @InfoTipoAcaoController(nomeAcao = "Abandonar empresa", descricao = "Desvincula a empresa do usuário logado", icone = "fa fa-meh-o", entidade = PessoaJuridica.class)
    PROSPECTO_CTR_ABANDONAR_PROSPECTO,
    @InfoTipoAcaoController(nomeAcao = "Salvar e Cadastrar Atividade", descricao = "Registra os dados no sistema", icone = "fa fa-save", entidade = PessoaJuridica.class)
    PROSPECTO_CTR_SALVAR_E_EXECUTAR_ATIVIDADE,
    @InfoTipoAcaoController(nomeAcao = "Salvar e Cadastrar Detalhes", descricao = "Registra os dados no sistema", icone = "fa fa-save", entidade = PessoaJuridica.class)
    PROSPECTO_CTR_SALVAR_E_CADASTRAR_DETALHES,
    @InfoTipoAcaoController(nomeAcao = "Salvar dados dinâmicos", icone = "fa fa-floppy-o")
    PROSPECTO_CTR_ATIVIDADE_SALVAR_DADOS_DINAMICOS,
    @InfoTipoAcaoController(nomeAcao = "Reliazar pré análise", descricao = "", icone = "fa fa-gears")
    PROSPECTO_CTR_ATIVIDADE_REALIZAR_PRE_ANALISE,
    @InfoTipoAcaoController(nomeAcao = "Reliazar pré análise", descricao = "", icone = "fa fa-futbol-o")
    PROSPECTO_CTR_PASSAR_A_BOLA,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Meu Painel", icone = "fa fa-heartbeat", entidade = UsuarioCRM.class, precisaPermissao = true)
    MEU_DASHBOARD_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-line-chart", nomeAcao = "Visão geral do atendimento", precisaPermissao = true)
    MEU_DASHBOARD_FRM_TOTAIS,
    @InfoTipoAcaoFormulario(icone = "fa fa-line-chart", nomeAcao = "totais administrativo")
    MEU_DASHBOARD_FRM_TOTAIS_ADM,
    @InfoTipoAcaoFormulario(icone = "fa fa-globe", nomeAcao = "Cartões de orígens publica")
    MEU_DASHBOARD_FRM_ORIGENS_PUBLICA,
    @InfoTipoAcaoFormulario(icone = "fa fa-briefcase", nomeAcao = "Cartões de origem privada")
    MEU_DASHBOARD_FRM_ORIGENS_PRIVADAS,
    @InfoTipoAcaoFormulario(icone = "fa fa-handshake-o")
    MEU_DASHBOARD_FRM_ORIGENS_COMPARTILHADAS,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Meus Cartões",
            precisaPermissao = true, entidade = PessoaJuridica.class, iconeFonteAnsowame = FabIconeFontAwesome.PESSOA_CORACAO)
    MEUS_PROSPECTOS_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Meus cartões ativos",
            descricao = "Lista todos prospectos seu que é Cliente", entidade = PessoaJuridica.class)
    MEUS_PROSPECTOS_FRM_LISTAR_ATIVOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar meus cartões",
            descricao = "Lista todos prospectos exceto os que são Clientes",
            entidade = PessoaJuridica.class)
    MEUS_PROSPECTOS_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar cartões urgentes", icone = "fa fa-exclamation",
            descricao = "Lista todos os Leads com demand urgente",
            entidade = PessoaJuridica.class)
    MEUS_PROSPECTOS_FRM_LISTAR_URGENTES,
    @InfoTipoAcaoFormulario(nomeAcao = "Minhas Aividades Pendentes", descricao = "Lista todas as atividades não finalizadas", entidade = PessoaJuridica.class,
            xhtmlDaAcao = "meus_prospectos_atividades_pendentes.xhtml"
    )
    MEUS_PROSPECTOS_FRM_LISTAR_ATIVIDADES_PENDENTES,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Fluxo do Funíl de Vendas", entidade = FluxoDeAtividades.class)
    ORGANOGRAMA_FLUXO_MB,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Envio de documentos", entidade = EnvioEmail.class)
    ENVIO_DOCUMENTO_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Gerar Documento", iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ENGRENAGEM)
    ENVIO_DOCUMENTO_FRM_ETAPA_GERAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Formatar Documento", iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ENGRENAGEM)
    ENVIO_DOCUMENTO_FRM_ETAPA_FORMATAR_DOCUMENTO,
    @InfoTipoAcaoFormulario(nomeAcao = "Atividade não encontrada", iconeFonteAnsowame = FabIconeFontAwesome.REG_EDITAR)
    ENVIO_DOCUMENTO_FRM_ETAPA_ENVIAR_DOCUMENTO,
    @InfoTipoAcaoController(iconeFonteAnsowame = FabIconeFontAwesome.COMUNICACAO_AVIAO_DE_PAPEL, nomeAcao = "Enviar")
    ENVIO_DOCUMENTO_CTR_ENVIAR_EMAIL,
    @InfoTipoAcaoFormulario(nomeAcao = "Atividade não encontrada", xhtmlDaAcao = "acessoNegadoAoProspecto.xhtml", iconeFonteAnsowame = FabIconeFontAwesome.REG_VALIDAR)
    ENVIO_DOCUMENTO_FRM_PROSPECTO_SEM_PERMISSAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Atividade não encontrada", xhtmlDaAcao = "prospectoNaoEncontrado.xhtml", iconeFonteAnsowame = FabIconeFontAwesome.REG_VALIDAR)
    ENVIO_DOCUMENTO_FRM_PROSPECTO_NAO_ENCONTRADO,
    @InfoTipoAcaoFormulario(nomeAcao = "Atividade não encontrada", xhtmlDaAcao = "tipoAtividadeNaoEncontrada.xhtml", iconeFonteAnsowame = FabIconeFontAwesome.REG_VALIDAR)
    ENVIO_DOCUMENTO_FRM_SELECIONAR_TIPO_ATIVIDADE,
    @InfoTipoAcaoFormulario(nomeAcao = "Atividade finalizada", xhtmlDaAcao = "atividadeFoiFinalizada.xhtml", iconeFonteAnsowame = FabIconeFontAwesome.REG_VALIDAR)
    ENVIO_DOCUMENTO_FRM_ATIVIDADE_FOI_FINALIZADA,
    @InfoTipoAcaoFormulario(nomeAcao = "Enviar Documento", xhtmlDaAcao = "enviarDocumento.xhtml",
            iconeFonteAnsowame = FabIconeFontAwesome.COMUNICACAO_AVIAO_DE_PAPEL)
    ENVIO_DOCUMENTO_FRM_ENVIAR_PADRAO,
    @InfoTipoAcaoGestaoEntidade(entidade = EmailCrm.class)
    MODAL_EMAIL_MB_GESTAO,
    @InfoTipoAcaoFormulario(entidade = ContatoProspecto.class)
    MODAL_EMAIL_FRM_NOVO_CONTATO,
    @InfoTipoAcaoFormulario(entidade = ArquivoAnexado.class)
    MODAL_EMAIL_FRM_NOVO_ANEXO,
    @InfoTipoAcaoFormulario(entidade = EmailCrm.class)
    MODAL_EMAIL_FRM_VISUALIZAR,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Acesso Rápido Prospecto", xhtmlDaAcao = "acesso_rapido_prospecto.xhtml",
            entidade = PessoaJuridica.class)
    MODAL_CRM_MB_PROSPECTOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Edição Rápida",
            iconeFonteAnsowame = FabIconeFontAwesome.REG_EDITAR,
            xhtmlDaAcao = "modalEdicao_rapida.xhtml",
            entidade = PessoaJuridica.class
    )
    MODAL_CRM_FRM_EDICAO_RAPIDA,
    @InfoTipoAcaoFormulario(nomeAcao = "Informações Pendentes", descricao = "Permite cadastrar informações pendentes para evolução deste nível de relacionamento", icone = "fa fa-database", xhtmlDaAcao = "modalDadosDinamicos.xhtml",
            entidade = DadoCRM.class
    )
    MODAL_CRM_FRM_DADOS_DINAMICOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Onde estou?", icone = "fa fa-sitemap", xhtmlDaAcao = "modalFluxoAtualProspecto.xhtml",
            entidade = FluxoDeAtividades.class
    )
    MODAL_CRM_FRM_FLUXO,
    @InfoTipoAcaoFormulario(nomeAcao = "Compartilhar", descricao = "Compartilha um prospecto com colaborador (o colaborador será notificado a cada alteração)", icone = "fa fa-share-alt", xhtmlDaAcao = "modalCompartilhar.xhtml",
            entidade = PessoaJuridica.class
    )
    MODAL_CRM_FRM_COMPARTILHAR_PROSPECTO,
    @InfoTipoAcaoFormulario(nomeAcao = "Histórico", icone = "fa fa-history", xhtmlDaAcao = "modalAtividadesHistorico.xhtml",
            entidade = PessoaJuridica.class
    )
    MODAL_CRM_FRM_EXIBIR_ATIVIDADES,
    @InfoTipoAcaoFormulario(nomeAcao = "Contatos", icone = "fa fa-address-book", xhtmlDaAcao = "modalContatos.xhtml",
            campos = {"[separador:Contatos]", "contatosProspecto[].nome", "contatosProspecto[].email", "contatosProspecto[].celular"},
            entidade = PessoaJuridica.class
    )
    MODAL_CRM_FRM_CONTATOS,
    @InfoTipoAcaoFormulario(nomeAcao = "NovoContatos", icone = "fa fa-address-book", xhtmlDaAcao = "novoContato.xhtml",
            entidade = ContatoProspecto.class
    )
    MODAL_CRM_FRM_CONTATO_NOVO,
    @InfoTipoAcaoFormulario(nomeAcao = "Email Rápido", icone = "fa fa-envelope-o", xhtmlDaAcao = "modalEmailRapido.xhtml",
            entidade = PessoaJuridica.class
    )
    MODAL_CRM_FRM_EMAIL_RAPIDO,
    @InfoTipoAcaoFormulario(nomeAcao = "Anexos", icone = "fa fa-paperclip", xhtmlDaAcao = "modalAnexos.xhtml",
            entidade = PessoaJuridica.class
    )
    MODAL_CRM_FRM_ANEXOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Mais Opções", icone = "fa fa-map-signs", xhtmlDaAcao = "opcoesDeProspecto.xhtml",
            entidade = PessoaJuridica.class
    )
    MODAL_CRM_FRM_OPCOES_AVANCADO,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Meu Perfil", icone = "fa fa-address-card", entidade = UsuarioCRM.class, precisaPermissao = true, utilizarMesmoFormEdicao = false)
    MEU_PERFIL_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Minhas #Hashtags", icone = "fa fa-hashtag", entidade = UsuarioCRM.class,
            campos = {"[separador:Assinatura de corrreio eletrônico]", "tagsMonitoradas"})
    MEU_PERFIL_FRM_EDITAR_TAGS,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar meu perfil", icone = "fa fa-address-card", entidade = UsuarioCRM.class,
            campos = {"[separador:Informações básicas]", "nome", "email", "apelido"})
    MEU_PERFIL_FRM_EDITAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Alterar senha", icone = "fa fa-address-card", entidade = UsuarioCRM.class,
            campos = {"[separador:Informações básicas]", "nome", "email", "apelido"})
    MEU_PERFIL_FRM_ALTERAR_SENHA,
    @InfoTipoAcaoFormulario(nomeAcao = "Alterar senha Chat", icone = "fa fa-address-card", entidade = UsuarioCRM.class)
    MEU_PERFIL_FRM_ALTERAR_SENHA_MATRIX,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar o avatar", icone = "fa fa-address-card", entidade = UsuarioCRM.class)
    MEU_PERFIL_FRM_ALTERAR_AVATAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar Assinatura", icone = "fa fa-address-card", entidade = UsuarioCRM.class,
            campos = {"[separador:Assinatura de corrreio eletrônico]", "assinaturaEmail",})
    MEU_PERFIL_FRM_EDITAR_ASSINATURA_EMAIL,
    @InfoTipoAcaoFormulario(nomeAcao = "Servidor email Alternativo", icone = "fa fa-address-card", entidade = UsuarioCRM.class,
            campos = {"[separador:Email]", "caixaPostalPrincipal.enderecoServidor", "caixaPostalPrincipal.senhaSMTP",
                "caixaPostalPrincipal.portaSMTP", "caixaPostalPrincipal.usuarioSMTP", "caixaPostalPrincipal.usarSSLSMTP",
                "caixaPostalPrincipal.usarTSLSMTP"})
    MEU_PERFIL_FRM_EDITAR_SERVIDOR_ENVIO_PERSONALIZADO,
    @InfoTipoAcaoController(nomeAcao = "Salvar meu Perfil")
    MEU_PERFIL_CTR_SALVAR_MERGE,
    @InfoTipoAcaoController(nomeAcao = "Lembrar mais tarde", icone = "fa fa-clock")
    MEU_PERFIL_CTR_LEMBRAR_MAIS_TARDE_ATIVIDADES_INACABADAS,
    @InfoTipoAcaoController(nomeAcao = "Testar Envio E-mail", iconeFonteAnsowame = FabIconeFontAwesome.COMUNICACAO_AVIAO_DE_PAPEL)
    MEU_PERFIL_CTR_TESTAR,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Correio Eletronico", icone = "fa fa-envelope-o", entidade = EmailCrm.class)
    EMAILS_MB_GESTAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Correio eletronico do Prospecto", icone = "fa fa-envelope-o")
    EMAILS_FRM_EMAILS_DO_PROSPECTO,
    @InfoTipoAcaoFormulario(nomeAcao = "Ultimos mails", icone = "fa fa-envelope-o")
    EMAILS_FRM_LISTAR_ULTMOS_EMAILS,
    @InfoTipoAcaoFormulario(nomeAcao = "Enviados", icone = "fa fa-envelope-o", entidade = EnvioEmail.class, xhtmlDaAcao = "emails_listarEnviados.xhtml")
    EMAILS_FRM_LISTAR_ENVIADOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Recebidos ", icone = "fa fa-envelope-o", entidade = EmailRecebido.class, xhtmlDaAcao = "emails_listarRecebidos.xhtml")
    EMAILS_FRM_LISTAR_RECEBIDO,
    @InfoTipoAcaoFormulario(nomeAcao = "Rascunhos", icone = "fa fa-envelope-o", entidade = EnvioEmail.class, xhtmlDaAcao = "emails_listarRascunho.xhtml")
    EMAILS_FRM_LISTAR_RASCUNHOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Ver email Recebido", icone = "fa fa-envelope-o")
    EMAILS_FRM_VISUALIZAR_EMAIL,
    @InfoTipoAcaoFormulario(nomeAcao = "Novo email", icone = "fa fa-envelope-o", xhtmlDaAcao = "emailNovo.xhtml", entidade = EnvioEmail.class, estadoFormulario = FabEstadoFormulario.NOVO)
    EMAILS_FRM_NOVO,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar rascunho", icone = "fa fa-envelope-o", xhtmlDaAcao = "emailNovo.xhtml", entidade = EnvioEmail.class, estadoFormulario = FabEstadoFormulario.NOVO)
    EMAILS_FRM_EDITAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Responder", icone = "fa fa-reply", estadoFormulario = FabEstadoFormulario.NOVO, entidade = EnvioEmail.class)
    EMAILS_FRM_RESPONDER,
    @InfoTipoAcaoFormulario(nomeAcao = "Responder com modelo", icone = "fa fa-reply", estadoFormulario = FabEstadoFormulario.NOVO, entidade = EnvioEmail.class)
    EMAILS_FRM_RESPONDER_ENVANDO_MODELO,
    @InfoTipoAcaoFormulario(nomeAcao = "Responder com modelo", icone = "fa fa-file-text-o", estadoFormulario = FabEstadoFormulario.NOVO, entidade = EnvioEmail.class)
    EMAILS_FRM_RESPONDER_EDITANDO_MODELO,
    @InfoTipoAcaoFormulario(icone = "fa fa-clock-o", nomeAcao = "Agendar Disparo", entidade = EnvioEmail.class)
    EMAILS_FRM_AGENDAR_ENVIO,
    @InfoTipoAcaoController(icone = "fa fa-hand-paper-o", nomeAcao = "Solicitar Revisão", entidade = EnvioEmail.class)
    EMAILS_CTR_SOLICITAR_REVISAO,
    @InfoTipoAcaoController(icone = "fa fa-check", nomeAcao = "Concluir Revisão", entidade = EnvioEmail.class)
    EMAILS_CTR_CONCLUIR_REVISAO,
    @InfoTipoAcaoController(icone = "fa fa-clock-o", nomeAcao = "Agendar Disparo", entidade = EnvioEmail.class)
    EMAILS_CTR_AGENDAR_ENVIO,
    @InfoTipoAcaoController(icone = "fa fa-hand-paper-o")
    EMAILS_CTR_BLOQUEAR_REMETENTE,
    @InfoTipoAcaoController(icone = "fa fa-hand-paper-o")
    EMAILS_CTR_RELATAR_LEITURA,
    @InfoTipoAcaoController(icone = "fa fa-bug")
    EMAILS_CTR_RELATAR_SPAN,
    @InfoTipoAcaoController(icone = "fa fa-link", nomeAcao = "Atribuir Pessoa Responsável")
    EMAILS_CTR_ATRIBUIR_PESSSOA,
    @InfoTipoAcaoController(icone = "fa fa-smile-o")
    EMAILS_CTR_ATRIBUIR_PRIVADO,
    @InfoTipoAcaoController(icone = "fa fa-paper-plane-o", nomeAcao = "Enviar Agora", descricao = "Salva o rascunho, e envia o e-mail", entidade = EnvioEmail.class)
    EMAILS_CTR_ENVIAR_SALVANDO_RASCUNHO,
    @InfoTipoAcaoController(icone = "fa fa-paper-plane-o", nomeAcao = "Enviar agora")
    EMAILS_CTR_ENVIAR_AGORA,
    @InfoTipoAcaoController(icone = "fa fa-save", nomeAcao = "Salvar rascunho, finalizar depois")
    EMAILS_CTR_SALVAR_RASCUNHO,
    @InfoTipoAcaoController(icone = "fa fa-remove", nomeAcao = "Remover")
    EMAILS_CTR_REMOVER_EMAIL,
    @InfoTipoAcaoController(icone = "fa fa-remove", nomeAcao = "Remover", entidade = EnvioEmail.class)
    EMAILS_CTR_REMOVER_EMAIL_ENVIADO,
    @InfoTipoAcaoController(icone = "fa fa-remove", nomeAcao = "Remover", entidade = EnvioEmailAtividade.class)
    EMAILS_CTR_REMOVER_EMAIL_DE_ATIVIDADE,
    @InfoTipoAcaoController(icone = "fa fa-remove", nomeAcao = "Remover", entidade = EmailRecebido.class)
    EMAILS_CTR_REMOVER_EMAIL_RECEBIDO,
    @InfoTipoAcaoFormulario(nomeAcao = "Complemento de resposta", icone = "fa fa-envelope-o")
    EMAILS_FRM_COMPLEMENTAR_RESPOSTA,
    @InfoTipoAcaoFormulario(nomeAcao = "Encaminhar", icone = "fa fa-share")
    EMAILS_FRM_ENCAMINHAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Configurações do eMail", icone = "fa fa-envelope-o")
    EMAILS_FRM_CONFIGURACOES,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Caderno de endereços", entidade = ContatoProspecto.class)
    CONTATO_MB_GESTAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar contatos")
    CONTATO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar contatos")
    CONTATO_FRM_EDITAR,
    @InfoTipoAcaoController(nomeAcao = "Salvar contato")
    CONTATO_CTR_SALVAR,
    @InfoTipoAcaoController(nomeAcao = "Remover contato")
    CONTATO_CTR_REMOVER,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Orçamentos", entidade = Orcamento.class)
    MEUS_ORCAMENTOS_MB_GESTAO,
    @InfoTipoAcaoFormulario(entidade = Orcamento.class, nomeAcao = "Listar Meus Orçamentos",
            campos = {"id", "pessoa", "dataHoraCriacao"})
    MEUS_ORCAMENTOS_FRM_LISTAR,
    @InfoTipoAcaoFormulario(entidade = Orcamento.class, nomeAcao = "Orçamentos da pessoa",
            campos = {"id", "pessoa", "dataHoraCriacao"}, icone = "fa fa-money")
    MEUS_ORCAMENTOS_FRM_LISTAR_ORCAMENTOS_DA_PESSOA,
    @InfoTipoAcaoFormulario(nomeAcao = "Todos Orcamentos",
            campos = {"id", "pessoa", "dataHoraCriacao", "usuariocriou"})
    MEUS_ORCAMENTOS_FRM_LISTAR_TODOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Novo Orçamento", campos = {"[separador: Dados do Orçamento]",
        "pessoa", "descricao", "[separador: Itens do orçamento]",
        "servicoOferecido[].nome",
        "servicoOferecido[].tipoServico",
        "servicoOferecido[].valorSetup",
        "servicoOferecido[].valorMensal"})
    MEUS_ORCAMENTOS_FRM_NOVO,
    @InfoTipoAcaoFormulario(nomeAcao = "editar orçamento",
            campos = {"[separador: Dados do Orçamento]",
                "id", "pessoa", "descricao", "[separador: Itens do orçamento]",
                "servicoOferecido[].nome",
                "servicoOferecido[].tipoServico",
                "servicoOferecido[].valorSetup",
                "servicoOferecido[].valorMensal"},
            icone = "fa fa-money"
    )
    MEUS_ORCAMENTOS_FRM_EDITAR,
    @InfoTipoAcaoFormulario(icone = "fa fa-ban")
    MEUS_ORCAMENTOS_FRM_ACESSO_NEGADO,
    @InfoTipoAcaoController(nomeAcao = "Salvar")
    MEUS_ORCAMENTOS_CTR_SALVAR_MERGE,
    @InfoTipoAcaoController(nomeAcao = "Finalizar Orçamento", icone = "fa fa-flag-checkered")
    MEUS_ORCAMENTOS_CTR_FINALIZAR_ORCAMENTO,
    @InfoTipoAcaoController(nomeAcao = "Cancelar orçamento", icone = "fa fa-ban")
    MEUS_ORCAMENTOS_CTR_CANCELAR_ORCAMENTO,
    @InfoTipoAcaoController(nomeAcao = "Enviar para Faturamento", icone = "fa fa-money")
    MEUS_ORCAMENTOS_CTR_ENVIAR_PARA_FATURAMENTO,
    @InfoTipoAcaoGestaoEntidade(entidade = PedidoAcesso.class)
    MEUS_PEDIDOS_AUTORIZACAO_MB_GESTAO,
    @InfoTipoAcaoFormulario()
    MEUS_PEDIDOS_AUTORIZACAO_FRM_LISTAR,
    @InfoTipoAcaoController(icone = "fa fa-thumbs-o-up")
    MEUS_PEDIDOS_AUTORIZACAO_CTR_APROVAR,
    @InfoTipoAcaoController(icone = "fa fa-thumbs-o-down")
    MEUS_PEDIDOS_AUTORIZACAO_CTR_NEGAR,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Minhas Origens", icone = "fa fa-users", entidade = OrigemProspectoPrivado.class, precisaPermissao = true)
    MINHAS_ORIGENS_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-dot-circle-o", nomeAcao = "Listar minhas Origens")
    MINHAS_ORIGENS_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Detalhes origem", campos = {"nome", "usuariosComAcesso", "quantidadeLeads", "qtdLeadsAtivos", "qtdLeadsInativos"})
    MINHAS_ORIGENS_FRM_VISUALIZAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar Origem", campos = {"nome", "usuariosComAcesso"})
    MINHAS_ORIGENS_FRM_EDITAR,
    @InfoTipoAcaoFormulario(icone = "fa fa-users", campos = {"nome", "descricao", "usuariosComAcesso"})
    MINHAS_ORIGENS_FRM_NOVO,
    @InfoTipoAcaoController(nomeAcao = "Alterar privacidade do Lead")
    MINHAS_ORIGENS_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(entidade = PesquisaLead.class)
    PESQUISA_MB_GESTAO,
    @InfoTipoAcaoController()
    PESQUISA_CTR_SALVAR_COMO_NOVA_PESQUISA,
    @InfoTipoAcaoController()
    PESQUISA_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Minha agenda", icone = "fa fa-calendar", entidade = AtividadeCRM.class)
    MEUS_COMPROMISSOS_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Compromissos Hojes", icone = "fa fa-calendar-check-o")
    MEUS_COMPROMISSOS_FRM_HOJE,
    @InfoTipoAcaoFormulario(nomeAcao = "Compromisso 60 dias", icone = "fa fa-calendar")
    MEUS_COMPROMISSOS_FRM_VER_60_DIAS,
    @InfoTipoAcaoFormulario(nomeAcao = "Agenda completa", icone = "fa fa-calendar")
    MEUS_COMPROMISSOS_FRM_AGENDA_COMPLETA,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Meus chamados", icone = "fa fa-life-ring", entidade = ChamadoCliente.class, precisaPermissao = true)
    MEUS_CHAMADOS_MB_GESTAO,
    @InfoTipoAcaoFormulario(campos = {CPChamadoCliente.pessoa, CPChamadoCliente.resumodescricao, CPChamadoCliente.usuariocliente},
            nomeAcao = "Novo Chamado", estadoFormulario = FabEstadoFormulario.NOVO)
    MEUS_CHAMADOS_FRM_NOVO,
    @InfoTipoAcaoController()
    MEUS_CHAMADOS_CTR_SALVAR_MERGE,
    @InfoTipoAcaoController(icone = "fa fa-play", nomeAcao = "Criar Chamado")
    MEUS_CHAMADOS_CTR_CRIAR_CHAMADO,
    @InfoTipoAcaoController(icone = "fa fa-stop")
    MEUS_CHAMADOS_CTR_FECHAR_CHAMADO,
    @InfoTipoAcaoFormulario(nomeAcao = "Todos", icone = "fa fa-life-ring")
    MEUS_CHAMADOS_FRM_TODOS_STATUS,
    @InfoTipoAcaoFormulario(nomeAcao = "Meus chamados em atendimento", icone = "fa fa-life-ring")
    MEUS_CHAMADOS_FRM_CHAMADOS_EM_ATENDIMENTO,
    @InfoTipoAcaoFormulario(nomeAcao = "Atender", icone = "fa fa-handshake-o")
    MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER,
    @InfoTipoAcaoFormulario(nomeAcao = "Definir atendimento", icone = "fa fa-handshake-o")
    MEUS_CHAMADOS_FRM_CHAMADOS_DEFINIR_ATENDIMENTO,
    @InfoTipoAcaoFormulario(nomeAcao = "Meus chamados aguardando atendimento", icone = "fa fa-life-ring", campos = {"id", "tipoChamado", "titulo", "status", "pessoa"})
    MEUS_CHAMADOS_FRM_CHAMADOS_AGUARDANDO_ATENDIMENTO,
    @InfoTipoAcaoController(nomeAcao = "Definir Responsável", icone = "fa fa-share")
    MEUS_CHAMADOS_CTR_DEFINIR_RESPONSAVEL,
    @InfoTipoAcaoController(nomeAcao = "Assumir chamado", icone = "fa fa-hand-rock-o")
    MEUS_CHAMADOS_CTR_ASSUMIR_CHAMADO,
    @InfoTipoAcaoController(nomeAcao = "Notificação 1 click", icone = "fa fa-bell-o")
    MEUS_CHAMADOS_CTR_NOTIFICAR_CLIENTE,
    @InfoTipoAcaoController(nomeAcao = "Gerar código de acesso", icone = "fa fa-key")
    MEUS_CHAMADOS_CTR_GERAR_CODIGO_ACESSO,
    @InfoTipoAcaoGestaoEntidade(entidade = SistemaERPConfiavel.class, icone = "fa fa-sign-in")
    LOGIN_ERP_RESTFUL_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-sign-in", nomeAcao = "logar sistema ERP")
    LOGIN_ERP_RESTFUL_FRM_LOGAR,
    @InfoTipoAcaoFormulario(icone = "fa fa-sign-in", nomeAcao = "logar sistem faturamento")
    LOGIN_ERP_RESTFUL_FRM_LOGAR_FATURA,
    @InfoTipoAcaoController(icone = "fa fa-sign-in")
    LOGIN_ERP_RESTFUL_CTR_LOGAR,
    @InfoTipoAcaoGestaoEntidade(entidade = DadoCRM.class)
    DADO_CRM_MB_GESTAO,
    @InfoTipoAcaoFormulario()
    DADO_CRM_FRM_LISTAR,
    @InfoTipoAcaoController()
    DADO_CRM_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(entidade = Solicitacao.class, precisaPermissao = true)
    SOLICITACAO_MB_GESTAO,
    @InfoTipoAcaoFormulario(campos = "id", icone = "fa fa-gavel")
    SOLICITACAO_FRM_CONCEDER_ACESSO,
    @InfoTipoAcaoFormulario(campos = "id", icone = "fa fa-hand-paper-o", xhtmlDaAcao = "solicitacoes_pessoa.xhtml")
    SOLICITACAO_FRM_LISTAR_SOLICITACOES_PESSOA,
    @InfoTipoAcaoFormulario(campos = "id", icone = "fa fa-hand-paper-o")
    SOLICITACAO_FRM_LISTAR_MINHAS_PENDENCIAS_ABERTAS,
    @InfoTipoAcaoFormulario(icone = "fa fa-hand-paper-o")
    SOLICITACAO_FRM_LISTAR_MEUS_EDIDOS_ABRTOS,
    @InfoTipoAcaoController(entidade = SolicitacaoAcessoCard.class, icone = "fa fa-thumbs-up", nomeAcao = "Solicitar acesso ao Cartão")
    SOLICITACAO_CTR_SOLICIATAR_ACESSO_PESSOA,
    @InfoTipoAcaoController(entidade = SolicitacaoAcessoCard.class, icone = "fa fa-thumbs-up", nomeAcao = "Conceder")
    SOLICITACAO_CTR_CONCEDER_ACESSO,
    @InfoTipoAcaoController(entidade = SolicitacaoAcessoCard.class, icone = "fa fa-thumbs-down", nomeAcao = "Negar")
    SOLICITACAO_CTR_NEGAR_ACESSO,
    @InfoTipoAcaoController(entidade = SolicitacaoArquivoEquipe.class, icone = "fa fa-hand-paper-o", nomeAcao = "Enviar solicitação arquivo")
    SOLICITACAO_CTR_SOLICIATAR_ARQUIVO_EQUIPE,
    @InfoTipoAcaoController(entidade = SolicitacaoArquivoEquipe.class, icone = "fa fa-hand-paper-o", nomeAcao = "Enviar aquivo")
    SOLICITACAO_CTR_ENVIAR_ARQUIVO_EQUIPE,
    @InfoTipoAcaoController(entidade = SolicitacaoArquivoCliente.class, icone = "fa fa-hand-paper-o", nomeAcao = "Enviar solicitação para cliente")
    SOLICITACAO_CTR_SOLICIATAR_ARQUIVO_CLIENTE,
    @InfoTipoAcaoController(entidade = SolicitacaoArquivoCliente.class, icone = "fa fa-upload", nomeAcao = "Enviar")
    SOLICITACAO_CTR_ENVIAR_ARQUIVO_CLIENTE,
    @InfoTipoAcaoController(entidade = SolicitacaoConfirmacaoCliente.class, icone = "fa fa-hand-paper-o", nomeAcao = "Solicitar confirmação a membro da equipe")
    SOLICITACAO_CTR_SOLICIATAR_CONFIRMACAO_EQUIPE,
    @InfoTipoAcaoController(entidade = SolicitacaoArquivoCliente.class, nomeAcao = "Solicitar confirmação do Cliente")
    SOLICITACAO_CTR_SOLICIATAR_CONFIRMACAO_CLIENTE,
    @InfoTipoAcaoFormulario(icone = "fa fa-hand-paper-o", nomeAcao = "Nova solicitação de Equipe")
    SOLICITACAO_FRM_NOVA_SOLICITACAO_EQUIPE,
    @InfoTipoAcaoFormulario(icone = "fa fa-thumbs-up", entidade = SolicitacaoConfirmacaoEquipe.class, nomeAcao = "Solicitar confirmação", estadoFormulario = FabEstadoFormulario.NOVO)
    SOLICITACAO_FRM_NOVO_CONFIRMACAO_EQUIPE,
    @InfoTipoAcaoFormulario(icone = "fa fa-upload", entidade = SolicitacaoArquivoEquipe.class, nomeAcao = "Solicitar Arquivo equipe", estadoFormulario = FabEstadoFormulario.NOVO)
    SOLICITACAO_FRM_NOVO_ARQUIVO_EQUIPE,
    @InfoTipoAcaoFormulario(icone = "fa fa-upload", entidade = SolicitacaoArquivoCliente.class, nomeAcao = "Solicitar Arquivo Cliente", estadoFormulario = FabEstadoFormulario.NOVO)
    SOLICITACAO_FRM_NOVO_ARQUIVO_CLIENTE,
    @InfoTipoAcaoFormulario(icone = "fa fa-upload", entidade = SolicitacaoArquivoEquipe.class, nomeAcao = "Enviar arquivo equipe solicitado", estadoFormulario = FabEstadoFormulario.EDITAR)
    SOLICITACAO_FRM_ENVIAR_ARQUIVO_EQUIPE,
    @InfoTipoAcaoFormulario(icone = "fa fa-thumbs-up", entidade = SolicitacaoConfirmacaoCliente.class, nomeAcao = "Solicitar confirmação Cliente", estadoFormulario = FabEstadoFormulario.NOVO)
    SOLICITACAO_FRM_NOVO_CONFIRMACAO_CLIENTE,
    @InfoTipoAcaoFormulario(icone = "fa fa-upload", nomeAcao = "Solicitar atualização do arquivo")
    SOLICITACAO_FRM_ATUALIZAR_ARQUIVO,
    @InfoTipoAcaoFormulario(icone = "fa fa-clock-o", nomeAcao = "Renegociar prazo")
    SOLICITACAO_FRM_RENEGOCIA_PRAZO,
    @InfoTipoAcaoGestaoEntidade(entidade = ArquivoAnexado.class, utilizarMesmoFormEdicao = false, precisaPermissao = true)
    DOCUMENTOS_PESSOA_MB_GESTAO,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "listarPastasCliente.xhtml", nomeAcao = "Pastas De Clientes", icone = "fa fa-share-alt")
    DOCUMENTOS_PESSOA_FRM_LISTAR_PASTAS_CLIENTE,
    @InfoTipoAcaoFormulario(entidade = ArquivoCliente.class, xhtmlDaAcao = "listarArquivosPastaCliente.xhtml", nomeAcao = "Listar arquivos da pasta do cliente")
    DOCUMENTOS_PESSOA_FRM_LISTAR_ARQUIVOS_PASTA_CLIENTE,
    @InfoTipoAcaoFormulario(entidade = ArquivoAnexado.class, xhtmlDaAcao = "listarArquivosPastasEquipe.xhtml", nomeAcao = "Listar arquivos pasta da equipe")
    DOCUMENTOS_PESSOA_FRM_LISTAR_ARQUIVOS_PASTA_EQUIPE,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "listarPastasEquipe.xhtml", nomeAcao = "Pastas da equipe", icone = "fa fa-archive")
    DOCUMENTOS_PESSOA_FRM_LISTAR_PASTAS_EQUIPE,
    @InfoTipoAcaoFormulario(nomeAcao = "Criar Subpasta", icone = "fa fa-folder-o")
    DOCUMENTOS_PESSOA_FRM_NOVA_SUB_PASTA,
    @InfoTipoAcaoFormulario(icone = "fa fa-share", entidade = ArquivoCliente.class)
    DOCUMENTOS_PESSOA_FRM_COMPARTILHAR,
    @InfoTipoAcaoController(icone = "fa fa-share", entidade = ArquivoCliente.class)
    DOCUMENTOS_PESSOA_CTR_COMPARTILHAR,
    @InfoTipoAcaoController(icone = "fa fa-save", entidade = ArquivoCliente.class, nomeAcao = "Salvar Arquivo Cliente")
    DOCUMENTOS_PESSOA_CTR_SALVAR_MERGE_CLIENTE,
    @InfoTipoAcaoController(icone = "fa fa-save", entidade = ArquivoAnexado.class, nomeAcao = "Salvar ArquivoEquipe")
    DOCUMENTOS_PESSOA_CTR_SALVAR_MERGE_EQUIPE,
    @InfoTipoAcaoGestaoEntidade(entidade = SubPastaPrivada.class)
    SUBPASTA_MB_GESTAO,
    @InfoTipoAcaoFormulario(entidade = SubPastaEquipe.class,
            campos = {"[separador: Metados da subpasta]", "pastaPai", "nome", CPSubPastaPrivada.pastapublica})
    SUBPASTA_FRM_NOVO_PASTA_EQUIPE,
    @InfoTipoAcaoFormulario(entidade = SubpastaCliente.class,
            campos = {"nome", CPSubPastaPrivada.pastapublica}
    )
    SUBPASTA_FRM_NOVO_PASTA_CLIENTE,
    @InfoTipoAcaoController()
    SUBPASTA_CTR_SALVAR_MERGE,

}
