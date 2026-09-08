/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroRegraDeNEgocioChat;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoUsuarioChat;
import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.api.email.ErroEnvioEmail;
import br.org.coletivoJava.integracoes.amazonSMS.FabIntegracaoSMS;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.chat.UtilCRMChat;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.EventoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.NotificacaoResponsaveisChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.StatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.cliente.satisfacao.FabSatisfacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.cliente.satisfacao.SatisfacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.porteEmpresa.Porte;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.TipoEmpresa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.FabTipoNotificacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.regras_de_negocio_e_controller.intranetMarketingDigital.controller.ServicoNotificacao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.FabAcaoAcessoAnonimoIntranet;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.InfoAcaoaAcessoAnonimoCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListasObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.CPPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.CPUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda.MapaHorariosDisponiveis;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.FabStatusReservaHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.StatusReserva;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import br.org.carameloCode.erp.modulo.notificacao.controller.ModuloNotificacao;

/**
 *
 * @author sfurbino
 */
public abstract class ModuloCRMCliente extends ControllerAbstratoSBPersistencia {

    @InfoAcaoaAcessoAnonimoCRM(acao = FabAcaoAcessoAnonimoIntranet.LOGIN_CTR_CLIENTE_CADASTRAR_SENHA_PRIMEIRO_ACESSO)
    public static ItfRespostaAcaoDoSistema cadastrarNovaSenhaUsuario(UsuarioCrmCliente pUsuario) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(UsuarioCrmCliente.class), pUsuario) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                UsuarioCrmCliente usuario = null;
                if (pUsuario.getId() != null && pUsuario.getId() > 0) {
                    usuario = loadEntidade(pUsuario);
                    usuario.setEmail(pUsuario.getEmail());
                    usuario.setTelefone(pUsuario.getTelefone());
                    usuario.setNome(pUsuario.getNome());
                    usuario.setSenha(pUsuario.getSenha());
                } else {
                    usuario = pUsuario;
                }
                if (UtilCRCStringValidador.isNuloOuEmbranco(pUsuario.getSenha())) {
                    throw new ErroRegraDeNegocio("A senha não pode ser nula");
                }
                if (usuario.getSenha().length() < 4) {
                    throw new ErroRegraDeNegocio("A senha precisa ter 4 caracteres");
                }
                if (usuario.getRepresentanteLegal() == null) {
                    throw new ErroRegraDeNegocio("O representante legal não foi cadastrado");
                }
                String chaveSecretaMAtrix = (String) usuario.getCampoInstanciadoByNomeOuAnotacao(CPUsuarioCrmCliente.chavesecretarc).getValor();

                UsuarioCrmCliente usuarioAtualizado = atualizarEntidade(usuario, true);
                usuario.getContatoClienteVinculado().setUsuarioVinculado(usuarioAtualizado);
                atualizarEntidade(usuario.getContatoClienteVinculado(), true);
            }
        }.getResposta();
    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.CHAMADO_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema chamadoSalvarMerge(ChamadoCliente pChamado) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(ChamadoCliente.class), pChamado) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pChamado.getStatus() == null) {
                    throw new ErroRegraDeNegocio("O status do chamado não pode ser nulo");
                }

                if (!pChamado.getStatus().equals(FabStatusChamado.RASCUNHO.getRegistro())) {
                    throw new ErroRegraDeNegocio("O status do chamado não permite alteração");
                }
                if (pChamado.getPessoa() != null) {
                    pChamado.setPessoa(loadEntidade(pChamado.getPessoa()));
                }
                pChamado.setDataHoraCriacao(new Date());
                atualizarEntidade(pChamado);
                setProximoFormulario(FabAcaoCRMCliente.CHAMADO_FRM_NOVO.getRegistro().getComoFormulario());
                setRetorno(atualizarEntidade(pChamado));

            }
        }.getResposta();
    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.CHAMADO_CTR_ABRIR_CHAMADO)
    public static ItfRespostaAcaoDoSistema chamadoAbrirChamado(ChamadoCliente pChamado) {
        ChamadoCliente chamadoAtualizado;
        ItfRespostaAcaoDoSistema resposataChamado = chamadoSalvarMerge(pChamado);
        if (resposataChamado.isSucesso()) {
            chamadoAtualizado = (ChamadoCliente) resposataChamado.getRetorno();
        } else {
            return resposataChamado;
        }

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(UsuarioCrmCliente.class), chamadoAtualizado) {
            private ComoChatSalaBean sala;

            @Override
            public void executarAcoesIniciais() throws ErroEmBancoDeDados {
                super.executarAcoesIniciais();

            }

            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

                if (isSucesso()) {
                    try {
                        ComoChatSalaBean sala = UtilCRMChat.gerarSalaChamado(chamadoAtualizado);

                    } catch (ErroConexaoServicoChat | ErroRegraDeNEgocioChat ex) {
                        addErro("Falha criando integração do chamado com Matrix");
                        EntityManager emCriacaoSala = UtilSBPersistencia.getEntyManagerPadraoNovo();
                        try {
                            ChamadoCliente chamadoReversaoFalhaMatrix = UtilSBPersistencia.loadEntidade(pChamado, emCriacaoSala);
                            chamadoReversaoFalhaMatrix.setStatus(FabStatusChamado.RASCUNHO.getRegistro());

                            UtilSBPersistencia.mergeRegistro(chamadoReversaoFalhaMatrix, emCriacaoSala);
                        } finally {
                            UtilSBPersistencia.fecharEM(emCriacaoSala);
                        }
                    }

                }

                if (!isSucesso()) {
                    if (sala != null) {
                        try {
                            UtilCRMChat.chatService.salaExcluir(sala);
                        } catch (ErroConexaoServicoChat ex) {
                            try {
                                for (ComoUsuarioChat usuario : sala.getUsuarios()) {
                                    try {
                                        UtilCRMChat.chatService.salaRemoverMembro(sala, usuario.getCodigoUsuario());
                                    } catch (ErroConexaoServicoChat ex1) {
                                        Logger.getLogger(ModuloCRMCliente.class.getName()).log(Level.SEVERE, null, ex1);
                                    }
                                }
                                UtilCRMChat.chatService.salaExcluir(sala);
                            } catch (ErroConexaoServicoChat ex1) {
                                Logger.getLogger(ModuloCRMCliente.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                        }
                    }
                } else {
                    ServicoNotificacao.notificarChamadoCliente(FabTipoNotificacao.NOTIFICACAO_CLIENTE_PROTOCOLO_DE_CHAMADO, (ChamadoCliente) getRetorno());
                    ServicoNotificacao.notificacarChamadoResponsaveis(FabTipoNotificacao.NOTIFICACAO_RESPONSAVEIS_CHAMADO_ABERTO, (ChamadoCliente) getRetorno());
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                loadEntidade(chamadoAtualizado);
                if (chamadoAtualizado.getStatus().equals(FabStatusChamado.RASCUNHO.getRegistro())
                        || chamadoAtualizado.getStatus().equals(FabStatusChamado.FINALIZADO.getRegistro())) {

                } else {
                    throw new ErroRegraDeNegocio("O status do chamado não permite reabertura");
                }
                if (chamadoAtualizado.getDescricao().length() < 30) {
                    throw new ErroRegraDeNegocio("Fale mais sobre sua dor ou desejo");
                }
                if (chamadoAtualizado.getPessoa() == null) {
                    UsuarioCrmCliente usuario = (UsuarioCrmCliente) SBCore.getUsuarioLogado();
                    chamadoAtualizado.setPessoa(usuario.getRepresentanteLegal());

                }

                if (chamadoAtualizado.getPessoa() == null) {
                    throw new ErroRegraDeNegocio("Pessoa associada ao contato não encontrada para abertura do chamado");
                }
                chamadoAtualizado.setStatus(FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro());
                pChamado.setDataHoraCriacao(new Date());
                if (chamadoAtualizado.getTipoChamado() == null) {
                    throw new ErroRegraDeNegocio("O tipo de chamado é obrigatorio");
                }

                setRetorno(atualizarEntidade(chamadoAtualizado, true));

                setProximoFormulario(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO.getRegistro().getComoFormulario());
            }
        }.getResposta();
    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.CHAMADO_CTR_FINALIZAR)
    public static ItfRespostaAcaoDoSistema chamadoFinalizar(ChamadoCliente pChamado) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(UsuarioCrmCliente.class), pChamado) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    EntityManager em = null;
                    try {
                        em = UtilSBPersistencia.getEMPadraoNovo();
                        ChamadoCliente chamadoNotificacao = UtilSBPersistencia.loadEntidade(pChamado, em);
                        String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER, chamadoNotificacao);
                        try {
                            if (chamadoNotificacao.getAtendenteResponsavel() != null) {
                                ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(chamadoNotificacao.getAtendenteResponsavel().getCodigoMatrix(),
                                        chamadoNotificacao.getUsuarioCliente().getNome() + "Fechou o chamado Cod.:" + chamadoNotificacao.getId() + " de " + chamadoNotificacao.getPessoa().getNome() + " do tipo " + chamadoNotificacao.getTipoChamado().getNome()
                                        + " para ver detalhes clique no link: " + url);
                            }
                        } catch (ErroConexaoServicoChat ex) {
                            Logger.getLogger(ModuloCRMCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } finally {
                        UtilSBPersistencia.fecharEM(em);
                    }
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ChamadoCliente chamado = loadEntidade(pChamado);
                if (pChamado.getStatus().equals(FabStatusChamado.RASCUNHO.getRegistro())
                        || pChamado.getStatus().equals(FabStatusChamado.FINALIZADO.getRegistro())) {
                    throw new ErroRegraDeNegocio("O status do chamado não permite alteração");
                }
                chamado.setStatus(FabStatusChamado.FINALIZADO.getRegistro());
                atualizarEntidade(chamado);
                EventoChamado novoEvento = new EventoChamado();
                novoEvento.setChamado(chamado);
                novoEvento.setAgente((UsuarioCRM) SBCore.getUsuarioLogado());
                novoEvento.setDescricao("Chamado Finalizado");
                atualizarEntidade(novoEvento);
                setProximoFormulario(FabAcaoCRMCliente.CHAMADO_FRM_LISTAR.getRegistro().getComoFormulario());

            }
        }.getResposta();
    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.CHAMADO_CTR_CANCELAR)
    public static ItfRespostaAcaoDoSistema chamadoCancelar(ChamadoCliente pChamado) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(UsuarioCrmCliente.class), pChamado) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    try {

                        ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(pChamado.getAtendenteResponsavel().getCodigoMatrix(),
                                pChamado.getUsuarioCliente().getNome() + "CANCELOU o chamado Cod.:" + pChamado.getId() + " de " + pChamado.getPessoa().getNome() + " do tipo " + pChamado.getTipoChamado().getNome()
                        );

                    } catch (Throwable t) {

                    }
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ChamadoCliente chamado = loadEntidade(pChamado);
                if (pChamado.getStatus().equals(FabStatusChamado.FINALIZADO.getRegistro())) {
                    throw new ErroRegraDeNegocio("O status do chamado não permite alteração");
                }
                removerEntidade(chamado);
                setProximoFormulario(FabAcaoCRMCliente.CHAMADO_FRM_LISTAR.getRegistro().getComoFormulario());
            }
        }.getResposta();
    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.CHAMADO_CTR_NOTIFICAR_RESONSAVEIS)
    public static ItfRespostaAcaoDoSistema chamadoNotificar(ChamadoCliente pChamado) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(UsuarioCrmCliente.class), pChamado) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ChamadoCliente chamado = loadEntidade(pChamado);
                List<NotificacaoResponsaveisChamado> notificacoes = chamado.getNotificacoes();
                if (!notificacoes.isEmpty()) {
                    UtilCRCListasObjeto.ordernarPorCampoReverso(notificacoes, "dataHoraNotificacao");
                    NotificacaoResponsaveisChamado ultimaNotificacao = notificacoes.get(0);
                    Date dataLimiteNotificacao = UtilCRCDataHora.incrementaHoras(ultimaNotificacao.getDataHoraNotificacao(), 5);
                    if (dataLimiteNotificacao.getTime() > new Date().getTime()) {
                        //    throw new ErroRegraDeNegocio("Proxima notificação autorizada a partir das " + UtilCRCDataHora.getDataHoraString(dataLimiteNotificacao, UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO));
                    }
                }

                boolean foinotificado = false;

                UsuarioCRM usuarioPrincipalNotificao = chamado.getAtendenteResponsavel();

                if (usuarioPrincipalNotificao == null) {
                    usuarioPrincipalNotificao = ((Pessoa) chamado.getCPinst("pessoa").getValor()).getUsuarioAtendimento();
                }
                if (usuarioPrincipalNotificao == null) {
                    usuarioPrincipalNotificao = chamado.getPessoa().getUsuarioResponsavel();
                }
                if (usuarioPrincipalNotificao == null) {
                    usuarioPrincipalNotificao = chamado.getPessoa().getUsuarioCriou();
                }
                if (usuarioPrincipalNotificao == null) {
                    usuarioPrincipalNotificao = chamado.getPessoa().getUsuarioCriou();
                }
                if (usuarioPrincipalNotificao == null && chamado.getPessoa().getUsuariosResponsaveis().size() == 1) {
                    usuarioPrincipalNotificao = chamado.getPessoa().getUsuarioResponsavel();
                }

                String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER, chamado);
                url = url.replace("https://atendimento.", "htpps://crm.");

                final List<UsuarioCRM> usuariosParaNotificar = new ArrayList<>();
                if (usuarioPrincipalNotificao == null) {
                    throw new ErroRegraDeNegocio("Nenhum responsável econtrado");
                }
                usuariosParaNotificar.add(usuarioPrincipalNotificao);
                final UsuarioCRM usrNot = usuarioPrincipalNotificao;

                chamado.getAtendentesConvidados().stream().filter(atd -> !atd.equals(usrNot)).forEach(usuariosParaNotificar::add);

                for (UsuarioCRM usuario : usuariosParaNotificar) {
                    if (usuario.getContatoVinculado() != null) {
                        if (usuario.getContatoVinculado().isPossuiTelefone()) {
                            try {
                                foinotificado = FabIntegracaoSMS.ENVIAR_MENSAGEM.
                                        getAcao(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(usuario.getContatoVinculado().getCelular()), chamado.getUsuarioCliente().getNome() + "solicita atenção no chamado cod " + chamado.getId() + "criado para" + chamado.getPessoa().getNome() + " ").getResposta().isSucesso();
                            } catch (Throwable t) {

                            }
                        }
                    }

                    if (usuario.getContatoVinculado().isPossuiTelefone()) {

                        //String conteudoSMS = "Um novo chamado foi aberto para " + chamado.getPessoa().getNome() + "O assunto é <i></i>" + " <h1>Clique aqui para ver:</h1>";
                        // foinotificado
                        //        = foinotificado || FabIntegracaoSMS.ENVIAR_MENSAGEM.getAcao(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(chamado.getAtendenteResponsavel().getContatoVinculado().getCelular()), "Novo chamado:" + url).getResposta().isSucesso();
                    }

                    try {
                        ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(usrNot.getCodigoMatrix(),
                                chamado.getUsuarioCliente().getNome() + "solicita atenção no chamado cod " + chamado.getId() + "criado para" + chamado.getPessoa().getNome() + " acesse o chamado via " + url);
                    } catch (Throwable t) {

                    }

                    String conteudoEmail = chamado.getUsuarioCliente().getNome() + "solicita atenção no chamado cod " + chamado.getId() + "criado para" + chamado.getPessoa().getNome() + " do tipo " + chamado.getTipoChamado().getNome()
                            + "acesse <h1> <a href='" + url + "'>Abrir chamado</a></h1>";
                    String conteudoNotificacao = chamado.getUsuarioCliente().getNome() + "solicita atenção no chamado cod " + chamado.getId() + "criado para" + chamado.getPessoa().getNome() + " do tipo Acesse o chamado em:" + chamado.getTipoChamado().getNome() + url;
                    try {
                        foinotificado = ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(usuario.getCodigoMatrix(), conteudoNotificacao) != null;
                    } catch (ErroConexaoServicoChat ex) {
                        Logger.getLogger(ModuloCRMCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (usuario.getContatoVinculado().isPossuiEmail()) {
                        try {
                            foinotificado = foinotificado
                                    || ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(
                                            SBCore.getUsuarioLogado(),
                                            usuario,
                                            chamado.getUsuarioCliente().getNome() + " da empresa " + chamado.getUsuarioCliente().getRepresentanteLegal().getNomeCurto() + " solicita atenação no chamado",
                                            conteudoEmail);
                        } catch (ErroEnvioEmail ex) {
                            addAviso("Falha notificando " + usuario.getEmail());
                        }

                    }

                }
                NotificacaoResponsaveisChamado novaNotificacao = new NotificacaoResponsaveisChamado();
                novaNotificacao.setChamado(chamado);
                novaNotificacao.setDataHoraNotificacao(new Date());
                atualizarEntidade(novaNotificacao);

            }
        }.getResposta();
    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.DASHBOARD_CTR_SATISFEITO)
    public static ItfRespostaAcaoDoSistema satisfacaoDeclararSatisfeito(UsuarioCrmCliente pUsuario) {
        final SatisfacaoCliente satisfacao = pUsuario.getSatisfacao();
        final Pessoa pessoa = pUsuario.getRepresentanteLegal();
        pessoa.getUsuariosResponsaveis().size();

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(UsuarioCrmCliente.class), pUsuario) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    List<UsuarioCRM> usuariosNTF = new ArrayList<>();
                    if (pessoa.getUsuarioAtendimento() != null) {
                        usuariosNTF.add(pessoa.getUsuarioAtendimento());
                    }
                    if (pessoa.getUsuarioResponsavel() != null) {
                        if (!usuariosNTF.contains(pessoa.getUsuarioResponsavel())) {
                            usuariosNTF.add(pessoa.getUsuarioResponsavel());
                        }

                    }
                    for (UsuarioCRM usr : pessoa.getUsuariosResponsaveis()) {
                        if (!usuariosNTF.contains(usr)) {
                            usuariosNTF.add(usr);
                        }
                    }
                    for (UsuarioCRM usrNtf : usuariosNTF) {
                        try {
                            ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(usrNtf.getCodigoMatrix(), "Parábens! "
                                    + pUsuario.getNome() + " de " + pessoa.getNome() + " alterou o nível de satisfação de  " + satisfacao.getNome() + " para satisfeito"
                            );
                        } catch (Throwable t) {

                        }
                    }
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                UsuarioCrmCliente usuario = loadEntidade(pUsuario);
                if (usuario.getSatisfacao().equals(FabSatisfacaoCliente.SATISFEITO.getRegistro())) {
                    throw new ErroRegraDeNegocio("Nenhuma modificação realizada.");
                }
                usuario.setSatisfacao(FabSatisfacaoCliente.SATISFEITO.getRegistro());
                Pessoa pessoaresponsavel = loadEntidade(usuario.getRepresentanteLegal());
                pessoaresponsavel.setSatisfacao(FabSatisfacaoCliente.SATISFEITO.getRegistro());
                UtilSBPersistencia.mergeRegistro(pessoaresponsavel);
                UsuarioCRM responsavel = (UsuarioCRM) usuario.getRepresentanteLegal().getCampoInstanciadoByNomeOuAnotacao(CPPessoa.usuarioresponsavel).getValor();
                addAviso("Obrigado, nós notificamos " + responsavel.getNome() + " sobre sua satisfação atual, nos informe sobre como podemos te deixar muito satisfeito abrindo chamados em nossa ouvidoria.");
            }
        }.getResposta();
    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.DASHBOARD_CTR_MUITO_SATISFEITO)
    public static ItfRespostaAcaoDoSistema satisfacaoDeclararMuitoSatisfeito(UsuarioCrmCliente pUsuario) {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        UsuarioCrmCliente usuario = UtilSBPersistencia.loadEntidade(pUsuario, em);

        try {
            final SatisfacaoCliente satisfacao = usuario.getSatisfacao();
            final Pessoa pessoa = usuario.getRepresentanteLegal();
            pessoa.getUsuariosResponsaveis().size();
            return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(UsuarioCrmCliente.class), pUsuario) {
                @Override
                public void executarAcoesFinais() throws ErroEmBancoDeDados {
                    super.executarAcoesFinais();
                    if (isSucesso()) {
                        List<UsuarioCRM> usuariosNTF = new ArrayList<>();
                        if (pessoa.getUsuarioAtendimento() != null) {
                            usuariosNTF.add(pessoa.getUsuarioAtendimento());
                        }
                        if (pessoa.getUsuarioResponsavel() != null) {
                            if (!usuariosNTF.contains(pessoa.getUsuarioResponsavel())) {
                                usuariosNTF.add(pessoa.getUsuarioResponsavel());
                            }

                        }
                        for (UsuarioCRM usr : pessoa.getUsuariosResponsaveis()) {
                            if (!usuariosNTF.contains(usr)) {
                                usuariosNTF.add(usr);
                            }
                        }
                        for (UsuarioCRM usrNtf : usuariosNTF) {
                            try {
                                ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(usrNtf.getCodigoMatrix(), "Parábens! "
                                        + pUsuario.getNome() + " de " + pessoa.getNome() + " alterou o nível de satisfação de  " + satisfacao.getNome() + " para MUITO SATISFEITO "
                                );
                            } catch (Throwable t) {

                            }
                        }
                    }
                }

                @Override
                public void regraDeNegocio() throws ErroRegraDeNegocio {
                    UsuarioCrmCliente usuario = loadEntidade(pUsuario);
                    if (usuario.getSatisfacao().equals(FabSatisfacaoCliente.MUITOSATISFEITO.getRegistro())) {
                        throw new ErroRegraDeNegocio("Nenhuma modificação realizada.");
                    }
                    usuario.setSatisfacao(FabSatisfacaoCliente.MUITOSATISFEITO.getRegistro());
                    UtilSBPersistencia.mergeRegistro(usuario, getEm());
                    Pessoa pessoaresponsavel = loadEntidade(usuario.getRepresentanteLegal());
                    pessoaresponsavel.setSatisfacao(FabSatisfacaoCliente.MUITOSATISFEITO.getRegistro());
                    UtilSBPersistencia.mergeRegistro(pessoaresponsavel);
                    UsuarioCRM responsavel = (UsuarioCRM) usuario.getRepresentanteLegal().getCampoInstanciadoByNomeOuAnotacao(CPPessoa.usuarioresponsavel).getValor();
                    addAviso("Obrigado, nós notificamos " + responsavel.getNome() + " sobre sua satisfação atual, você também já está em nossos corações, e na lista de ofertas exclusivas ;)");
                }
            }.getResposta();
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.DASHBOARD_CTR_INSATISFEITO)
    public static ItfRespostaAcaoDoSistema satisfacaoDeclararInsatisfeito(UsuarioCrmCliente pUsuario) {

        final SatisfacaoCliente satisfacao = pUsuario.getSatisfacao();
        final Pessoa pessoa = pUsuario.getRepresentanteLegal();
        pessoa.getUsuariosResponsaveis().size();

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(UsuarioCrmCliente.class), pUsuario) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    List<UsuarioCRM> usuariosNTF = new ArrayList<>();
                    if (pessoa.getUsuarioAtendimento() != null) {
                        usuariosNTF.add(pessoa.getUsuarioAtendimento());
                    }
                    if (pessoa.getUsuarioResponsavel() != null) {
                        if (!usuariosNTF.contains(pessoa.getUsuarioResponsavel())) {
                            usuariosNTF.add(pessoa.getUsuarioResponsavel());
                        }

                    }
                    for (UsuarioCRM usr : pessoa.getUsuariosResponsaveis()) {
                        if (!usuariosNTF.contains(usr)) {
                            usuariosNTF.add(usr);
                        }
                    }
                    for (UsuarioCRM usrNtf : usuariosNTF) {
                        try {
                            ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(usrNtf.getCodigoMatrix(),
                                    pUsuario.getNome() + " de " + pessoa.getNome() + " alterou o nível de satisfação de  " + satisfacao.getNome() + " para Insatisfeito"
                            );
                        } catch (Throwable t) {

                        }
                    }
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                UsuarioCrmCliente usuario = loadEntidade(pUsuario);
                if (usuario.getSatisfacao().equals(FabSatisfacaoCliente.INSATISFEITO.getRegistro())) {
                    throw new ErroRegraDeNegocio("Nenhuma modificação realizada.");
                }

                UsuarioCRM responsavel = (UsuarioCRM) usuario.getRepresentanteLegal().getCampoInstanciadoByNomeOuAnotacao(CPPessoa.usuarioresponsavel).getValor();
                List<StatusChamado> statusDosChamadosAtivos = new ArrayList<>();
                statusDosChamadosAtivos.add(FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro());
                statusDosChamadosAtivos.add(FabStatusChamado.EM_ATENDIMENTO.getRegistro());
                statusDosChamadosAtivos.add(FabStatusChamado.ATRAZADO.getRegistro());
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ChamadoCliente.class, getEMResposta());
                consulta.addCondicaoManyToOneContemNoIntervalo("status", statusDosChamadosAtivos);
                long chamadosAbertos = consulta.resultadoSomarQuantidade();
                if (chamadosAbertos > 0) {
                    addAviso("Obrigado, nós notificamos " + responsavel.getNome() + " sobre sua insatisfação. O setor de qualidade acompanhará a solução dos seus problemas.");
                } else {
                    addAviso("Obrigado, nós notificamos " + responsavel.getNome() + " sobre sua insatisfação, "
                            + "não existe nenhum chamado ativo no momento, se fizer sentido você pode criar chamados, clicando em meus chamados");
                }

                usuario.setSatisfacao(FabSatisfacaoCliente.INSATISFEITO.getRegistro());
                Pessoa pessoaresponsavel = loadEntidade(usuario.getRepresentanteLegal());
                pessoaresponsavel.setSatisfacao(FabSatisfacaoCliente.INSATISFEITO.getRegistro());
                UtilSBPersistencia.mergeRegistro(pessoaresponsavel);
                UtilSBPersistencia.mergeRegistro(usuario, getEm());

            }
        }.getResposta();
    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.RESERVAS_CTR_RESERVAR)
    public static ItfRespostaAcaoDoSistema reservaHorario(ReservaHorarioCRM pReserva) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    ServicoNotificacao.notificarReservaAtendente(FabTipoNotificacao.NOTIFICAR_ATENDENTE_CLIENTE_MARCOU_NA_AGENDA, pReserva);
                    MapaHorariosDisponiveis.adicionarReservaAtendente((ReservaHorario) getRetorno());
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pReserva.getId() != null && pReserva.getId() > 0) {
                    throw new ErroRegraDeNegocio("A reserva já foi registrada");
                }
                pReserva.setStatus(FabStatusReservaHorario.AGENDADO.getRegistro());

                ReservaHorarioCRM reservaAtualizada = atualizarEntidade(pReserva);
                setRetorno(reservaAtualizada);

                setProximoFormulario(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO.getRegistro().getComoFormulario());
            }

        }.getResposta();

    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.RESERVAS_CTR_CONFIRMAR)
    public static ItfRespostaAcaoDoSistema reservaHorarioConfirmar(ReservaHorarioCRM pReserva) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                if (isSucesso()) {
                    ServicoNotificacao.notificarReservaAtendente(FabTipoNotificacao.NOTIFICAR_ATENDENTE_CLIENTE_CANCELOU, pReserva);
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ReservaHorario reserva = loadEntidade(pReserva);
                if (!reserva.getStatus().equals(FabStatusReservaHorario.AGENDADO.getRegistro())) {
                    throw new ErroRegraDeNegocio("O status não é compatível");
                }
                reserva.setStatus((StatusReserva) FabStatusReservaHorario.CONFIRMADO.getRegistro());
                ReservaHorario reservaAtualizada = atualizarEntidade(reserva);
                setRetorno(reservaAtualizada);

                atualizarEntidade(reserva);
                setProximoFormulario(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO.getRegistro().getComoFormulario());

            }

        }.getResposta();

    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.RESERVAS_CTR_CANCELAR)
    public static ItfRespostaAcaoDoSistema reservaHorarioCancelar(ReservaHorarioCRM pReserva) {

        final String nomeCliente;
        final String tipoChamado;
        if (pReserva.getPessoaRelacionada() == null) {
            nomeCliente = "Cliente indefinodo";
            tipoChamado = "Tipo Agenda indefinida";
        } else {
            nomeCliente = pReserva.getPessoaRelacionada().getNome();
            tipoChamado = pReserva.getTipoAgendamento().getNome();
        }

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                try {
                    if (isSucesso()) {

                        ServicoNotificacao.notificarReservaAtendente(FabTipoNotificacao.NOTIFICAR_ATENDENTE_CLIENTE_CANCELOU, pReserva);
                    }
                } catch (Throwable t) {

                }

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                ReservaHorario reserva = loadEntidade(pReserva);

                if (reserva.getStatus().equals(FabStatusReservaHorario.REALIZADO.getRegistro())) {
                    throw new ErroRegraDeNegocio("O status não é compatível");
                }
                if (reserva.getStatus().equals(FabStatusReservaHorario.CONFIRMADO.getRegistro())) {
                    addAlerta("Esta reserva já foi confirmada, por favor clique em cancelar novamente, para confirmar sua ação.");
                    reserva.setStatus((StatusReserva) FabStatusReservaHorario.AGENDADO.getRegistro());
                } else {
                    setRetorno(reserva);
                    removerEntidade(reserva);
                    setProximoFormulario(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO.getRegistro().getComoFormulario());

                }

            }
        }.getResposta();

    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.DOCUMENTOS_CTR_ENVIAR_ARQUIVO)
    public static ItfRespostaAcaoDoSistema
            arquivoUpload(ArquivoCliente pArquivo) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(ArquivoCliente.class
        ), pArquivo) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ArquivoCliente arquivoCLiente = atualizarEntidade(pArquivo);
                Pessoa p = loadEntidade(arquivoCLiente.getProspecto());
                if (p.getUsuarioAtendimento() != null) {
                    String telefone = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(p.getUsuarioAtendimento().getTelefone());
                    if (telefone != null) {
                        //   ItfRespostaWebServiceSimples resposta = FabIntegracaoSMS.ENVIAR_MENSAGEM.getAcao(telefone, p.getNome() + " enviou um arquivo" + pArquivo.getNome()).getResposta();
                    }
                }
                if (p.getResponsavel() != null) {
                    String telefone = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(p.getUsuarioResponsavel().getTelefone());
                    if (telefone != null) {
                        ItfRespostaWebServiceSimples resposta = FabIntegracaoSMS.ENVIAR_MENSAGEM.getAcao(telefone, p.getNome() + " enviou um arquivo" + pArquivo.getNome()).getResposta();
                    }
                }

            }
        }
                .getResposta();
    }

}
