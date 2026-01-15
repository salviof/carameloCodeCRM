/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin;

import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.StatusIntegracao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.apresentacao.DocumentoApresentacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.CategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.TipoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chatBot.TipoChatBot;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.GrupoTipoDadoCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRMLogica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.TipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.resposta.RespostaFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServico;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServicoRecorrente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServicoSazonal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.ParametroMensagemWtzap;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoTipoAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.GrupoUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SistemaAtualInfo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.estadoFormulario.FabEstadoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormCamposAtualizaForm;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormCamposAtualizaGrupoDoCampo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.icones.FabIconeFontAwesome;
import br.org.carameloCode.erp.modulo.crm.api.model.categoriaarquivocliente.CPCategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.categoriaarquivoequipe.CPCategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.metarelacionamento.CPMetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.model.modelodocumentocrm.CPModeloDocumentoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.parametromensagemwtzap.CPParametroMensagemWtzap;
import br.org.carameloCode.erp.modulo.crm.api.model.respostaformulario.CPRespostaFormulario;
import br.org.carameloCode.erp.modulo.crm.api.model.servicooferecido.CPServicoOferecido;
import br.org.carameloCode.erp.modulo.crm.api.model.sistemaerpconfiavel.CPSistemaERPConfiavel;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.CPTipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipochatbot.CPTipoChatBot;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrm.CPTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao.CPTipoDadoCrmLinkIntegracao;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoformulario.CPTipoFormulario;
import br.org.carameloCode.erp.modulo.crm.api.model.tipomensagemmktwhatsapp.CPTipoMensagemMktWhatsApp;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.CPTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervico.CPTipoServico;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervicorecorrente.CPTipoServicoRecorrente;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervicosazonal.CPTipoServicoSazonal;

/**
 *
 * @author desenvolvedor
 */
@InfoModulosCRM(
        modulo = FabModulosCRM.ADMIN_CRM)
