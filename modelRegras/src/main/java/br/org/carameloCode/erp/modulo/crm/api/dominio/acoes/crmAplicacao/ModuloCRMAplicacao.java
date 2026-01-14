package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroRegraDeNEgocioChat;
import br.org.coletivoJava.fw.api.erp.chat.ItfErpChatService;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoUsuarioChat;
import br.org.coletivoJava.fw.erp.implementacao.chat.ChatMatrixOrgimpl;
import br.org.coletivoJava.integracoes.restTypebot.api.FabApiRestIntTypebotResultados;
import br.org.coletivoJava.integracoes.restTypebot.api.FabApiRestTypebotBots;
import br.org.coletivoJava.integracoes.restTypebot.api.FabApiRestTypebotWorkspace;
import br.org.coletivoJava.integracoes.restTypebot.implementacao.util.UtilIntegracaoTypebot;
import br.org.coletivojava.erp.comunicacao.transporte.ERPTipoCanalComunicacao;
import br.org.coletivojava.fw.utils.agendador.UtilSBAgendadorTarefas;
import com.google.common.collect.Lists;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.chat.UtilCRMChat;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.typebot.UtilCRMTypeBot;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.FabStatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chatBot.ChatBot;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.FabDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.formulario.TipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.formulario.resposta.RespostaFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.ProspectoRepositorio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.historicoRelacionamento.HistoricoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.MensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmEmail.ModuloCRMAtendimentoEmail;
import com.super_bits.Super_Bits.intTypebot.config.FabConfigModuloTypebot;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringVariaveisEntreCaracteres;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroAcessandoCanalComunicacao;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrm.CPTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoformulario.CPTipoFormulario;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.CPUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariosb.CPUsuarioSB;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 *
 * @author salviofurbino
 * @version 1.0
 * @since 10/05/2019
 */
public class ModuloCRMAplicacao extends ControllerAbstratoSBPersistencia {

