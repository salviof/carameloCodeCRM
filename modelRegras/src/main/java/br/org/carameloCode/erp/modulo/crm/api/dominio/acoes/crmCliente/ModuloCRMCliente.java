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
import br.org.coletivojava.erp.notificacao.api.ErroGerandoNotificacao;
import br.org.coletivojava.erp.notificacao.padrao.controller.ModuloNotificacao;
import br.org.coletivojava.erp.notificacao.padrao.model.notificacao.NotificacaoSB;
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
import static com.super_bits.Casa_Nova.Intranet_Marketing_Digital.regras_de_negocio_e_controller.intranetMarketingDigital.controller.ServicoNotificacao.NOTIFICACAO_SRV;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.FabAcaoAcessoAnonimoIntranet;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.InfoAcaoaAcessoAnonimoCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoComGestaoEntityManager;
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
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
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
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda.MapaHorariosDisponiveis;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabStatusReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.StatusReserva;

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

                    new ExecucaoComGestaoEntityManager() {

                        @Override
                        public void regraDeNegocio() throws ErroRegraDeNegocio {
                            ChamadoCliente chamado = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getRetorno(), getEm());
                            NotificacaoSB notificacao;
                            try {
                                notificacao = NOTIFICACAO_SRV.getNotificacao(FabTipoNotificacao.NOTIFICACAO_CLIENTE_PROTOCOLO_DE_CHAMADO.getRegistro(), chamado.getUsuarioCliente(), chamado);
                                notificacao.setCodigoEntidadeRelacionada(chamado.getId().toString());
                                ModuloNotificacao.notificacaoRegistrar(notificacao).isSucesso();
                                ServicoNotificacao.chamadoNotificarResponsaveis(FabTipoNotificacao.NOTIFICACAO_RESPONSAVEIS_CHAMADO_ABERTO, chamado);
                            } catch (ErroGerandoNotificacao ex) {
                                Logger.getLogger(ModuloCRMCliente.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    };

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

                ServicoNotificacao.chamadoNotificarResponsaveis(FabTipoNotificacao.NOTIFICACAO_RESPONSAVEIS_CHAMADO_ABERTO, chamadoAtualizado);

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
    public static ItfRespostaAcaoDoSistema reservaHorario(ReservaHorario pReserva) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    MapaHorariosDisponiveis.adicionarReservaAtendente((ReservaHorario) getRetorno());
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pReserva.getId() != null && pReserva.getId() > 0) {
                    throw new ErroRegraDeNegocio("A reserva já foi registrada");
                }
                pReserva.setStatus(FabStatusReservaHorario.AGENDADO.getRegistro());
                ReservaHorario reservaAtualizada = atualizarEntidade(pReserva);
                setRetorno(reservaAtualizada);
                try {
                    ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(reservaAtualizada.getAtendenteResponsavel().getCodigoMatrix(),
                            reservaAtualizada.getAtendidoResponsavel().getNome() + " agendou uma reunião com você dia "
                            + UtilCRCDataHora.getDataHoraString(reservaAtualizada.getInicioReservaAtendente(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO)
                            + " para  " + reservaAtualizada.getPessoaRelacionada().getNome() + " sobre " + reservaAtualizada.getTipoAgendamento().getNome()
                    );
                } catch (Throwable t) {

                }

                setProximoFormulario(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO.getRegistro().getComoFormulario());
            }

        }.getResposta();

    }

    @InfoAcaoaAcessoAnonimoCRM(acao = FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_CTR_CANCELAR)
    public static ItfRespostaAcaoDoSistema reservaPubHorarioCancelar(ReservaHorario pReserva) {
        final String codigoRC = pReserva.getAtendenteResponsavel().getCodigoMatrix();
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
                        MapaHorariosDisponiveis.removerReservaAtendente((ReservaHorario) getRetorno());
                        ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(codigoRC,
                                pReserva.getAtendidoResponsavel().getNome() + " cancelou uma reunião com você dia "
                                + UtilCRCDataHora.getDataHoraString(pReserva.getInicioReservaAtendente(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO)
                                + " para  " + nomeCliente + " sobre " + tipoChamado);
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
                    setProximoFormulario(FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_LISTAR_HORARIOS.getRegistro().getComoFormulario());
                }

            }
        }.getResposta();

    }

    @InfoAcaoaAcessoAnonimoCRM(acao = FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_CTR_CONFIRMAR)
    public static ItfRespostaAcaoDoSistema reservaConfirmar(ReservaHorario pReserva) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ReservaHorario reserva = loadEntidade(pReserva);
                reserva.setStatus(FabStatusReservaHorario.CONFIRMADO.getRegistro());
                try {
                    ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(reserva.getAtendenteResponsavel().getCodigoMatrix(),
                            reserva.getAtendidoResponsavel().getNome() + " confirmou a reunião nas proximas 24 horas, para  " + reserva.getPessoaRelacionada().getNome() + " sobre " + reserva.getTipoAgendamento().getNome()
                    );
                } catch (Throwable t) {

                }
                atualizarEntidade(reserva);
            }
        }.getResposta();

    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.RESERVAS_CTR_CONFIRMAR)
    public static ItfRespostaAcaoDoSistema reservaHorarioConfirmar(ReservaHorario pReserva) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ReservaHorario reserva = loadEntidade(pReserva);
                if (!reserva.getStatus().equals(FabStatusReservaHorario.AGENDADO.getRegistro())) {
                    throw new ErroRegraDeNegocio("O status não é compatível");
                }
                reserva.setStatus((StatusReserva) FabStatusReservaHorario.CONFIRMADO.getRegistro());
                ReservaHorario reservaAtualizada = atualizarEntidade(reserva);
                setRetorno(reservaAtualizada);
                try {
                    ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(reservaAtualizada.getAtendenteResponsavel().getCodigoMatrix(),
                            reservaAtualizada.getAtendidoResponsavel().getNome() + " confirmou a reunião nas proximas 24 horas, para  " + reservaAtualizada.getPessoaRelacionada().getNome() + " sobre " + reservaAtualizada.getTipoAgendamento().getNome()
                    );
                } catch (Throwable t) {

                }
                atualizarEntidade(reserva);
                setProximoFormulario(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO.getRegistro().getComoFormulario());

            }

        }.getResposta();

    }

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.RESERVAS_CTR_CANCELAR)
    public static ItfRespostaAcaoDoSistema reservaHorarioCancelar(ReservaHorario pReserva) {
        final String codigoRC = pReserva.getAtendenteResponsavel().getCodigoMatrix();
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
                        MapaHorariosDisponiveis.removerReservaAtendente((ReservaHorario) getRetorno());
                        ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(codigoRC,
                                pReserva.getAtendidoResponsavel().getNome() + " cancelou uma reunião com você dia "
                                + UtilCRCDataHora.getDataHoraString(pReserva.getInicioReservaAtendente(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO)
                                + " para  " + nomeCliente + " sobre " + tipoChamado);
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

    @InfoAcaoaAcessoAnonimoCRM(acao = FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_CTR_RESERVAR)
    public static ItfRespostaAcaoDoSistema reservaPublicaReservar(ReservaHorario pReserva) {

        ItfRespostaAcaoDoSistema respCadastroContato = new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pReserva.getDadosContatoAcessoAnonimo() == null) {
                    throw new ErroRegraDeNegocio("Os dados do contato não foram enviados");
                }
                String nomeEmpresa = pReserva.getDadosContatoAcessoAnonimo().getNomeEmpresa();
                String nomeContato = pReserva.getDadosContatoAcessoAnonimo().getNomeUsuario();

                if (nomeEmpresa == null || nomeEmpresa.isEmpty()) {
                    nomeEmpresa = "Empresa em nome de " + nomeContato;
                }

                if (pReserva.getDadosContatoAcessoAnonimo() == null) {
                    throw new ErroRegraDeNegocio("Defina os dados do contato");
                }

                String emailContatp = pReserva.getDadosContatoAcessoAnonimo().getEmail();
                String telefone = pReserva.getDadosContatoAcessoAnonimo().getCelular();
                if (telefone == null) {
                    throw new ErroRegraDeNegocio("Informe o celular");
                }

                String site = pReserva.getDadosContatoAcessoAnonimo().getSite();
                String celInternancional = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(telefone);
                ContatoProspecto contatoExistente = (ContatoProspecto) UtilSBPersistencia.gerarConsultaDeEntidade(ContatoProspecto.class, getEm()).addcondicaoCampoIgualA(CPContatoProspecto.celularformatointernacional, celInternancional).getPrimeiroRegistro();

                PessoaJuridica pessoa = null;
                if (contatoExistente != null) {
                    pessoa = UtilSBPersistencia.getRegistroByID(PessoaJuridica.class, contatoExistente.getProspecto().getId(), getEMResposta());
                    ModuloCRMAtendimento.prospectoCriarUsuarios(pessoa);
                } else {
                    //campos = {
                    //"[separador: Cadastro básico]",
                    //"nome", "site", "umPerfilPrivado", "telefonePrincipal",
                    //"[separador: Dados  Básicos de Marketing]",
                    //"origem", "tipoEmpresa", "porte",
                    //"[separador: Contato]", "responsavel", "contatoPrincipal.email", "contatoPrincipal.celular",
                    //  "[separador: Obeservações]", "observacao"
                    //}
                    pessoa = new PessoaJuridica();
                    try {

                        pessoa.setUsuarioAtendimento(pReserva.getAtendenteResponsavel());
                        pessoa.getCPinst(CPPessoa.nome).setValorSeValido(nomeContato);
                        if (site != null && !site.isEmpty()) {
                            pessoa.getCPinst(CPPessoaJuridica.site).setValorSeValido(site);
                        }
                        pessoa.setOrigem((OrigemProspecto) UtilSBPersistencia.getRegistroByID(OrigemProspecto.class,
                                5l));
                        pessoa.setPorte((Porte) UtilSBPersistencia.getRegistroByID(Porte.class,
                                4l));
                        pessoa
                                .setTipoEmpresa((TipoEmpresa) UtilSBPersistencia.getRegistroByID(TipoEmpresa.class,
                                        4l));
                    } catch (ErroValidacao ex) {
                        throw new ErroRegraDeNegocio(ex.getMessage());
                    }
                    pessoa.setObservacao(pReserva.getDadosContatoAcessoAnonimo().getObservacao());
                    try {
                        pessoa.getCPinst(CPPessoa.responsavel).setValorSeValido(nomeContato);
                        ContatoProspecto novoContato = (ContatoProspecto) pessoa.getCPinst(CPPessoa.contatoprincipal).getValor();
                        novoContato.getCPinst(CPContatoProspecto.email).setValorSeValido(emailContatp);

                        novoContato.getCPinst(CPContatoProspecto.celular).setValorSeValido(telefone);

                    } catch (ErroValidacao ex) {
                        throw new ErroRegraDeNegocio(ex.getMensagemAoUsuario());
                    }

                }
                if (pessoa.getId() == null) {
                    ItfRespostaAcaoDoSistema respostaCadastroNovoProsp = ModuloCRMAtendimento.prospectoSalvar(pessoa);
                    setRetorno(respostaCadastroNovoProsp.getRetorno());
                    if (!respostaCadastroNovoProsp.isSucesso()) {
                        addErro(respostaCadastroNovoProsp.getMensagens().get(0).getMenssagem());
                    }
                } else {
                    setRetorno(pessoa);
                }
            }
        }.getResposta();
        if (respCadastroContato.isSucesso()) {
            return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pReserva), pReserva) {
                @Override
                public void regraDeNegocio() throws ErroRegraDeNegocio {
                    if (pReserva.getDadosContatoAcessoAnonimo() == null) {
                        throw new ErroRegraDeNegocio("Defina os dados do contato");
                    }
                    Pessoa pessoa = loadEntidade((Pessoa) respCadastroContato.getRetorno());
                    pReserva.setPessoaRelacionada(pessoa);
                    if (pessoa.getContatoPrincipal().getUsuarioVinculado() == null) {
                        throw new ErroRegraDeNegocio("usuário vinculado não foi encontrado");
                    }
                    pReserva.setAtendidoResponsavel(pessoa.getContatoPrincipal().getUsuarioVinculado());
                    ReservaHorario reserva = atualizarEntidade(pReserva);
                    setRetorno(reserva);
                    setProximoFormulario(FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_RESERVA_CONCLUIDA.getRegistro().getComoFormulario());
                }
            }.getResposta();
        } else {
            respCadastroContato.setRetorno(null);
            respCadastroContato.dispararMensagens();
            return respCadastroContato;
        }

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