public enum FabAcaoCrmAdmin implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Grupos de dados dinâmicos ",
            descricao = "Grupos de dados dinâmicos", icone = "fa fa-pencil-square-o", entidade = GrupoTipoDadoCrm.class, precisaPermissao = true)
    GRUPO_OPCAO_DADOS_CRM_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar grupos dinâmicos", descricao = "Lista os grupos dos campos dinâmicos de atividade", icone = "fa fa-list", entidade = GrupoTipoDadoCrm.class,
            campos = {"[separador: listagem]", "id", "nome"})
    GRUPO_OPCAO_DADOS_CRM_FRM_LISTAR,
    @InfoTipoAcaoFormulario(entidade = GrupoTipoDadoCrm.class,
            campos = {"[separador: Nome do grupo]", "nome"})
    GRUPO_OPCAO_DADOS_CRM_FRM_NOVO,
    @InfoTipoAcaoFormulario(entidade = GrupoTipoDadoCrm.class,
            campos = {"[separador: Nome do grupo]", "id", "nome"})
    GRUPO_OPCAO_DADOS_CRM_FRM_EDITAR,
    @InfoTipoAcaoFormulario(entidade = GrupoTipoDadoCrm.class,
            campos = {"[separador: Nome do grupo]", "id", "nome", "tiposDados"})
    GRUPO_OPCAO_DADOS_CRM_FRM_VISUALIZAR,
    @InfoTipoAcaoController()
    GRUPO_OPCAO_DADOS_CRM_CTR_SALVAR_MERGE,
    @InfoTipoAcaoController(iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER)
    GRUPO_OPCAO_DADOS_CRM_CTR_REMOVER,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Tipo dado integracao",
            descricao = "Gestão de Dados dinamicos focados em conecão com integraca", icone = "fa fa-link", entidade = TipoDadoCrmLinkIntegracao.class, precisaPermissao = true)
    TIPO_DADO_DINAMICO_INTEGRACAO_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: listagem]", "id", "nome", CPTipoDadoCrmLinkIntegracao.mostrarparacliente, "imagem", "fabricaTipoAtributo"})
    TIPO_DADO_DINAMICO_INTEGRACAO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: listagem]", "nome", "imagem", "fabricaTipoAtributo", CPTipoDadoCrmLinkIntegracao.mostrarparacliente, CPTipoDadoCrmLinkIntegracao.nomeclasselogica})
    TIPO_DADO_DINAMICO_INTEGRACAO_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {"[separador: listagem]", "nome", "imagem", "fabricaTipoAtributo", CPTipoDadoCrmLinkIntegracao.mostrarparacliente, CPTipoDadoCrmLinkIntegracao.nomeclasselogica})
    TIPO_DADO_DINAMICO_INTEGRACAO_FRM_EDITAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: listagem]", "nome", "imagem", "fabricaTipoAtributo", CPTipoDadoCrmLinkIntegracao.mostrarparacliente, CPTipoDadoCrmLinkIntegracao.nomeclasselogica})
    TIPO_DADO_DINAMICO_INTEGRACAO_FRM_VISUALIZAR,
    TIPO_DADO_DINAMICO_INTEGRACAO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Tipo dado lógico",
            descricao = "Gestão de Dados obtidos por algorítimos", icone = "fa fa-cogs ",
            entidade = TipoDadoCRMLogica.class, precisaPermissao = true)
    TIPO_DADO_DINAMICO_LOGICO_MB_GERENCIA,
    @InfoTipoAcaoFormulario(campos = {"[separador: listagem]", "id", "nome", "label", "descricao", "fabricaTipoAtributo"})
    TIPO_DADO_DINAMICO_LOGICO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: listagem]", "nome", "label", "descricao", "valorPadrao", "fabricaTipoAtributo"})
    TIPO_DADO_DINAMICO_LOGICO_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {"[separador: listagem]", "nome", "label", "descricao", "valorPadrao", "fabricaTipoAtributo"})
    TIPO_DADO_DINAMICO_LOGICO_FRM_EDITAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: listagem]", "nome", "descricao", "valorPadrao", "fabricaTipoAtributo"})
    TIPO_DADO_DINAMICO_LOGICO_FRM_VISUALIZAR,
    @InfoTipoAcaoController()
    TIPO_DADO_DINAMICO_LOGICO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Tipos dados dinâmicos",
            descricao = "Gestão de Dados dinamicos CRM", icone = "fa fa-pencil-square-o", entidade = TipoDadoCRM.class, precisaPermissao = true)
    OPCAO_DADOS_CRM_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar Tipos de Dados", descricao = "Lista tipo Dados dinâmicos", icone = "fa fa-list", entidade = TipoDadoCRM.class,
            campos = {"[separador: listagem]", "id", "nome", CPTipoDadoCRM.grupotipodadodinamico,
                "descricao", "tipoEntityDadoDinamico", "fabricaTipoAtributo"//, "listaDeOpcoes"
            })
    OPCAO_DADOS_CRM_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Criar Opção de Dados CRM", descricao = "Exibe formulario de criação de opção para um dado crm", icone = "fa fa-plus",
            campos = {"[separador: Informações Basicas]", "nome", "descricao", "label",
                "grupoTipoDadoDinamico",
                "fabricaTipoAtributo", "valorPadrao",
                "[separador: Validação Geral]", "valorMaximo", "valorMinimo", "obrigatorio", "fraseValidacao",
                "[separador: Validação texto]", "mascara",
                "[separador: Validação Numero]", "numeroDeCasasDecimais",
                "[separador: Avançado]", "campoProspectoCorrespondente",
                "[separador: propriedades combo]", "caminhoListagem", "umalistagemDinamica"

            })
    OPCAO_DADOS_CRM_FRM_NOVO,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar Opção de Dados CRM", descricao = "Exibe formulario de edição de uma opção de dado crm", icone = "fa fa-edit",
            campos = {"[separador: Informações Basicas]",
                "nome", "descricao", "label",
                "grupoTipoDadoDinamico",
                "fabricaTipoAtributo", "valorPadrao",
                "[separador: Validação Geral]", "valorMaximo", "valorMinimo", "obrigatorio", "fraseValidacao",
                "[separador: Validação texto]", "mascara",
                "[separador: Validação Numero]", "numeroDeCasasDecimais",
                "[separador: propriedades combo]", "caminhoListagem", "umalistagemDinamica",
                "[separador: Avançado]", "campoProspectoCorrespondente", "nomeClasseAtributoDeclarado"})
    OPCAO_DADOS_CRM_FRM_EDITAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Cadastrar opções", icone = "fa fa-bars")
    OPCAO_DADOS_CRM_FRM_DADOS_DINAMICOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Visualizar Opção de Dado CRM", descricao = "Exibe as informações de uma opção de dado crm", icone = "fa fa-eye", entidade = TipoDadoCRM.class,
            campos = {"id", "nome", "descricao"})
    OPCAO_DADOS_CRM_FRM_VISUALIZAR,
    @InfoTipoAcaoController(nomeAcao = "Salvar", descricao = "Registra as informações no sistema", entidade = TipoDadoCRM.class, icone = "fa fa-save", precisaPermissao = true)
    OPCAO_DADOS_CRM_CTR_SALVAR_MERGE,
    @InfoTipoAcaoController(nomeAcao = "Remover", descricao = "Registra as informações no sistema", entidade = TipoDadoCRM.class, iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER)
    OPCAO_DADOS_CRM_CTR_REMOVER,
    @InfoTipoAcaoGestaoEntidade(descricao = "Origens de Cartões", nomeAcao = "Origens de Cartões", entidade = OrigemProspecto.class)

    ORIGEM_PROSPECTO_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Criar Origem de Prospecto", descricao = "Exibe formulario de criação de origens para prospectos", icone = "fa fa-plus", entidade = OrigemProspecto.class,
            campos = {"nome", "descricao", "relacionamentoPadrao"})
    ORIGEM_PROSPECTO_FRM_NOVO,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar Origem de Prospecto", descricao = "Exibe formulario de edição de origens de prospectos", icone = "fa fa-edit", entidade = OrigemProspecto.class,
            campos = {"nome", "descricao", "relacionamentoPadrao"})
    ORIGEM_PROSPECTO_FRM_EDITAR,
    @InfoTipoAcaoFormulario(iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER)
    ORIGEM_PROSPECTO_FRM_REMOVER,
    @InfoTipoAcaoController(exibirModalConfirmacao = true, fraseComunicação = "Deseja remover a origem [nome] para sempre")
    ORIGEM_PROSPECTO_CTR_REMOVER,
    @InfoTipoAcaoFormulario(icone = "fa fa-arrow-circle-right")
    ORIGEM_PROSPECTO_FRM_CONVERTER,
    @InfoTipoAcaoController(icone = "fa fa-arrow-circle-right", exibirModalConfirmacao = true, fraseComunicação = "Deseja converter essa origem ?")
    ORIGEM_PROSPECTO_CTR_CONVERTER,
    @InfoTipoAcaoController(exibirModalConfirmacao = true, fraseComunicação = "Deseja mover os cartões para a ")
    ORIGEM_PROSPECTO_CTR_MOVER,
    @InfoTipoAcaoFormulario(nomeAcao = "Visualizar Origem de Prospecto", descricao = "Exibe as informações de uma Origem de Prospecto", icone = "fa fa-eye", entidade = OrigemProspecto.class,
            campos = {"id", "nome", "descricao", "relacionamentoPadrao"})
    ORIGEM_PROSPECTO_FRM_VISUALIZAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar Origem de Prospecto", descricao = "Listas as origens de prospectos", icone = "fa fa-list", entidade = OrigemProspecto.class,
            campos = {"id", "nome", "descricao", "umaOrigempublica"})
    ORIGEM_PROSPECTO_FRM_LISTAR,
    @InfoTipoAcaoController(nomeAcao = "Salvar", descricao = "Registra os dados informados", icone = "fa fa-save", entidade = OrigemProspecto.class)
    ORIGEM_PROSPECTO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Serviços de Orçamento", descricao = "Gerenciar Serviços Disponiveis", entidade = TipoServico.class, precisaPermissao = true)
    SERVICO_DIPONIVEL_MB_GESTAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Cadastrar Serviço", descricao = "Cadastrar Serviço Disponivel", entidade = TipoServico.class,
            campos = {"nome", "descricao",
                "[separador: Tipo de pagamento (Sazonal ou Recorrente)]",
                CPTipoServico.gerapgtorecorrente, CPTipoServico.gerapgtosazonal,
                "[separador: Apresentaçãoo]",
                "descricaoApresentacao", "arquivoApresentacao", "urlDetalhes"},
            icone = "fa fa-plus-square"
    )
    @InfoTipoAcaoFormCamposAtualizaGrupoDoCampo(campos = {CPTipoServico.gerapgtorecorrente, CPTipoServico.gerapgtosazonal})
    SERVICO_DIPONIVEL_FRM_NOVO,
    @InfoTipoAcaoFormulario(nomeAcao = "Cadastrar item Recorrente", campos = {"[separador: Dados básicos]", CPTipoServico.nome, CPTipoServico.descricao,
        "[separador: Valor item recorrente]", CPTipoServicoRecorrente.valormensalmin, CPTipoServicoRecorrente.valormensalmax, "[separador: Divulgação]", CPTipoServico.arquivoapresentacao,
        CPTipoServico.descricaoapresentacao, CPTipoServico.urldetalhes}, icone = "fa fa-retweet",
            entidade = TipoServicoRecorrente.class
    )
    SERVICO_DIPONIVEL_FRM_NOVO_ITEM_RECORRENTE,
    @InfoTipoAcaoFormulario(nomeAcao = "Cadastrar item Sazonal", campos = {"[separador: Dados básicos]", CPTipoServico.nome, CPTipoServico.descricao,
        "[separador: Item sazonal]", CPTipoServicoSazonal.valorsetupmin, CPTipoServicoSazonal.valorsetupmax,
        "[separador: Divulgação]", CPTipoServico.arquivoapresentacao,
        CPTipoServico.descricaoapresentacao, CPTipoServico.urldetalhes},
            entidade = TipoServicoSazonal.class
    )
    SERVICO_DIPONIVEL_FRM_NOVO_ITEM_SAZONAL,
    @InfoTipoAcaoController(comunicacao = FabTipoComunicacao.PERGUNTAR_SIM_OU_NAO, nomeAcao = "Converter para Sazonal", fraseComunicação = "Ao converter para sazonal, os valores mínímo e máximo do serviço serão excluídos")
    SERVICO_DIPONIVEL_CTR_CONVERTER_PARA_SAZONAL,
    @InfoTipoAcaoController(comunicacao = FabTipoComunicacao.PERGUNTAR_SIM_OU_NAO, nomeAcao = "Converter para Recorrente", fraseComunicação = "Ao converter para recorrente, os valores mínímo e máximo do serviço serão excluídos")
    SERVICO_DIPONIVEL_CTR_CONVERTER_PARA_RECORRENTE,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar Serviços", descricao = "Listar Serviços Disponiveis", entidade = TipoServico.class,
            campos = {"id", "nome", CPTipoServico.tipocobranca, "descricao"})
    SERVICO_DIPONIVEL_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar Serviços", descricao = "Editar Serviço Disponivel", entidade = TipoServico.class,
            campos = {"nome", "descricao", "[separador: Apresentaçãoo]",
                "descricaoApresentacao"})
    SERVICO_DIPONIVEL_FRM_EDITAR,
    @InfoTipoAcaoController(nomeAcao = "Salvar Serviço", descricao = "Salvar Serviço Disponivel", entidade = TipoServico.class)
    SERVICO_DIPONIVEL_CTR_SALVAR_MERGE,
    @InfoTipoAcaoFormulario(nomeAcao = "Remover Serviço", descricao = "Remove o serviço de forma definitiva", iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER)
    SERVICO_DIPONIVEL_FRM_REMOVER,
    @InfoTipoAcaoController(nomeAcao = "Remover Serviço", descricao = "Remove o serviço de forma definitiva", iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER, exibirModalConfirmacao = true, fraseComunicação = "Certeza que deseja exluir [" + CPServicoOferecido.nome + "] definitivamente?")
    SERVICO_DIPONIVEL_CTR_REMOVER,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Gerenciar Usuarios", entidade = UsuarioCRM.class, precisaPermissao = true)
    CADASTRO_USUARIO_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar Usuarios", entidade = UsuarioCRM.class, descricao = "Lista os usuários cadastrados no sistema",
            campos = {"id", "nome", "email", "telefone", "ativo"})
    CADASTRO_USUARIO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar Usuário", entidade = UsuarioCRM.class, descricao = "Exibe formulario para edição de um usuário",
            campos = {"nome", "apelido", "email", "grupo", "telefone"})
    CADASTRO_USUARIO_FRM_EDITAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Alterar Senha", entidade = UsuarioCRM.class, descricao = "Alteração de senha",
            campos = {"senha"}, xhtmlDaAcao = "cadastro_usuario_editar.xhtml", icone = "fa fa-key")
    CADASTRO_USUARIO_FRM_EDITAR_SENHA,
    @InfoTipoAcaoFormulario(nomeAcao = "Criar Usuário", entidade = UsuarioCRM.class, descricao = "Exibe formulario para criação de um usuário",
            campos = {"[separador: Dados]", "nome", "apelido", "email", "senha", "grupo", "telefone"})
    CADASTRO_USUARIO_FRM_NOVO,
    @InfoTipoAcaoFormulario(nomeAcao = "Visualizar Usuário", entidade = UsuarioCRM.class, descricao = "Exibe formulario para visualização de um usuário",
            campos = {"id", "nome", "email", "senha", "telefone"})
    CADASTRO_USUARIO_FRM_VISUALIZAR,
    @InfoTipoAcaoFormulario(campos = {"tagsMonitoradas"}, icone = "fa fa-tags", nomeAcao = "Tags monitoradas")
    CADASTRO_USUARIO_FRM_TAGS_ACOMPANHAMENTO,
    @InfoTipoAcaoController(nomeAcao = "Salvar", entidade = UsuarioCRM.class, descricao = "Grava os dados informados")
    CADASTRO_USUARIO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoController(nomeAcao = "Alterar Status", entidade = UsuarioCRM.class, descricao = "Grava os dados informados", precisaPermissao = true,
            fraseComunicação = "Deseja Alterar o status do [apelido] ? O status DEIXARÁ de ser: [ativo] ")

    CADASTRO_USUARIO_CTR_ALTERAR_STATUS,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Modelos de Documentos", iconeFonteAnsowame = FabIconeFontAwesome.ESCRITORIO_DOCUMENTO_TEXTO,
            entidade = ModeloDocumentoTipoAtividade.class, precisaPermissao = true)
    MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(campos = {"id", "nome", "descricao", "tipoAtividadeVinculada", CPModeloDocumentoCRM.categoriaarquivocliente, CPModeloDocumentoCRM.categoriaarquivoequipe})
    MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: Dados novo Documento]", "nome", "descricao", "documento", "tipoAtividadeVinculada", "[separador: Ao concluir atividade salvar em:]", CPModeloDocumentoCRM.categoriaarquivocliente, CPModeloDocumentoCRM.categoriaarquivoequipe})
    MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {"[separador: Dados Doc]", "nome", "descricao",
        "documento",
        "[separador: Teste e dados dinâmicos]", "leadParaTestes",
        "tipoAtividadeVinculada", "[separador: Ao concluir atividade salvar em:]", CPModeloDocumentoCRM.categoriaarquivocliente, CPModeloDocumentoCRM.categoriaarquivoequipe})
    MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_FRM_EDITAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar dados dinamicos do lead")
    MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_FRM_EDITAR_DADOS_LEAD,
    @InfoTipoAcaoController(nomeAcao = "Salvar Modelo")
    MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_MERGE,
    @InfoTipoAcaoController(nomeAcao = "Testar com Empresa Teste",
            iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ENGRENAGEM)
    MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_GERAR_TESTE,
    @InfoTipoAcaoController(nomeAcao = "Remover", comunicacao = FabTipoComunicacao.PERGUNTAR_SIM_OU_NAO,
            iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER)
    MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_REMOVER,
    @InfoTipoAcaoController(nomeAcao = "Atualizar empresa",
            iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ENGRENAGEM)
    MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_ATUALIZAR_PROSPECTO_TESTE,
    @InfoTipoAcaoGestaoEntidade(entidade = TipoRelacionamento.class, nomeAcao = "Tipos de Relacionamento",
            precisaPermissao = true)
    TIPO_RELACIONAMENTO_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(entidade = TipoRelacionamento.class,
            campos = {"id", "nome", "descricao", "nomeDoRelacionado", "peso",
                "metaRelacionamento", "qtdEmpresasNesteTipoRelacionamento"}, precisaPermissao = true)
    TIPO_RELACIONAMENTO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(icone = "fa fa-refresh", nomeAcao = "Converter Tipo de relacionamento", campos = {"tipoRelacionamentoConversao"})
    TIPO_RELACIONAMENTO_FRM_CONVERTER,
    @InfoTipoAcaoController(icone = "fa fa-refresh", nomeAcao = "Converter")
    TIPO_RELACIONAMENTO_CTR_CONVERTER,
    @InfoTipoAcaoFormulario(entidade = TipoRelacionamento.class,
            precisaPermissao = false, icone = "fa fa-id-card")
    TIPO_RELACIONAMENTO_FRM_RESPONSAVEIS,
    @InfoTipoAcaoFormulario(icone = "fa fa-keyboard-o",
            nomeAcao = "Dados Necessários",
            campos = {"[separador: Dados Gerais]", "dadosNescessarios"})
    TIPO_RELACIONAMENTO_FRM_DADOS_NESCESSARIOS,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Atividades Disponíveis",
            iconeFonteAnsowame = FabIconeFontAwesome.REG_AGRUPAR_REGISTROS)
    TIPO_RELACIONAMENTO_FRM_ATIVIDADES_DISPONIVEIS,
    @InfoTipoAcaoFormulario(campos = {"[separador: Dados novo Relacionamento]", "nome", "descricao", "dicas", "nomeDoRelacionado", "peso", "cor", "resultado",
        "metaRelacionamento",
        "[separador: Opções de tempo para execução]", "tempoAceitavelResolucao", "tempoAcaoInerciaRelacionamento", "relacionamentoPeranteInercia", "relacionamentoPeranteInercia",
        "[separador: Integracao]",
        CPTipoRelacionamento.tipomensagemwtzp
    })
    TIPO_RELACIONAMENTO_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {"[separador: Dados Relacionamento]", "nome", "descricao", "dicas", "nomeDoRelacionado", "peso", "cor", "resultado",
        "metaRelacionamento",
        "[separador: Opções versão Beta]", "tempoAceitavelResolucao", "tempoAcaoInerciaRelacionamento", "relacionamentoPeranteInercia", "relacionamentoPeranteInercia"})
    TIPO_RELACIONAMENTO_FRM_EDITAR,
    @InfoTipoAcaoController(nomeAcao = "Mover serviços", fraseComunicação = "Deseja mover os servi", iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER, comunicacao = FabTipoComunicacao.PERGUNTAR_SIM_OU_NAO)
    TIPO_RELACIONAMENTO_CTR_MOVERLEADS,
    @InfoTipoAcaoController(nomeAcao = "Excluir Relacionamento", fraseComunicação = "Excluir de maneira irreversível, o relacionamento [nome] e todas as atividades vinculadas?", iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER, comunicacao = FabTipoComunicacao.PERGUNTAR_SIM_OU_NAO)
    TIPO_RELACIONAMENTO_CTR_REMOVER,
    @InfoTipoAcaoFormulario(iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER, campos = {"[separador: Dados Relacionamento]", "nome", "descricao", "dicas", "nomeDoRelacionado", "peso", "cor", "resultado", "metaRelacionamento"}, estadoFormulario = FabEstadoFormulario.VISUALIZAR)
    TIPO_RELACIONAMENTO_FRM_REMOVER,
    TIPO_RELACIONAMENTO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Tipos de Atividade", entidade = TipoAtividadeCRM.class, precisaPermissao = true)
    TIPO_ATIVIDADE_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(campos = {"[separador:Tipos de Atividade]", "id", "nomeAtividade", "relacionamentoGerado", "cor", "temDisparoDeEmail", "icone"})
    TIPO_ATIVIDADE_FRM_LISTAR,
    @InfoTipoAcaoFormCamposAtualizaGrupoDoCampo(campos = {"progresso", "regresso"})
    @InfoTipoAcaoFormulario(campos = {
        "[separador: Dados Gerais]", "nomeAtividade", "nomeInicioAtivida", "nomeFimAtividade",
        "[separador: Visualizacao]", "cor", "icone",
        "[separador: Classificação Deste Tipo de atividade", "progresso", "regresso", CPTipoAtividadeCRM.disponivelapenasposvendas,
        "[separador: Condições especiais de execução]",
        "precisaServicosDefinidos", "precisaTerPreAnalise",
        "precisaEnviarDocumento", "precisaTerImagem", "precisaTerAnexo", "precisaTerSite",
        "execucaoDiretaSemRelatorio",
        "[separador: Plugin]", "acaoDePLuginVunculado",
        "[separador: ChatBot]", CPTipoAtividadeCRM.tipochatbot,
        "[separador: Finalização de atividade]", "resultaEmRelacionamentoAnterior", "relacionamentoGerado", CPTipoAtividadeCRM.tipomensagemwtzap,
        "atividadeAgendada", "diasAgendarNovaAtividade"})
    TIPO_ATIVIDADE_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {
        "[separador: Dados Gerais]", "nomeAtividade", "nomeInicioAtivida", "nomeFimAtividade",
        "[separador: Visualizacao]", "cor", "icone",
        "[separador: Classificação Deste Tipo de atividade", "progresso", "regresso", CPTipoAtividadeCRM.disponivelapenasposvendas,
        "[separador: Condições especiais de execução]", "precisaServicosDefinidos", "precisaTerPreAnalise", "precisaEnviarDocumento",
        "precisaTerImagem", "precisaTerAnexo", "precisaTerSite", "execucaoDiretaSemRelatorio",
        "[separador: Plugin]", "acaoDePLuginVunculado",
        "[separador: ChatBot]", CPTipoAtividadeCRM.tipochatbot,
        "[separador: Finalização de atividade]", "resultaEmRelacionamentoAnterior", "relacionamentoGerado", CPTipoAtividadeCRM.tipomensagemwtzap,
        "atividadeAgendada", "diasAgendarNovaAtividade"})
    @InfoTipoAcaoFormCamposAtualizaForm(campos = {"progresso", "regresso"})
    TIPO_ATIVIDADE_FRM_EDITAR,
    @InfoTipoAcaoFormulario(campos = {CPTipoAtividadeCRM.tiposdadoscoletarnaatividade}, nomeAcao = "Tipos de dado para coletar", icone = "fa fa-database")
    TIPO_ATIVIDADE_FRM_DADOS_COLETAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Limitar Acessos", icone = "fa fa-shield")
    TIPO_ATIVIDADE_FRM_PERMISSAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Vincular a modelo de e-mail", campos = {
        "[separador: Formatar E-Mail]", "modeloEmail.assunto", "modeloEmail.textoModelo"}, icone = "fa fa-envelope-square")
    TIPO_ATIVIDADE_FRM_FORMATAR_EMAIL_VINCULADO,
    @InfoTipoAcaoController(nomeAcao = "Vincular E-Mail", icone = "fa fa-envelope-square")
    TIPO_ATIVIDADE_CTR_VINCULAR_EMAIL,
    @InfoTipoAcaoController(nomeAcao = "Desvincular E-Mail", icone = "fa fa-times")
    TIPO_ATIVIDADE_CTR_DESVINCULAR_EMAIL,
    TIPO_ATIVIDADE_CTR_SALVAR_MERGE,
    @InfoTipoAcaoFormulario(nomeAcao = "Remover", iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER)
    TIPO_ATIVIDADE_FRM_REMOVER,
    @InfoTipoAcaoController(nomeAcao = "Remover", iconeFonteAnsowame = FabIconeFontAwesome.REG_REMOVER,
            exibirModalConfirmacao = true,
            fraseComunicação = "O tipo de atividade, e as atividades vinculadas a '[nomeAtividade]' serão exluidas de maneira ireversível, ok? ")
    TIPO_ATIVIDADE_CTR_REMOVER,
    @InfoTipoAcaoGestaoEntidade(entidade = AtividadeCRM.class)
    ATIVIDADES_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-history")
    ATIVIDADES_FRM_LISTAR_ATIVIDADES,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "DashBoard Admin", entidade = SistemaAtualInfo.class, precisaPermissao = true)
    ADMINISTRATIVO_PAGINA_PRINCIPAL_MB,
    @InfoTipoAcaoGestaoEntidade(entidade = MetaRelacionamento.class, nomeAcao = "Estágio de Relacionamento", precisaPermissao = true)
    META_RELACIONAMENTO_MB,
    @InfoTipoAcaoFormulario(campos = {
        "[separador:Estágio de Relacionamento]", "id", "nome", "cor", CPMetaRelacionamento.peso})
    META_RELACIONAMENTO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {
        "[separador: Dados Estágio de Relacionamento]", "nome", "descricaoEtapaVisaoPrestador", "descricaoEtapaVisaoCliente", "cor", CPMetaRelacionamento.peso, CPMetaRelacionamento.cliente})
    META_RELACIONAMENTO_FRM_EDITAR,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Atividades Disponíveis",
            campos = {CPMetaRelacionamento.tiposatividadedisponiveis},
            iconeFonteAnsowame = FabIconeFontAwesome.REG_AGRUPAR_REGISTROS)
    META_RELACIONAMENTO_FRM_ATIVIDADES_VINCULADOS,
    @InfoTipoAcaoFormulario(campos = {
        "[separador: Dados Estágio de Relacionamento]", "nome", "descricaoEtapaVisaoPrestador", "descricaoEtapaVisaoCliente", "cor"})
    META_RELACIONAMENTO_FRM_NOVO,
    META_RELACIONAMENTO_CTR_MERGE,
    @InfoTipoAcaoFormulario(campos = {"metaConversao"}, icone = "fa fa-refresh", nomeAcao = "Converter meta")
    META_RELACIONAMENTO_FRM_CONVERTER,
    @InfoTipoAcaoController(nomeAcao = "Converter", icone = "fa fa-refresh")
    META_RELACIONAMENTO_CTR_CONVERTER,
    @InfoTipoAcaoController()
    META_RELACIONAMENTO_CTR_REMOVER,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Importador Cartões", entidade = PessoaJuridica.class)
    IMPORTADOR_PROSPECTO_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Importar Cartões", icone = "fa fa-upload")
    IMPORTADOR_PROSPECTO_FRM_IMPOTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Relatorio Importacao", icone = "fa fa-file-text-o")
    IMPORTADOR_PROSPECTO_FRM_RELATORIO,
    @InfoTipoAcaoController(icone = "fa fa-upload")
    IMPORTADOR_PROSPECTO_CTR_IMPORTAR,
    @InfoTipoAcaoGestaoEntidade(entidade = PessoaJuridica.class, icone = "fa fa-line-chart", precisaPermissao = true)
    MAUTIC_MB_INTEGRACAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar Configurações", iconeFonteAnsowame = FabIconeFontAwesome.REG_SALVAR)
    MAUTIC_FRM_CONFIGURACOES,
    @InfoTipoAcaoController(nomeAcao = "Salvar configurações", iconeFonteAnsowame = FabIconeFontAwesome.REG_SALVAR)
    MAUTIC_CTR_SALVAR_CONFIGURACOES,
    @InfoTipoAcaoFormulario(nomeAcao = "Atualizar Empresas Mautic", icone = "fa fa-upload")
    MAUTIC_FRM_EXPORTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Selecionar opções", icone = "fa fa-map-signs")
    MAUTIC_FRM_SELECIONAR_OPCOES,
    @InfoTipoAcaoController(nomeAcao = "Exportar", icone = "fa fa-upload")
    MAUTIC_CTR_EXPORTAR_DADOS,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-folder-o", entidade = CategoriaArquivoCliente.class, precisaPermissao = true, nomeAcao = "Diretórios do Cliente")
    CATEGORIA_DOCUMENTO_CLIENTE_MB_GESTAO,
    @InfoTipoAcaoFormulario(campos = {"id", "nome", "icone", CPCategoriaArquivoCliente.pastapai, //CPCategoriaArquivoCliente.subpastas
})
    CATEGORIA_DOCUMENTO_CLIENTE_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {"nome", "icone"})
    CATEGORIA_DOCUMENTO_CLIENTE_FRM_NOVO,
    @InfoTipoAcaoFormulario(nomeAcao = "Novo subDiretorio", campos = {"[separador: Diretorio pai:]", CPCategoriaArquivoCliente.pastapai,
        "[separador: Detalhes subdiretorio]",
        "nome", "icone"})
    CATEGORIA_DOCUMENTO_CLIENTE_FRM_NOVO_SUB_DIRETORIO,
    @InfoTipoAcaoFormulario(campos = {"nome", "icone"})
    CATEGORIA_DOCUMENTO_CLIENTE_FRM_EDITAR,
    @InfoTipoAcaoFormulario(campos = {"id", "nome", "icone"})
    CATEGORIA_DOCUMENTO_CLIENTE_FRM_VISUALIZAR,
    CATEGORIA_DOCUMENTO_CLIENTE_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(entidade = DocumentoApresentacao.class,
            nomeAcao = "Arquivos de Apresentação", icone = "fa fa-file-powerpoint-o", utilizarMesmoFormEdicao = false)
    DOCUMENTO_APRESENTACAO_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar Apresentações", entidade = DocumentoApresentacao.class, precisaPermissao = true,
            campos = {"id", "nome", "ativo"})
    DOCUMENTO_APRESENTACAO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar Apresentação",
            precisaPermissao = true,
            descricao = "Edita os dados básicos, atualizar arquivo e vincular a serviço",
            campos = {"tiposServicoVinculado", "nome", "arquivo"}
    )
    DOCUMENTO_APRESENTACAO_FRM_EDITAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Novas Apresentações", descricao = "Sobe novas apresentações para o servidor, podem ser imagens  powerpoints e outros")
    DOCUMENTO_APRESENTACAO_FRM_NOVO,
    @InfoTipoAcaoController(nomeAcao = "Savar Edião")
    DOCUMENTO_APRESENTACAO_CTR_SALVAR_MERGE,
    DOCUMENTO_APRESENTACAO_CTR_UPLOAD_APRESENTACOES,
    @InfoTipoAcaoController(nomeAcao = "Excluir", precisaPermissao = true, exibirModalConfirmacao = true,
            fraseComunicação = "Deseja excluir a apresentação [DocumentoApresentacao.nome]? ",
            respostasComunicacaoPersonalizada = {FabTipoRespostaComunicacao.SIM, FabTipoRespostaComunicacao.NAO}
    )
    DOCUMENTO_APRESENTACAO_CTR_REMOVER,
    @InfoTipoAcaoController(nomeAcao = "Alterar Status", descricao = "Ativar e desativar apresentações", precisaPermissao = true,
            exibirModalConfirmacao = true,
            fraseComunicação = "Deseja alterar o status do documento  [DocumentoApresentacao.nome], de  [DocumentoApresentacao.ativo]  para o contŕario? ",
            respostasComunicacaoPersonalizada = {FabTipoRespostaComunicacao.SIM, FabTipoRespostaComunicacao.NAO},
            iconeFonteAnsowame = FabIconeFontAwesome.REG_ATUALIZAR)
    DOCUMENTO_APRESENTACAO_CTR_ATIVAR_DESATIVAR,
    @InfoTipoAcaoGestaoEntidade(entidade = UsuarioCRM.class, nomeAcao = "Configurações de eMail", icone = "fa fa-cogs", precisaPermissao = true)
    CONFIGURACOES_GERAIS_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Configurar e-mails", icone = "fa fa-refresh", precisaPermissao = true)
    CONFIGURACOES_GERAIS_FRM_CONFIGURAR_EMAILS,
    @InfoTipoAcaoController(nomeAcao = "Salvar configuração caixa de entrada", icone = "fa fa-save", precisaPermissao = true)
    CONFIGURACOES_GERAIS_CTR_SALVAR_CONFIGURACAO,
    @InfoTipoAcaoController(nomeAcao = "Testar conta eMail", icone = "fa fa-check", precisaPermissao = true)
    CONFIGURACOES_GERAIS_CTR_TESTAR_CONFIGURACAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar Assinaturas", icone = "fa fa-pencil")
    CONFIGURACOES_GERAIS_FRM_LISTAR_ASSINATURAS,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar Assinatura", icone = "fa fa-pencil")
    CONFIGURACOES_GERAIS_FRM_EDITAR_ASSINATURA,
    @InfoTipoAcaoController(nomeAcao = "Salvar assinatura", icone = "fa fa-save")
    CONFIGURACOES_GERAIS_CTR_SALVAR_ASSINATURA,
    @InfoTipoAcaoFormulario(nomeAcao = "Receber E-Mails", icone = "fa fa-envelope-o")
    CONFIGURACOES_GERAIS_FRM_RECEBER_EMAILS,
    @InfoTipoAcaoController(nomeAcao = "Receber e-Mail", icone = "fa fa-envelope-o")
    CONFIGURACOES_GERAIS_CTR_RECEBER_EMAILS,
    @InfoTipoAcaoController(nomeAcao = "Receber e-Mails", icone = "fa fa-envelope-o")
    CONFIGURACOES_GERAIS_CTR_RECEBER_EMAIL,
    @InfoTipoAcaoGestaoEntidade(entidade = PessoaJuridica.class, nomeAcao = "Manutenção de dados dinâmicos")
    MANUTENCAO_DADOS_PROSPECTO_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar prospectos")
    MANUTENCAO_DADOS_PROSPECTO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Visualizar informacao", xhtmlDaAcao = "manutecaoDado.xhtml", iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_ENGRENAGEM, estadoFormulario = FabEstadoFormulario.VISUALIZAR)
    MANUTENCAO_DADOS_PROSPECTO_FRM_VISUALIZAR_DADO,
    @InfoTipoAcaoFormulario(nomeAcao = "Proxima Informação ", xhtmlDaAcao = "manutecaoDado.xhtml", iconeFonteAnsowame = FabIconeFontAwesome.REG_PROXIMO, estadoFormulario = FabEstadoFormulario.VISUALIZAR)
    MANUTENCAO_DADOS_PROSPECTO_FRM_PROXIMO_DADO,
    @InfoTipoAcaoFormulario(nomeAcao = "Dado anterior", xhtmlDaAcao = "manutecaoDado.xhtml", iconeFonteAnsowame = FabIconeFontAwesome.REG_ANTERIOR, estadoFormulario = FabEstadoFormulario.VISUALIZAR)
    MANUTENCAO_DADOS_PROSPECTO_FRM_DADO_ANTERIOR,
    @InfoTipoAcaoController(nomeAcao = "Salvar")
    MANUTENCAO_DADOS_PROSPECTO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(entidade = PessoaJuridica.class, nomeAcao = "Adminstração de leads")
    PROSPECTO_ADMIN_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Listar Prospectos",
            descricao = "Exibe as informações de um Prospecto",
            icone = "fa fa-address-book-o",
            entidade = PessoaJuridica.class,
            campos = {"id", "nome", "email", "telefonePrincipal", "origem", "usuariosResponsaveis"})
    PROSPECTO_ADMIN_FRM_LISTAR,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Editar Prospecto",
            descricao = "Exibe formulario de edição de Prospecto",
            icone = "fa fa-edit",
            entidade = PessoaJuridica.class,
            campos = {"[separador:Dados Básicos]",
                "nome", "origem", "porte", "tipoEmpresa", "relacionamento",
                "[separador:Dados de contato]",
                "email", "telefonePrincipal", "responsavel",
                "[separador:Locais]",
                "endereco", "site",
                "[separador:Configuração ]",
                "umPerfilPrivado",
                "[separador:Observações]",
                "observacao"})
    PROSPECTO_ADMIN_FRM_EDITAR,
    @InfoTipoAcaoController
    PROSPECTO_ADMIN_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(
            nomeAcao = "Gerenciar Grupos", icone = "fa fa-users",
            precisaPermissao = true, entidade = GrupoUsuarioSB.class
    )
    GRUPO_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(
            nomeAcao = "Novo",
            descricao = "Permite criar um grupo de usuários para ser utilizado por administradores do VipKompras",
            iconeFonteAnsowame = FabIconeFontAwesome.REG_NOVO, precisaPermissao = false,
            campos = {"nome", "descricao"},
            codigoJira = "UI026", entidade = GrupoUsuarioCRM.class)
    GRUPO_FRM_NOVO,
    @InfoTipoAcaoFormulario(
            entidade = GrupoUsuarioCRM.class,
            icone = "fa fa-edit",
            campos = {"nome", "descricao"},
            precisaPermissao = false,
            codigoJira = "UC021")
    GRUPO_FRM_EDITAR,
    @InfoTipoAcaoFormulario(
            entidade = GrupoUsuarioCRM.class, nomeAcao = "Listar Grupos de usuarios",
            descricao = "Lista os tipos de grupos em que um usuario pode participar",
            iconeFonteAnsowame = FabIconeFontAwesome.REG_LISTAR,
            precisaPermissao = false,
            codigoJira = "UI026",
            campos = {"id", "nome", "ativo"})
    GRUPO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(
            icone = "fa fa-search-plus", nomeAcao = "Ver Detalhe do Grupo",
            precisaPermissao = false, codigoJira = "UI028")
    GRUPO_FRM_VISUALIZAR,
    @InfoTipoAcaoController(
            nomeAcao = "Ativar/Desativar", icone = "fa fa-retweet", precisaPermissao = false,
            codigoJira = "UC021", precisaJustificativa = true,
            comunicacao = FabTipoComunicacao.PERGUNTAR_SIM_OU_NAO)
    GRUPO_CTR_ALTERAR_STATUS,
    @InfoTipoAcaoController(
            nomeAcao = "Ativar/Desativar", icone = "fa fa-retweet", precisaPermissao = false,
            codigoJira = "UC021", precisaJustificativa = true, entidade = GrupoUsuarioCRM.class,
            comunicacao = FabTipoComunicacao.PERGUNTAR_SIM_OU_NAO
    )
    GRUPO_CTR_ALTERAR_STATUS_GRUPO_SK,
    @InfoTipoAcaoFormulario(nomeAcao = "Permissões", icone = "fa fa-key", codigoJira = "UI026")
    GRUPO_FRM_EDITAR_PERMISSOES,
    @InfoTipoAcaoFormulario(entidade = GrupoUsuarioCRM.class, nomeAcao = "Permissões", xhtmlDaAcao = "grupo_editar_permissoes.xhtml", icone = "fa fa-key", codigoJira = "UI026")
    GRUPO_FRM_EDITAR_PERMISSOES_SK,
    @InfoTipoAcaoController(nomeAcao = "Salvar", iconeFonteAnsowame = FabIconeFontAwesome.REG_SALVAR)
    GRUPO_CTR_SALVAR_PERMISSOES,
    /**
     * ACAO DE LISTAGEM DE USUARIOS DE GRUPO
     */
    @InfoTipoAcaoFormulario(iconeFonteAnsowame = FabIconeFontAwesome.REG_LISTAR,
            entidade = GrupoUsuarioSB.class)
    GRUPO_FRM_LISTAR_USUARIOS,
    /**
     * ACAO DE PERSISTENCIA DE ALTERACOES DE GRUPO
     */
    @InfoTipoAcaoController(entidade = GrupoUsuarioCRM.class, icone = "fa fa-save",
            nomeAcao = "Salvar Alterações", precisaPermissao = false, codigoJira = "UC021")
    GRUPO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(entidade = TagAtendimento.class, nomeAcao = "Tags de Cartões", icone = "fa fa-tags")
    TAG_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar Tags", icone = "fa fa-tags", entidade = TagAtendimento.class,
            campos = {"nome", "cor", "descricao"}
    )
    TAG_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar Tags", icone = "fa fa-tag", entidade = TagAtendimento.class, estadoFormulario = FabEstadoFormulario.EDITAR,
            campos = {"nome", "cor", "descricao"}
    )
    TAG_FRM_EDITAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Nova Tag", icone = "fa fa-tag", entidade = TagAtendimento.class, estadoFormulario = FabEstadoFormulario.NOVO,
            campos = {"nome", "cor", "descricao"})
    TAG_FRM_NOVO,
    @InfoTipoAcaoController(nomeAcao = "Salvar", icone = "fa fa-save", entidade = TagAtendimento.class)
    TAG_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(entidade = StatusIntegracao.class, icone = "fa fa-cubes", nomeAcao = "Integrações")
    INTEGRACOES_MB_GESTAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar integrações", icone = "fa fa-cubes")
    INTEGRACOES_FRM_LISTAR_INTEGRACOES,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-life-ring", entidade = TipoChamado.class, nomeAcao = "Tipos de chamadoS")
    TIPO_CHAMADO_MB,
    @InfoTipoAcaoFormulario(icone = "fa fa-life-ring", campos = {"id", "nome", "descricao", "ativo"})
    TIPO_CHAMADO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: Dados básicos tipo Chamado]", "nome", "descricao", "ativo", "[separador: Usuários responsáveis]", "responsaveis"})
    TIPO_CHAMADO_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {"[separador: Dados básicos tipo Chamado]", "nome", "descricao", "ativo", "[separador: Usuários responsáveis]", "responsaveis"})
    TIPO_CHAMADO_FRM_EDITAR,
    @InfoTipoAcaoController()
    TIPO_CHAMADO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-plug", precisaPermissao = false, entidade = SistemaERPConfiavel.class, nomeAcao = "Integrações ERP")
    INTEGRACAO_ERP_MB_GESTAO,
    @InfoTipoAcaoFormulario(campos = {CPSistemaERPConfiavel.id, CPSistemaERPConfiavel.nome,
        CPSistemaERPConfiavel.dominio, CPSistemaERPConfiavel.urlpublicaendpoint,
        CPSistemaERPConfiavel.urlrecepcaocodigo, CPSistemaERPConfiavel.hashchavepublica})
    INTEGRACAO_ERP_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: Informações]",
        CPSistemaERPConfiavel.nome, CPSistemaERPConfiavel.dominio,
        "[separador: Email acesso direto sem intermediação de senha do usuário]",
        "emailusuarioAdmin",
        CPSistemaERPConfiavel.urlpublicaendpoint, CPSistemaERPConfiavel.urlrecepcaocodigo,
        "[separador: Chave pública]",
        CPSistemaERPConfiavel.chavepublica})
    INTEGRACAO_ERP_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {
        "[separador: Informações]",
        CPSistemaERPConfiavel.nome, CPSistemaERPConfiavel.dominio,
        "[separador: Email acesso direto sem intermediação de senha do usuário]",
        "emailusuarioAdmin",
        CPSistemaERPConfiavel.urlpublicaendpoint, CPSistemaERPConfiavel.urlrecepcaocodigo,
        "[separador: Chave pública]",
        CPSistemaERPConfiavel.chavepublica})
    @InfoTipoAcaoFormCamposAtualizaGrupoDoCampo(campos = CPSistemaERPConfiavel.urlpublicaendpoint)
    INTEGRACAO_ERP_FRM_EDITAR,
    @InfoTipoAcaoFormulario(campos = {CPSistemaERPConfiavel.nome, CPSistemaERPConfiavel.chavepublica, CPSistemaERPConfiavel.dominio})
    INTEGRACAO_ERP_FRM_VISUALIZAR,
    @InfoTipoAcaoController()
    INTEGRACAO_ERP_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Mensagens de Marketing do Whatsapp", entidade = TipoMensagemMktWhatsApp.class)
    TIPO_MENSAGEM_MKT_WTZAP_MB_GESTAO,
    @InfoTipoAcaoFormulario(campos = {CPTipoMensagemMktWhatsApp.id, CPTipoMensagemMktWhatsApp.nome, CPTipoMensagemMktWhatsApp.slugtemplate, CPTipoMensagemMktWhatsApp.enviounico})
    TIPO_MENSAGEM_MKT_WTZAP_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: Tipo de mensagem]", CPTipoMensagemMktWhatsApp.nome, CPTipoMensagemMktWhatsApp.slugtemplate, "[separador: Detalhes ]", CPTipoMensagemMktWhatsApp.enviounico, CPTipoMensagemMktWhatsApp.urlmensagempreview})
    TIPO_MENSAGEM_MKT_WTZAP_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {"[separador: Tipo de mensagem]", CPTipoMensagemMktWhatsApp.nome, CPTipoMensagemMktWhatsApp.slugtemplate, "[separador: Detalhes ]", CPTipoMensagemMktWhatsApp.enviounico, CPTipoMensagemMktWhatsApp.urlmensagempreview})
    TIPO_MENSAGEM_MKT_WTZAP_FRM_EDITAR,
    @InfoTipoAcaoController()
    TIPO_MENSAGEM_MKT_WTZAP_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(entidade = ParametroMensagemWtzap.class, nomeAcao = "Parametro de mensagem Wtzap")
    PARAMETRO_MSG_WTZAP_MB_GESTAO,
    @InfoTipoAcaoFormulario(campos = {CPParametroMensagemWtzap.id, CPParametroMensagemWtzap.descricao, CPParametroMensagemWtzap.codigoparametro, CPParametroMensagemWtzap.cabecalho,
        CPParametroMensagemWtzap.tipodado, CPParametroMensagemWtzap.tipoparametrowtzap})
    PARAMETRO_MSG_WTZAP_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: Modelo Mensagem de Marketing]",
        CPParametroMensagemWtzap.tipomensagem,
        "[separador: Configurações básicas]",
        CPParametroMensagemWtzap.cabecalho, CPParametroMensagemWtzap.codigoparametro,
        "[separador: Origem]",
        CPParametroMensagemWtzap.tipodado, CPParametroMensagemWtzap.dadorelativo,
        "[separador: Avançado]", CPParametroMensagemWtzap.tipoparametrowtzap, CPParametroMensagemWtzap.descricao})
    PARAMETRO_MSG_WTZAP_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {"[separador: Modelo Mensagem de Marketing]",
        CPParametroMensagemWtzap.tipomensagem,
        "[separador: Configurações básicas]",
        CPParametroMensagemWtzap.cabecalho, CPParametroMensagemWtzap.codigoparametro,
        "[separador: Origem]",
        CPParametroMensagemWtzap.tipodado, CPParametroMensagemWtzap.dadorelativo,
        "[separador: Avançado]", CPParametroMensagemWtzap.tipoparametrowtzap, CPParametroMensagemWtzap.descricao})
    PARAMETRO_MSG_WTZAP_FRM_EDITAR,
    @InfoTipoAcaoFormulario()
    PARAMETRO_MSG_WTZAP_FRM_VISUALIZAR,
    @InfoTipoAcaoController()
    PARAMETRO_MSG_WTZAP_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-folder-o", entidade = CategoriaArquivoEquipe.class, precisaPermissao = true, nomeAcao = "Diretórios internos")
    CATEGORIA_DOCUMENTO_EQUIPE_MB_GESTAO,
    @InfoTipoAcaoFormulario(campos = {"id", "nome", "icone", CPCategoriaArquivoEquipe.compartilharcomconvidados})
    CATEGORIA_DOCUMENTO_EQUIPE_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: Diretorio pai:]", CPCategoriaArquivoCliente.pastapai,
        "[separador: Detalhes subdiretorio]",
        "nome", "icone", CPCategoriaArquivoEquipe.compartilharcomconvidados})
    CATEGORIA_DOCUMENTO_EQUIPE_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {"[separador: Diretorio pai:]", CPCategoriaArquivoCliente.pastapai,
        "[separador: Detalhes subdiretorio]",
        "nome", "icone"})
    CATEGORIA_DOCUMENTO_EQUIPE_FRM_EDITAR,
    @InfoTipoAcaoFormulario(campos = {"[separador: Diretorio pai:]", CPCategoriaArquivoCliente.pastapai,
        "[separador: Detalhes subdiretorio]",
        "nome", "icone"})
    CATEGORIA_DOCUMENTO_EQUIPE_FRM_NOVO_SUB_DIRETORIO,
    @InfoTipoAcaoFormulario(campos = {"id", "nome", "icone", CPCategoriaArquivoEquipe.compartilharcomconvidados})
    CATEGORIA_DOCUMENTO_EQUIPE_FRM_VISUALIZAR,
    @InfoTipoAcaoController()
    CATEGORIA_DOCUMENTO_EQUIPE_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-comments-o", entidade = TipoChatBot.class)
    FORM_CHAT_MB_GESTAO,
    @InfoTipoAcaoFormulario(campos = {CPTipoChatBot.id, CPTipoChatBot.nome, CPTipoChatBot.link})
    FORM_CHAT_FRM_LISTAR,
    @InfoTipoAcaoFormulario(campos = {CPTipoChatBot.nome, CPTipoChatBot.link, CPTipoChatBot.dadosextras})
    FORM_CHAT_FRM_NOVO,
    @InfoTipoAcaoFormulario(campos = {CPTipoChatBot.nome, CPTipoChatBot.link, CPTipoChatBot.dadosextras})
    FORM_CHAT_FRM_EDITAR,
    @InfoTipoAcaoController()
    FORM_CHAT_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-list", nomeAcao = "Formularios Typebot", entidade = TipoFormulario.class)
    TIPO_FORMULARIO_TYPEBOT_MB_GESTAO,
    @InfoTipoAcaoFormulario(campos = {CPTipoFormulario.id, CPTipoFormulario.codigotypebot, CPTipoFormulario.urlpublica, "integrarDados"})
    TIPO_FORMULARIO_TYPEBOT_FRM_LISTAR,
    @InfoTipoAcaoController(nomeAcao = "Processar", icone = "fa fa-cogs")
    TIPO_FORMULARIO_TYPEBOT_CTR_PROCESSAR,
    @InfoTipoAcaoController(nomeAcao = "Salvar", iconeFonteAnsowame = FabIconeFontAwesome.REG_SALVAR)
    TIPO_FORMULARIO_TYPEBOT_CTR_SALVAR_MERGE,
    @InfoTipoAcaoFormulario(campos = {CPTipoFormulario.reprocessarquandohouvererrodados, CPTipoFormulario.integrardados, CPTipoFormulario.notificaratendente})
    TIPO_FORMULARIO_TYPEBOT_FRM_EDITAR,
    @InfoTipoAcaoGestaoEntidade(entidade = RespostaFormulario.class)
    RESPOSTAS_FORMULARIO_TYPEBOT_MB_GESTAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Respostas formulario", campos = {CPRespostaFormulario.codigoresposta, CPRespostaFormulario.datahora, CPRespostaFormulario.pessoa, CPRespostaFormulario.jsonresposta})
    RESPOSTAS_FORMULARIO_TYPEBOT_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Respostas formulario", entidade = RespostaFormulario.class, campos = {CPRespostaFormulario.codigoresposta, CPRespostaFormulario.datahora, CPRespostaFormulario.pessoa, CPRespostaFormulario.jsonresposta})
    RESPOSTAS_FORMULARIO_TYPEBOT_FRM_VISUALIZAR

}