    private static ChatMatrixOrgimpl chatService = (ChatMatrixOrgimpl) ERPChat.MATRIX_ORG.getImplementacaoDoContexto();

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_RECEBER_EMAILS_AUTO_EXEC)
    public synchronized static ItfRespostaAcaoDoSistema receberEmailsTodos() {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(EmailRecebido.class), null) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                List<GrupoUsuarioSB> grupos = Lists.newArrayList(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(),
                        FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro());

                List<UsuarioCRM> usuarios = new ConsultaDinamicaDeEntidade(UsuarioCRM.class)
                        .addCondicaoManyToOneContemNoIntervalo(CPUsuarioSB.grupo, grupos)
                        .addCondicaoManyToOneDiferenteDeNulo(CPUsuarioCRM.caixapostalprincipal)
                        .addCondicaoPositivo(CPUsuarioSB.ativo)
                        .resultadoRegistros();

                usuarios.forEach(usr -> {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ModuloCRMAplicacao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    new Thread("RecebendoEmails") {
                        @Override
                        public void run() {
                            if (usr.getCaixaPostalPrincipal().isAtivo()) {
                                ModuloCRMEmail.receberEmail(usr);
                            }
                        }

                    }.start();

                });
            }
        };

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_ALTERAR_TODOS_RELACIONAMENTOS_POR_INERCIA)
    public synchronized static ItfRespostaAcaoDoSistema relacionamentoPorInerciaTodos() {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(ProspectoRepositorio.class
        ), null) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ProspectoRepositorio.getUltimasAlteracoesProspectos().stream().forEach(up
                        -> {
                    alteracaoDeRelacionamentoPorInercia(up);
                });
            }
        };

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_ALTERAR_RELACIONAMENTO_POR_INERCIA)
    public synchronized static ItfRespostaAcaoDoSistema alteracaoDeRelacionamentoPorInercia(HistoricoRelacionamento pHistorico
    ) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pHistorico), pHistorico) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                HistoricoRelacionamento historicoAtualizado = loadEntidade(pHistorico);
                {
                    if (historicoAtualizado.getNovoRelacionamento() != null) {
                        int horasAceitaveis = historicoAtualizado.getNovoRelacionamento().getTempoAcaoInerciaRelacionamento() + historicoAtualizado.getNovoRelacionamento().getTempoAceitavelResolucao();
                        if (horasAceitaveis > 0) {
                            Date dataAceitavel = UtilCRCDataHora.incrementaHoras(historicoAtualizado.getDataHora(), horasAceitaveis);

                            TipoRelacionamento relacionamentoGerado;
                            if (historicoAtualizado.getAtividadeReferencia().getRelacionamentoGerado() != null) {
                                relacionamentoGerado = historicoAtualizado.getAtividadeReferencia().getRelacionamentoGerado();
                            } else {
                                relacionamentoGerado = historicoAtualizado.getNovoRelacionamento();
                            }

                            if (relacionamentoGerado != null && relacionamentoGerado.getRelacionamentoPeranteInercia() != null) {

                                if (dataAceitavel.getTime() >= new Date().getTime()) {

                                    Pessoa prosp = loadEntidade(historicoAtualizado.getAtividadeReferencia().getProspectoEmpresa());
                                    try {
                                        prosp.alterarRelacionamento(historicoAtualizado.getAtividadeReferencia().getRelacionamentoGerado().getRelacionamentoPeranteInercia());
                                    } catch (ErroValidacao ex) {
                                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro alterando status automátcamente " + ex.getMensagemAoUsuario(), ex);
                                    }
                                    if (atualizarEntidade(prosp) == null) {
                                        throw new ErroRegraDeNegocio("Erro alterando relacionamento devido a inercia");
                                    }
                                } else {
                                    UtilSBAgendadorTarefas.agendarTarefa(FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_ALTERAR_RELACIONAMENTO_POR_INERCIA,
                                            dataAceitavel, historicoAtualizado);
                                }
                            }

                        }
                    }
                }
            }
        }.getResposta();

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_ENVIAR_EMAIL_PROGRAMADO)
    public synchronized static ItfRespostaAcaoDoSistema enviarEmailProgramado(EnvioEmail pEmail
    ) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(EnvioEmail.class
        ), pEmail) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pEmail.getDataHoraAgendamentoDisparo().getTime() <= new Date().getTime() + 1000) {
                    if (!ModuloCRMAtendimentoEmail.email_enviar(pEmail).isSucesso()) {

                        String mensagem = "Houve uma falha enviando um e-mail agendado, o envio foi reagendado para "
                                + UtilCRCDataHora.getDataSTRFormatoUsuario(pEmail.getDataHoraInsercao());
                        ItfDialogo dialogo = SBCore.getServicoComunicacao().gerarComunicacaoSistema_Usuario(FabTipoComunicacao.NOTIFICAR,
                                (ComoUsuario) pEmail.getUsuarioCriou(), mensagem
                        );

                        try {
                            SBCore.getServicoComunicacao().dispararComunicacao(dialogo, ERPTipoCanalComunicacao.INTRANET_MENU);
                        } catch (ErroAcessandoCanalComunicacao ex) {

                        }
                        UtilSBAgendadorTarefas.agendarTarefa(FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_ENVIAR_EMAIL_PROGRAMADO,
                                UtilCRCDataHora.incrementaHoras(pEmail.getDataHoraAgendamentoDisparo(), 1), pEmail);
                    }
                }

            }

        };

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_INICIALIZAR_TAREFAS_AGENDADAS)
    public synchronized static ItfRespostaAcaoDoSistema inicializarTarefasAgendadas() {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(UsuarioSistemaRoot.class
        ), null) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                boolean processook = true;
                try {
                    //agendamento envio de e-mail
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.PARA_TUDO, "Falha inicializando agendamento de e-mails", t);
                    processook = false;
                }
                try {
                    //agendamento inercia de relacionamento
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.PARA_TUDO, "Falha inicializando agendamento de e-mails", t);
                    processook = false;
                }
                if (!processook) {
                    throw new ErroRegraDeNegocio("Houveram falhas ao agendar tarefas agendadas, verifique o log");
                }
            }

        };

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_ALTERAR_RELACIONAMENTO)
    public static ItfRespostaAcaoDoSistema atividadeAlterarRElacionamentoPessoa(AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(AtividadeCRM.class), pAtividade) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.
                if (isSucesso()) {
                    atividadeAlteracaoRelacionamentoAcoesAutonomas(pAtividade);
                }
            }

            @Override
            public void regraDeNegocio() {
                AtividadeCRM atividadeAtualizada = loadEntidade(pAtividade);
                TipoRelacionamento novoRelacionamento = (TipoRelacionamento) atividadeAtualizada.getCampoInstanciadoByNomeOuAnotacao(CPAtividadeCRM.relacionamentogerado).getValor();
                novoRelacionamento = loadEntidade(novoRelacionamento);

                if (novoRelacionamento != null) {

                    final Pessoa prospectoLoaded = atividadeAtualizada.getProspectoEmpresa();
                    if (atividadeAtualizada.getRelacionamentoGerado().getPeso() > prospectoLoaded.getRelacionamento().getPeso()) {
                        //        addAviso("Parabéns, " + prospectoLoaded.getNome() + " possui um novo relacionamento conosco: " + atividadeAtualizada.getRelacionamentoGerado().getNome());
                    }
                    if (atividadeAtualizada.getRelacionamentoGerado().getPeso() < prospectoLoaded.getRelacionamento().getPeso()) {
                        //       addAviso("Poxa vida, o novo relacionamento de " + prospectoLoaded.getNome() + " foi redefinido para" + atividadeAtualizada.getRelacionamentoGerado().getNome());
                    }
                    if (atividadeAtualizada.getRelacionamentoGerado().getPeso() == prospectoLoaded.getRelacionamento().getPeso()) {
                        //       addAviso("O novo relacionamento de " + prospectoLoaded.getNome() + " foi redefinido para" + atividadeAtualizada.getRelacionamentoGerado().getNome());

                    }
                    try {
                        prospectoLoaded.getCPinst("relacionamento").setValorSeValido(novoRelacionamento);
                        prospectoLoaded.getHistoricoRelacionamento().get(prospectoLoaded.getHistoricoRelacionamento().size() - 1).setAtividadeReferencia(atividadeAtualizada);
                    } catch (ErroValidacao ex) {
                        addErro("Erro alterando relacionamento: " + ex.getMensagemAoUsuario());
                    }
                    Pessoa prospectoAtualizado = UtilSBPersistencia.mergeRegistro(prospectoLoaded, getEMResposta());

                    setRetorno(prospectoAtualizado);

                }
            }

        }.getResposta();

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_ALTERACAO_RELACIONAMENTO_ACOES_AUTOMATICAS)
    public static ItfRespostaAcaoDoSistema atividadeAlteracaoRelacionamentoAcoesAutonomas(AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                AtividadeCRM atividadeAtualizada = loadEntidade(pAtividade);
                TipoRelacionamento novoRelacionamento = (TipoRelacionamento) atividadeAtualizada.getCampoInstanciadoByNomeOuAnotacao(CPAtividadeCRM.relacionamentogerado).getValor();
                novoRelacionamento = loadEntidade(novoRelacionamento);
                final Pessoa prospectoLoaded = atividadeAtualizada.getProspectoEmpresa();
                if (!novoRelacionamento.getUsuariosResponsaveis().isEmpty()) {

                    if (novoRelacionamento.isResponsabilidadeexclusiva()) {
                        prospectoLoaded.getUsuariosResponsaveis().clear();
                    }

                    for (UsuarioCRM usuario : novoRelacionamento.getUsuariosResponsaveis()) {
                        if (!prospectoLoaded.getUsuariosResponsaveis().contains(usuario)) {
                            prospectoLoaded.getUsuariosResponsaveis().add(usuario);
                        }
                    }
                }
                setRetorno(atualizarEntidade(prospectoLoaded, false));
            }

        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CRIAR_CHAT_BOT)
    public static ItfRespostaAcaoDoSistema atividadeCriarChatBot(AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                AtividadeCRM atividade = loadEntidade(pAtividade);

                if (atividade.getId() != null && atividade.getId() > 0) {
                    if (atividade.getChatBot() == null) {
                        if (atividade.getTipoAtividade().getTipoChatBot() != null) {

                            ChatBot chatBot = new ChatBot();
                            try {
                                chatBot.prepararNovoObjeto(atividade);
                            } catch (ErroPreparandoObjeto ex) {
                                throw new ErroRegraDeNegocio("Falha criando chat");
                            }
                            atividade.setChatBot(atualizarEntidade(chatBot));
                            atualizarEntidade(atividade);

                        }
                    }
                }
            }

        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_ENVIARWHATSAPP)
    public static ItfRespostaAcaoDoSistema atividadeEnviarMensagemWhatsapp(AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                AtividadeCRM atividade = loadEntidade(pAtividade);
                List<ContatoProspecto> contatos = new ArrayList<>();
                MensagemMktWhatsapp novaMensagem = new MensagemMktWhatsapp();
                ContatoProspecto contatoPrincipal = atividade.getProspectoEmpresa().getContatoPrincipal();
                if (contatoPrincipal.isPossuiTelefone()) {
                    String telefone = UtilCRCStringTelefone.gerarCeluarWhatasapp(contatoPrincipal.getCelular());
                    if (telefone == null) {
                        throw new ErroRegraDeNegocio("Telefone não ");
                    }
                    try {
                        novaMensagem.prepararNovoObjeto(contatoPrincipal);
                        novaMensagem.setTipo(atividade.getTipoAtividade().getTipoMensagemWtzap());
                        novaMensagem.setAtividade(atividade);

                        ItfRespostaAcaoDoSistema envioMEnsagem = ModuloCRMAtendimento.contatoProspectoEnviarWhatzapMtk(novaMensagem).dispararMensagens();

                        if (!envioMEnsagem.isSucesso()) {
                            throw new ErroRegraDeNegocio("Falha envian do mensagem");
                        }
                        novaMensagem = (MensagemMktWhatsapp) envioMEnsagem.getRetorno();
                        setRetorno(novaMensagem);
                    } catch (ErroPreparandoObjeto ex) {
                        Logger.getLogger(ModuloCRMAplicacao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        }.getResposta();

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_ACOES_AUTOMATICAS)
    public static ItfRespostaAcaoDoSistema atividadeAcoesAtomaticasPosConclusao(AtividadeCRM pAtividadeOriginal) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(AtividadeCRM.class), pAtividadeOriginal) {

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                AtividadeCRM atividadeAtualizada = loadEntidade(pAtividadeOriginal);
                atividadeAtualizada.setDadosRevisados(true);

                if (!atividadeAtualizada.getStatusAtividade().equals(FabStatusAtividade.CONCLUIDA_COM_SUCESSSO.getRegistro())) {
                    throw new ErroRegraDeNegocio("a Atividade não foi concluída");
                }

                if (atividadeAtualizada.getTipoAtividade().getAtividadeAgendada() != null) {
                    ItfRespostaAcaoDoSistema resposta = atividadeCOnclusaoAgendarNova(atividadeAtualizada);
                    if (!resposta.isSucesso()) {
                        resposta.dispararMensagens();
                    }
                }
                if (atividadeAtualizada.getTipoAtividade().getEmailTransacionalMkt() != null) {
                    ItfRespostaAcaoDoSistema respostaEmailtransacional = atividadeConcluisaoEmailMktTransacional(atividadeAtualizada);
                    if (!respostaEmailtransacional.isSucesso()) {
                        respostaEmailtransacional.dispararMensagens();
                    }
                }
                if (!atividadeAtualizada.getTipoAtividade().getTagsAtendimentoAdicionadas().isEmpty()
                        || !atividadeAtualizada.getTipoAtividade().getTagsAtendimentoRemovidas().isEmpty()) {

                    ItfRespostaAcaoDoSistema respostaAlterarTags = atividadeConclusaoAcaoAtomaticaAlterarTags(atividadeAtualizada);
                    if (!respostaAlterarTags.isSucesso()) {
                        respostaAlterarTags.dispararMensagens();
                    }
                }

                if (atividadeAtualizada.getTipoAtividade().getTipoChatBot() != null) {
                    atividadeCriarChatBot(atividadeAtualizada);
                }

                if (atividadeAtualizada.getTipoAtividade().getTipoMensagemWtzap() != null) {
                    ItfRespostaAcaoDoSistema respostaenvioMensagemWtzap = ModuloCRMAplicacao.atividadeEnviarMensagemWhatsapp(atividadeAtualizada);
                    if (!respostaenvioMensagemWtzap.isSucesso()) {
                        respostaenvioMensagemWtzap.dispararMensagens();
                    }
                }

            }
        }.getResposta();

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_AGENDAR_NOVA_ATIVIDADE)
    public static ItfRespostaAcaoDoSistema atividadeCOnclusaoAgendarNova(AtividadeCRM pAtividade) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                AtividadeCRM atividadeAtualizada = UtilSBPersistencia.loadEntidade(pAtividade, getEMResposta());
                TipoAtividadeCRM tipoAtividade = atividadeAtualizada.getTipoAtividade();
                TipoAtividadeCRM novoTipoAtividade = tipoAtividade.getAtividadeAgendada();
                AtividadeCRM novaAtividade = new AtividadeCRM();
                novaAtividade.prepararNovoObjeto(novoTipoAtividade, atividadeAtualizada.getProspectoEmpresa());
                novaAtividade.setStatusAtividade(FabStatusAtividade.AGENDADO.getRegistro());
                int diasAgendamento = tipoAtividade.getDiasAgendarNovaAtividade();
                if (diasAgendamento == 0) {
                    diasAgendamento = 1;
                }
                novaAtividade.setDataHoraPrevisaoExecucao(UtilCRCDataHora.incrementaDias(atividadeAtualizada.getDataHoraRealizacao(), diasAgendamento));
                novaAtividade.setAnotacoes(pAtividade.getAnotacoes());
                novaAtividade.setAnotacoes("Atividade Agendada dia " + UtilCRCDataHora.getDataSTRFormatoUsuario(new Date())
                        + " devido a conclusão de  " + atividadeAtualizada.getTipoAtividade().getNomeAtividade());

                setRetorno(atualizarEntidade(novaAtividade, false));

                addAlerta("Nova Atividade : " + novaAtividade.getTipoAtividade().getNome() + ", agendada para " + UtilCRCDataHora.converteDataEmStringFormatada(novaAtividade.getDataHoraPrevisaoExecucao()));

            }

        }.getResposta();
    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_ALTERAR_TAGS)
    public static ItfRespostaAcaoDoSistema atividadeConclusaoAcaoAtomaticaAlterarTags(AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                AtividadeCRM atividadeAtualizada = UtilSBPersistencia.loadEntidade(pAtividade, getEMResposta());
                final Pessoa prospectoLoaded = atividadeAtualizada.getProspectoEmpresa();
                atividadeAtualizada.getTipoAtividade().getTagsAtendimentoAdicionadas().forEach(
                        tag -> {
                            if (!prospectoLoaded.getTagsAtendimento().contains(tag)) {
                                prospectoLoaded.getTagsAtendimento().add(tag);
                            }
                        });
                List<TagAtendimento> tagsRemover = new ArrayList<>();
                atividadeAtualizada.getTipoAtividade().getTagsAtendimentoRemovidas().stream()
                        .filter(tag -> prospectoLoaded.getTagsAtendimento().contains(tag)).forEach(tagsRemover::add);
                tagsRemover.forEach(tag -> prospectoLoaded.getTagsAtendimento().remove(tag));
                setRetorno(atualizarEntidade(prospectoLoaded, false));
            }

        }.getResposta();

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ATIVIDADE_AUTONOMA_CRM_CTR_CONCLUSAO_DISPARAR_TRANSACIONAL)
    public static ItfRespostaAcaoDoSistema atividadeConcluisaoEmailMktTransacional(AtividadeCRM pAtividade) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pAtividade), pAtividade) {
            @Override
            public void regraDeNegocio() {

                AtividadeCRM atividade = loadEntidade(pAtividade);
                if (atividade.getTipoAtividade().getEmailTransacionalMkt() != null) {
                    //TODO api mautico envio email mkt
                }
            }

        }.getResposta();

    }

    /**
     *
     * listar os formularios dos ultimos 7 dias. /// aqueles formularios que não
     * tenham sido registrados, serão registrados. /// aqueles formulários que
     * não tenham sido processados com sucesso, serão re-processados.
     * <p>
     * //Talvez adicionar um limite maior de results (padrao 50)
     *
     * @return
     */
    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_TYPEBOT_FORM_SINCRONIZAR_AUTO_EXEC)
    public static ItfRespostaAcaoDoSistema formularioSincronizar() {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(TipoFormulario.class), new TipoFormulario()) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                List<TipoFormulario> formulariosBanco = UtilSBPersistencia.getListaTodos(TipoFormulario.class, getEMResposta());
                System.out.println("Lista de formulario " + formulariosBanco.toString());
                List<String> idsExistentes = formulariosBanco.stream()
                        .map(tf -> tf.getCodigoTypebot())
                        .collect(Collectors.toList());

                ItfRespostaWebServiceSimples resposta = FabApiRestTypebotWorkspace.LISTAR_TODOS_WORKSPACES.getAcao().getResposta();
                JsonArray workspaces = resposta.getRespostaComoObjetoJson().getJsonArray("workspaces");
                if (!resposta.isSucesso()) {
                    throw new ErroRegraDeNegocio("Falha conectando com typebot, cheque as configurações e o serviço");
                }
                final String prWorkspace = FabConfigModuloTypebot.WORKSPACE.getValorParametroSistema();
                String workspaceId = workspaces.stream()
                        .map(JsonValue::asJsonObject)
                        .filter(obj -> (obj.getString("name")).contains(prWorkspace))
                        .map(obj -> obj.getString("id"))
                        .findFirst()
                        .orElse(null);
                if (workspaceId == null) {
                    throw new ErroRegraDeNegocio("Workspace [" + prWorkspace + "] não encontrada");
                }

                JsonArray typebots = FabApiRestTypebotBots.LISTAR_TODOS_TYPEBOTS.getAcao(workspaceId).getResposta().getRespostaComoObjetoJson().getJsonArray("typebots");

                final List<String> typebotsDoWorkspace = new ArrayList<>();

                typebots.stream()
                        .map(jv -> jv.asJsonObject().getString("id"))
                        .forEach(typebotsDoWorkspace::add);

                for (String typebotId : typebotsDoWorkspace) {
                    TipoFormulario tipoFormulario = null;
                    if (!idsExistentes.contains(typebotId)) {
                        JsonObject typebotInfo = FabApiRestTypebotBots.OBTER_TYPEBOT_BY_ID.getAcao(typebotId).getResposta().getRespostaComoObjetoJson().getJsonObject("typebot");
                        if (!typebotInfo.isNull("publicId")) {
                            tipoFormulario = new TipoFormulario();
                            tipoFormulario.setCodigoTypebot(typebotId);
                            tipoFormulario.setIntegrarDados(UtilIntegracaoTypebot.isIntegrarDadosTrue(typebotInfo));
                            tipoFormulario.setUrlPublica("https://iachat.casanovadigital.com.br/" + typebotInfo.getString("publicId"));
                            UtilSBPersistencia.mergeRegistro(tipoFormulario, getEMResposta());
                        } else {
                            System.out.println("ignorando tipo de formulário não publicado");
                        }
                    } else {
                        ConsultaDinamicaDeEntidade conslta = new ConsultaDinamicaDeEntidade(TipoFormulario.class, getEMResposta());
                        conslta.addcondicaoCampoIgualA(CPTipoFormulario.codigotypebot, typebotId);
                        tipoFormulario = conslta.getPrimeiroRegistro();

                        // Atualizar url publica?
                    }

                }

            }
        }.getResposta();

    }

    @InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_FORMULARIO_TYPEBOT_CTR_PROCESSAR)
    public synchronized static ItfRespostaAcaoDoSistema FormularioTypebotProcessar(TipoFormulario pTipo) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(TipoFormulario.class), pTipo) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                TipoFormulario tipoFormulario = loadEntidade(pTipo);
                List<String> respostasJaProcessadas = UtilCRCStringVariaveisEntreCaracteres.extrairVariaveisEntreColchete(tipoFormulario.getUltimas50RespCodigos());
                JsonObject respostasws = FabApiRestIntTypebotResultados.LISTAR_7_DIAS.getAcao(tipoFormulario.getCodigoTypebot(), 100)
                        .getResposta().getRespostaComoObjetoJson();
                JsonArray respostas = respostasws.getJsonArray("results");

                for (JsonValue respostaJV : respostas) {
                    RespostaFormulario respostaForm = null;
                    Map<String, String> dadosDinamicosParaAtualizar = null;
                    try {

                        if (respostasJaProcessadas.contains(respostaJV.asJsonObject().getString("id"))) {

                            continue;
                        }
                        respostaForm = UtilCRMTypeBot.gerarRespostaByJson(respostaJV.asJsonObject(), tipoFormulario, true);

                        if (respostaForm.getPessoa() == null) {
                            continue;
                        }
                        boolean dadosprocessadosSucesso = true;
                        dadosDinamicosParaAtualizar = UtilCRMTypeBot.parseVariablesWithJakarta(respostaJV.asJsonObject().getJsonArray("variables"));
                        for (String nomeDadoDinamico : dadosDinamicosParaAtualizar.keySet()) {
                            if (nomeDadoDinamico.toLowerCase().contains("dados.")) {
                                try {
                                    String nomeTipoDado = nomeDadoDinamico.substring(6);
                                    TipoDadoCRM tipoDado = (TipoDadoCRM) new ConsultaDinamicaDeEntidade(TipoDadoCRM.class, getEMResposta())
                                            .addcondicaoCampoIgualA(CPTipoDadoCRM.nome, nomeTipoDado)
                                            .getPrimeiroRegistro();

                                    if (tipoDado != null) {
                                        DadoCRM dadoAtualizado = FabDadoCRM.getDadoNovoSeNaoExistir(respostaForm.getPessoa(), tipoDado);
                                        dadoAtualizado.setValorEmpacotado(dadosDinamicosParaAtualizar.get(nomeDadoDinamico));
                                        dadoAtualizado = UtilSBPersistencia.mergeRegistro(dadoAtualizado);
                                        if (dadoAtualizado == null) {
                                            throw new UnsupportedOperationException("Falha atualizando dados");
                                        }
                                    }
                                } catch (Throwable t) {
                                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha processando " + nomeDadoDinamico, t);
                                    dadosprocessadosSucesso = false;
                                }

                            }
                        }
                        respostaForm.setDadosProcessadosSucesso(dadosprocessadosSucesso);

                        if (respostaForm.getUsuarioResponsavelAtendimento() != null) {
                            if (tipoFormulario.isNotificarAtendente()) {
                                try {
                                    ComoUsuarioChat usuarioAtdChat = UtilCRMChat.gerarUsuarioAtendimento(respostaForm.getPessoa().getUsuarioResponsavel());
                                    if (usuarioAtdChat != null) {
                                        String linkLead = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO, respostaForm.getPessoa());
                                        String textoNotificacao = "Chegou um novo LEAD:" + respostaForm.getPessoa().getNome() + " acesse os detalhes via: " + linkLead;
                                        ItfErpChatService servicoChat = CarameloCode.getServicoERP(ERPChat.MATRIX_ORG);
                                        servicoChat.enviarDirect(usuarioAtdChat.getCodigoUsuario(), textoNotificacao);
                                    }
                                } catch (Throwable t) {
                                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando direct para o vendedor", t);
                                }
                            }
                        }
                        UtilSBPersistencia.mergeRegistro(respostaForm);
                    } catch (ErroValidacao
                            | ErroRegraDeNegocio ex) {
                        if (respostaForm != null && tipoFormulario.isNotificarAtendente() && dadosDinamicosParaAtualizar != null) {
                            if (!respostaForm.isAtendenteNotificado()) {
                                UsuarioCRM usuarioAtend = UtilCRMTypeBot.getUsuarioAtendimento(dadosDinamicosParaAtualizar);
                                ComoUsuarioChat usuarioAtendChat = null;
                                if (usuarioAtend == null) {
                                    continue;
                                }
                                try {
                                    usuarioAtendChat = UtilCRMChat.gerarUsuarioAtendimento(usuarioAtend);
                                } catch (ErroConexaoServicoChat | ErroRegraDeNEgocioChat ex1) {
                                    continue;
                                }
                                if (usuarioAtendChat != null) {
                                    ItfErpChatService servicoChat = CarameloCode.getServicoERP(ERPChat.MATRIX_ORG);
                                    String resultado = dadosDinamicosParaAtualizar.entrySet()
                                            .stream()
                                            .map(e -> e.getKey() + " -> " + e.getValue())
                                            .collect(Collectors.joining(", "));
                                    try {
                                        if (!(servicoChat.enviarDirect(usuarioAtendChat.getCodigoUsuario(),
                                                "Falha registrando resposta de formulário: " + ex.getMessage() + " | com os dados: " + resultado) != null)) {
                                            respostaForm.setAtendenteNotificado(true);
                                            UtilSBPersistencia.mergeRegistro(respostaForm);
                                        }
                                    } catch (ErroConexaoServicoChat ex1) {
                                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha enviando notifcação para o ", ex);
                                    }
                                }
                            }

                        }
                        if (respostaJV.asJsonObject().containsKey("id") && respostaJV.asJsonObject().containsKey("formId")) {
                            // Salvar Resposta, marcando flag para não ser mais processada?
                        }

                        continue;
                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha processando resposta" + UtilCRCJson.getTextoByJsonObjeect(respostasws), t);
                        continue;
                    }

                }
            }
        }.getResposta();

    }

    @InfoAcaoCRMAplicacao(acao = FabAcaoCrmAplicacao.ACOES_PROGRAMADAS_CTR_TYPEBOT_RESPOSTA_SINCRONIZAR_AUTO_EXEC)
    public static ItfRespostaAcaoDoSistema respostasSincronizar() {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(RespostaFormulario.class
        ), new RespostaFormulario()) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                List<TipoFormulario> tipoFormularios = UtilSBPersistencia.getListaTodos(TipoFormulario.class, getEMResposta());

                for (TipoFormulario tipoFormulario : tipoFormularios) {
                    if (tipoFormulario.isIntegrarDados()) {

                        FormularioTypebotProcessar(tipoFormulario);

                        //        ConsultaDinamicaDeEntidade conulta = new ConsultaDinamicaDeEntidade(RespostaFormulario.class, getEm())
                        //              .addcondicaoCampoIgualA("codigoResposta",);
                    }
                }

            }
        }
                .getResposta();
    }
}
