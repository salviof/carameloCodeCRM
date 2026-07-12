/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroRegraDeNEgocioChat;
import br.org.coletivoJava.fw.api.erp.chat.ItfErpChatService;
import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.ModuloCRMAplicacao;
import br.org.carameloCode.erp.modulo.crm.api.email.ErroEnvioEmail;
import br.org.coletivoJava.integracoes.amazonSMS.FabIntegracaoSMS;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.chat.UtilCRMChat;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.EventoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.marketing.Util.ErroNotificacao;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
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
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.CPUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.FabTipoNotificacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposCRMCaramelo;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposCRMCaramelo.CRM_ADMIN;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoUsuarioChat;
import static com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.chat.UtilCRMChat.gerarListasUsuariosContatos;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.regras_de_negocio_e_controller.intranetMarketingDigital.controller.ServicoNotificacao;
import static com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.chat.UtilCRMChat.gerarListasUsuariosAtendTimeIntranet;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import org.apache.commons.compress.utils.Lists;

/**
 *
 * @author salvio
 */
public class ModuloCRMAtendimentoChamado extends ControllerAbstratoSBPersistencia {

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_GERAR_CODIGO_ACESSO)
    public static ItfRespostaAcaoDoSistema chamadoGerarCodigoAcesso(final ChamadoCliente pPessoa) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pPessoa), pPessoa) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ChamadoCliente chamado = loadEntidade(pPessoa);
                ConsultaDinamicaDeEntidade consultaToken
                        = new ConsultaDinamicaDeEntidade(TokenAcessoDinamico.class,
                                getEm());

                consultaToken.addcondicaoCampoIgualA("slugAcaoFormulario", FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR.getRegistro().getNomeUnico());
                consultaToken.addcondicaoCampoIgualA("codigoEntidade", String.valueOf(chamado.getId()));

                List<TokenAcessoDinamico> tokens = consultaToken.resultadoRegistros();
                if (!tokens.isEmpty()) {
                    UtilCRCListasObjeto.ordernarPorCampoReverso(tokens, "dataHoraCriacao");
                    TokenAcessoDinamico ultimoToken = tokens.get(0);
                    Date agora = new Date();
                    Date validade = ultimoToken.getValidade();
                    if (validade.getTime() > agora.getTime()) {
                        throw new ErroRegraDeNegocio("Já existe um token ativo");
                    }
                }
                TokenAcessoDinamico token = new TokenAcessoDinamico();
                token.setSlugAcaoFormulario(FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR.getRegistro().getNomeUnico());
                token.setCodigoEntidade(String.valueOf(chamado.getId()));
                token.setCodigo(UUID.randomUUID().toString().replace("-", "_"));
                token.setDataHoraCriacao(new Date());
                token.setEmail(chamado.getUsuarioCliente().getEmail());
                token.setValidade(UtilCRCDataHora.incrementaDias(new Date(), 4));
                token
                        .setEntidadeDoAcesso(ChamadoCliente.class
                                .getSimpleName());
                atualizarEntidade(token);
            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_CRIAR_CHAMADO)
    public static ItfRespostaAcaoDoSistema chamadocruar(ChamadoCliente pChamado) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(ChamadoCliente.class), (ComoEntidadeSimples) chamadoSalvarMerge(pChamado).getRetorno()) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                if (isSucesso()) {
                    ItfRespostaAcaoDoSistema respAssumir = chamadoAssumirResponsavel(pChamado);
                    setRetorno(respAssumir.dispararMensagens().getRetorno());
                    if (respAssumir.isSucesso()) {
                        setProximoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_DEFINIR_ATENDIMENTO.getRegistro().getComoFormulario());
                    }
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ChamadoCliente chamado = loadEntidade(pChamado);
                if (chamado.getUsuarioCliente() == null) {
                    throw new ErroRegraDeNegocio("O cliente responsável  não foi definido");
                }
                if (chamado.getTipoChamado() == null) {
                    throw new ErroRegraDeNegocio("O tipo  não foi definido");
                }
                if (UtilCRCStringValidador.isNuloOuEmbranco(pChamado.getDescricao())
                        || pChamado.getDescricao().length() < 20) {
                    throw new ErroRegraDeNegocio("Por favor descreva melhor a situação");
                }
                UsuarioCrmCliente usuarioCliente = UtilSBPersistencia.loadEntidade(pChamado.getUsuarioCliente(), getEm());
                chamado.setUsuarioCliente(usuarioCliente);

                if (usuarioCliente.getContatoClienteVinculado() == null) {
                    throw new ErroRegraDeNegocio("Os dados do contato não foram encontrados");
                }

                if (usuarioCliente.getContatoClienteVinculado().getCelular() == null) {
                    throw new ErroRegraDeNegocio("Para abrir o chamado, cadastre o telefone de: " + pChamado.getUsuarioCliente().getNome());
                }

                boolean umnovoChamado = chamado.getStatus().equals(FabStatusChamado.RASCUNHO.getRegistro());

                UtilSBPersistencia.executaSQL(getEMResposta(), "update UsuarioSB set telefone = '" + usuarioCliente.getContatoClienteVinculado().getCelular() + "' where id = " + usuarioCliente.getId());
                chamado.setStatus(FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro());
                chamado.setTitulo(pChamado.getTitulo());
                chamado.setDescricao(pChamado.getDescricao());
                setRetorno(atualizarEntidade(chamado));

                setProximoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER.getRegistro().getComoFormulario());
            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema chamadoSalvarMerge(ChamadoCliente pChamado) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(ChamadoCliente.class), pChamado) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pChamado.getCPinst("pessoa").getValorComoEntidadeSimples() == null) {
                    throw new ErroRegraDeNegocio("não é possível salvar o chamado sem definir a pessoa");
                }
                if (pChamado.getUsuarioCriou() != null) {
                    if (!pChamado.getUsuarioCriou().equals(SBCore.getUsuarioLogado())) {
                        throw new ErroRegraDeNegocio("Este chamado só pode ser modificado por" + pChamado.getUsuarioCriou().getNome());
                    }
                }
                pChamado.setPessoa(loadEntidade(pChamado.getPessoa()));
                ChamadoCliente chamado = pChamado;
                if (pChamado.getId() == null) {
                    ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(ChamadoCliente.class, getEm());
                    novaConsulta.addCondicaoManyToOneIgualA("usuarioCriou", SBCore.getUsuarioLogado());
                    novaConsulta.addCondicaoManyToOneIgualA("status", FabStatusChamado.RASCUNHO.getRegistro());
                    novaConsulta.addCondicaoManyToOneIgualA("pessoa", pChamado.getCPinst("pessoa").getValorComoEntidadeSimples());
                    List<ChamadoCliente> chamadosRascunhos = novaConsulta.resultadoRegistros();
                    if (!chamadosRascunhos.isEmpty()) {
                        chamado = (ChamadoCliente) novaConsulta.resultadoRegistros().get(0);
                    }
                }

                chamado.setUsuarioCriou((UsuarioCRM) SBCore.getUsuarioLogado());
                chamado.setDataHoraCriacao(new Date());

                if (chamado.getStatus() == null) {
                    chamado.setStatus(FabStatusChamado.RASCUNHO.getRegistro());
                }
                if (!chamado.getStatus().equals(FabStatusChamado.RASCUNHO.getRegistro())) {
                    throw new ErroRegraDeNegocio("O status do chamado não permite alteração");
                }

                setRetorno(UtilSBPersistencia.mergeRegistro(chamado, getEMResposta()));
            }
        }
                .getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_NOTIFICAR_CLIENTE)
    public static ItfRespostaAcaoDoSistema chamadoNotificarCliente(final ChamadoCliente pChamado) {
        chamadoGerarCodigoAcesso(pChamado);

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pChamado), pChamado) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ChamadoCliente chamado = loadEntidade(pChamado);
                if (!chamado.getStatus().equals((FabStatusChamado.EM_ATENDIMENTO.getRegistro()))) {
                    throw new ErroRegraDeNegocio("O status do chamado não permite notificações");
                }
                if (!chamado.isNotificarViaSMS() && !chamado.isNotificarViaEmail()) {
                    throw new ErroRegraDeNegocio("O cliente não deseja ser notificado, nem por e-mail nem por sms");
                }
                UsuarioCRM usuarioResponsalvel = loadEntidade((UsuarioCRM) SBCore.getServicoSessao().getSessaoAtual().getUsuario());
                if (usuarioResponsalvel instanceof UsuarioCrmCliente) {
                    throw new ErroRegraDeNegocio("Sem permissão para notificar o cliente");
                }
                ConsultaDinamicaDeEntidade consultaToken = new ConsultaDinamicaDeEntidade(TokenAcessoDinamico.class, getEMResposta());
                consultaToken.addcondicaoCampoIgualA("slugAcaoFormulario", FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR.getRegistro().getNomeUnico());
                consultaToken.addcondicaoCampoIgualA("codigoEntidade", String.valueOf(chamado.getId()));
                TokenAcessoDinamico ultimoToken = null;
                List<TokenAcessoDinamico> tokens = consultaToken.resultadoRegistros();
                if (!tokens.isEmpty()) {
                    UtilCRCListasObjeto.ordernarPorCampoReverso(tokens, "dataHoraCriacao");
                    ultimoToken = tokens.get(0);
                }
                if (ultimoToken == null) {
                    throw new ErroRegraDeNegocio("Nenhum token de acesso foi definido, gere um novo token e tente novamente");
                }
                chamado.getCliente().getCPinst(CPUsuarioCrmCliente.contatoclientevinculado).getValor();
                String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB, ultimoToken);

                url = url.replace("crm.", "atendimento.");
                if (chamado.isNotificarViaEmail()) {

                    String textoEmail = "Olá, " + chamado.getUsuarioCliente().getNome() + " atualizei o seu chamado, você pode usar <a href=\"" + url + "\" > ESTE LINK</a> para acessar informações sobre o chamado, obrigado. <br/>";

                    List<ContatoProspecto> contatos = new ArrayList();
                    contatos.add(chamado.getCliente().getContatoClienteVinculado());
                    try {
                        for (ContatoProspecto ct : contatos) {
                            ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(chamado.getAtendenteResponsavel(), ct, "Atualização sobre :" + chamado.getTitulo(), textoEmail);
                        }

                    } catch (ErroEnvioEmail ex) {
                        throw new ErroRegraDeNegocio("Falha enviando e-mail para cliente");
                    } catch (Throwable t) {
                        throw new ErroRegraDeNegocio("Falha enviando e-mail para cliente");
                    }
                }
                String telefone;
                if (chamado.isNotificarViaSMS()) {
                    String textoSMS = "Olá " + chamado.getUsuarioCliente().getNome() + " atualizei o seu chamdo acesse rápido pelo link " + url;
                    telefone = (String) chamado.getCliente().getContatoClienteVinculado().getCampoInstanciadoByNomeOuAnotacao(CPContatoProspecto.celularformatointernacional).getValor();
                    if (UtilCRCStringValidador.isNuloOuEmbranco(telefone)) {
                        chamado.setNotificarViaSMS(false);
                    } else {
                        ItfRespostaWebServiceSimples resposta = FabIntegracaoSMS.ENVIAR_MENSAGEM.getAcao(telefone, textoSMS).getResposta();
                        String respostaStr = resposta.getRespostaTexto();
                        if (!resposta.isSucesso()) {
                            throw new ErroRegraDeNegocio("Falha enviando SMS para " + telefone + " - Erro:" + respostaStr);
                        }
                    }
                }
                EventoChamado novoEvento = new EventoChamado();
                novoEvento.setChamado(chamado);
                novoEvento.setDataHora(new Date());
                StringBuilder fraseEvento = new StringBuilder();
                fraseEvento.append(usuarioResponsalvel.getNome()).append(" notificou  " + chamado.getCliente().getContatoClienteVinculado().getNome());
                if (chamado.isNotificarViaSMS() && chamado.isNotificarViaEmail()) {
                    fraseEvento.append(" via SMS (" + chamado.getCliente().getContatoClienteVinculado().getCelular() + ") e e-mail (" + chamado.getCliente().getContatoClienteVinculado().getEmail() + ")");
                } else {
                    if (chamado.isNotificarViaEmail()) {
                        fraseEvento.append(" via e-mail (" + chamado.getCliente().getContatoVinculado().getEmail() + ")");
                    } else {
                        fraseEvento.append(" via SMS (" + chamado.getCliente().getContatoClienteVinculado().getTelefone() + ")");
                    }
                }
                novoEvento.setAgente(usuarioResponsalvel);
                novoEvento.setDescricao(fraseEvento.toString());
                atualizarEntidade(novoEvento);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_VALIDAR_CHAMADO)
    public static ItfRespostaAcaoDoSistema chamadoValidar(final ChamadoCliente pChamado) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pChamado), pChamado) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ChamadoCliente chamado = loadEntidade(pChamado);
                try {
                    List<UsuarioCrmCliente> usuariosExternosCLiente = gerarListasUsuariosContatos(pChamado);

                    ///VALIDA SE O CHAMADO ESTÁ APTO PARA SER CRIADO, VERIFICANDO SE USUÁRIO RESPONSÁVEL, E USUARIO DO CLIENTE EXISTEM

                    Pessoa pessoa = UtilSBPersistencia.loadEntidade(chamado.getPessoa(), getEm());

                    UsuarioCRM atendimento = (UsuarioCRM) pessoa.getCPinst(CPPessoa.usuarioatendimento).getValor();
                    UsuarioCRM captadorLead = (UsuarioCRM) pessoa.getCPinst(CPPessoa.usuarioresponsavel).getValor();
                    if (atendimento == null && captadorLead == null) {
                        throw new ErroRegraDeNegocio("Defina um responsável pelo atendimento do cliente para abrir um chamado");
                    }

                    List<UsuarioCRM> usuariosAtendimento = gerarListasUsuariosAtendTimeIntranet(pChamado);
                    if (chamado.getStatus().getId().equals(FabStatusChamado.EM_ATENDIMENTO.getRegistro().getId())) {
                        if (usuariosAtendimento.isEmpty()) {
                            throw new ErroRegraDeNegocio("Usuários de atendimento não foram definidos");
                        }
                    }

                    for (UsuarioCRM atend : usuariosAtendimento) {
                        ComoUsuarioChat usuarioAtendimento = UtilCRMChat.gerarUsuarioAtendimento(atend);
                    }
                    for (UsuarioCrmCliente usrCliente : usuariosExternosCLiente) {
                        ComoUsuarioChat usuarioCliente = UtilCRMChat.gerarUsuarioContatoCliente(usrCliente);
                        if (usuarioCliente == null) {
                            throw new ErroRegraDeNegocio("Falha obtendo usuário relacionado");
                        }
                    }

                } catch (ErroConexaoServicoChat erroConexao) {
                    throw new ErroRegraDeNegocio("Tente mais tarde: Erro conectando com serviço de chat" + erroConexao.getMessage());
                } catch (ErroRegraDeNEgocioChat ex) {
                    throw new ErroRegraDeNegocio("Falha criando chamado:" + ex.getMessage());
                }

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_ABANDONAR_CHAMADO)
    public static ItfRespostaAcaoDoSistema chamadoAbandonar(final ChamadoCliente pChamado) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pChamado), pChamado) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ChamadoCliente chamado = loadEntidade(pChamado);
                chamado.setStatus(FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro());
                if (chamado.getAtendenteResponsavel() != null) {
                    if (SBCore.getServicoSessao().getSessaoAtual().isIdentificado()) {
                        if (chamado.getAtendenteResponsavel().equals(SBCore.getUsuarioLogado())) {
                            chamado.setAtendenteResponsavel(null);
                        }
                    } else {
                        chamado.setAtendenteResponsavel(null);
                    }
                }
                atualizarEntidade(chamado);
                addAlerta("O Chamado foi abandonado, está em busca de um resposável agora");
            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_ASSUMIR_CHAMADO)
    public static ItfRespostaAcaoDoSistema chamadoAssumirResponsavel(final ChamadoCliente pChamado) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pChamado), pChamado) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                if (isSucesso()) {
                    try {
                        UtilCRMChat.gerarSalaChamado(pChamado);
                        ServicoNotificacao.notificarChamadoCliente(FabTipoNotificacao.NOTIFICACAO_CLIENTE_CHAMADO_EM_ATENDIMENTO, pChamado);

                    } catch (ErroConexaoServicoChat | ErroRegraDeNEgocioChat ex) {
                        chamadoAbandonar(pChamado).dispararMensagens();
                    }
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                ChamadoCliente chamado = loadEntidade(pChamado);
                UsuarioCRM usuario = (UsuarioCRM) loadEntidade(SBCore.getUsuarioLogado());
                if (chamado.getStatus().getId().equals(FabStatusChamado.EM_ATENDIMENTO.getRegistro().getId())) {
                    if (chamado.getAtendenteResponsavel().equals(usuario)) {
                        throw new ErroRegraDeNegocio(usuario.getNome() + " já assumiu o chamado");
                    }
                }
                chamado.setAtendenteResponsavel(usuario);
                chamado.setStatus(FabStatusChamado.EM_ATENDIMENTO.getRegistro());
                chamado = atualizarEntidade(chamado);
                ItfRespostaAcaoDoSistema respValidacaoChamado = chamadoValidar(pChamado);
                if (!respValidacaoChamado.isSucesso()) {
                    throw new ErroRegraDeNegocio("Não foi possível assumir o chamado, falha no Matrix" + respValidacaoChamado.getMensagens().get(0).getMenssagem());
                }

                EventoChamado novoEvento = new EventoChamado();
                novoEvento.setChamado(chamado);
                novoEvento.setAgente((UsuarioCRM) SBCore.getUsuarioLogado());
                novoEvento.setDescricao(SBCore.getUsuarioLogado().getNome() + " assumiu o chamado");
                novoEvento.setDataHora(new Date());
                atualizarEntidade(novoEvento);
                chamado.getAtendentesConvidados().size();

                setRetorno(chamado);
                setProximoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER.getRegistro().getComoFormulario());
            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_FECHAR_CHAMADO)
    public static ItfRespostaAcaoDoSistema chamadoFinalizar(ChamadoCliente pChamado) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(UsuarioCrmCliente.class), pChamado) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                EntityManager em = null;
                try {
                    em = UtilSBPersistencia.getEMPadraoNovo();
                    ChamadoCliente chamadoNotificacao = UtilSBPersistencia.loadEntidade(pChamado, em);

                    ServicoNotificacao.notificarChamadoCliente(FabTipoNotificacao.NOTIFICACAO_CLIENTE_CHAMADO_FINALIZADO, chamadoNotificacao);

                    try {
                        //
                        UtilCRMChat.gerarSalaChamado(chamadoNotificacao);
                    } catch (ErroConexaoServicoChat | ErroRegraDeNEgocioChat ex) {
                        Logger.getLogger(ModuloCRMAtendimentoChamado.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    setProximoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_LISTAR_CHAMADOS_EM_ATENDIMENTO.getRegistro().getComoFormulario());

                } finally {
                    UtilSBPersistencia.fecharEM(em);
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

                ComoUsuario usuarioAcao = loadEntidade(SBCore.getUsuarioLogado());
                if (chamado.getAtendenteResponsavel() != null) {
                    if (!chamado.getAtendenteResponsavel().equals(CarameloCode.getUsuarioLogado())) {
                        if (!usuarioAcao.getGrupo().getId().equals(FabGruposCRMCaramelo.CRM_ADMIN.getRegistro().getId())) {
                            if (chamado.getPessoa().getUsuarioAtendimento() != null) {
                                if (!chamado.getPessoa().getUsuarioAtendimento().equals(usuarioAcao)) {
                                    if (chamado.getPessoa().getUsuarioResponsavel() != null) {
                                        if (!chamado.getPessoa().getUsuarioResponsavel().equals(usuarioAcao)) {
                                            throw new ErroRegraDeNegocio("Sem permissão para finalizar este chamado");
                                        }
                                    }
                                }
                            } else {
                                if (chamado.getPessoa().getUsuarioResponsavel() != null) {
                                    if (!chamado.getPessoa().getUsuarioResponsavel().equals(usuarioAcao)) {
                                        throw new ErroRegraDeNegocio("Sem permissão para finalizar este chamado");
                                    }
                                }
                            }
                        }
                    }
                }
                chamado.setAtendenteAnterior((UsuarioCRM) usuarioAcao);
                atualizarEntidade(chamado);
                String salaMatrix = chamado.getSalaMatrix();
                ComoChatSalaBean sala;
                try {
                    sala = UtilCRMChat.chatService.getSalaByAlias(salaMatrix);
                    sala = UtilCRMChat.chatService.getSalaAtualizada(sala);
                } catch (ErroConexaoServicoChat ex) {
                    throw new ErroRegraDeNegocio("Falha de conexão com serviço matrix, tente mais tarde ");
                }

                List<ComoUsuarioChat> usuariosCadastrados = Lists.newArrayList(sala.getUsuarios().iterator());
                for (ComoUsuarioChat usuario : sala.getUsuarios()) {
                    if (UtilCRMChat.chatService.isUmUsuarioAtendimento(usuario)) {
                        try {
                            UtilCRMChat.chatService.salaRemoverMembro(sala, usuario.getCodigoUsuario());
                        } catch (ErroConexaoServicoChat ex) {

                            for (ComoUsuarioChat usuarioAdd : usuariosCadastrados) {
                                try {
                                    UtilCRMChat.chatService.salaAdicionarMembro(sala, usuarioAdd.getCodigoUsuario());
                                } catch (ErroConexaoServicoChat ex1) {

                                }
                            }
                            throw new ErroRegraDeNegocio("Falha removendo usuário atendimento da sala");
                        }
                    }

                }

                EventoChamado novoEvento = new EventoChamado();
                novoEvento.setChamado(chamado);
                novoEvento.setAgente((UsuarioCRM) SBCore.getUsuarioLogado());
                novoEvento.setDescricao("Chamado Finalizado");
                atualizarEntidade(novoEvento);
                setProximoFormulario(FabAcaoCRMCliente.CHAMADO_FRM_LISTAR.getRegistro().getComoFormulario());

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_DEFINIR_RESPONSAVEL)
    public static ItfRespostaAcaoDoSistema chamadoDefinirResponsavel(final ChamadoCliente pChamado) {
        if (pChamado.getAtendenteResponsavel() != null && pChamado.getAtendenteResponsavel().equals(SBCore.getUsuarioLogado())) {
            return chamadoAssumirResponsavel(pChamado);
        }

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pChamado), pChamado) {

            private UsuarioCRM usuarioNovo;
            private UsuarioCRM usuarioAntigo;
            private UsuarioCRM usuarioAcao;
            private ChamadoCliente chamado;
            ItfErpChatService servicoChat = ERPChat.MATRIX_ORG.getImplementacaoDoContexto();

            @Override
            public void executarAcoesIniciais() throws ErroEmBancoDeDados {
                super.executarAcoesIniciais(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                chamado = loadEntidade(pChamado);
                if (pChamado.getAtendenteResponsavel() != null) {
                    usuarioNovo = loadEntidade(pChamado.getAtendenteResponsavel());
                }
                if (chamado.getAtendenteResponsavel() != null) {
                    usuarioAntigo = loadEntidade(chamado.getAtendenteResponsavel());
                } else {
                    usuarioAntigo = null;
                }
                usuarioAcao = (UsuarioCRM) loadEntidade(SBCore.getUsuarioLogado());
            }

            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

                if (isSucesso()) {
                    if (usuarioAntigo != null) {
                        ServicoNotificacao.notificarChamadoAtendente(FabTipoNotificacao.NOTIFICACAO_RESPONSAVEIS_CHAMADO_TRANSFERIDO_PARA_OUTRO, pChamado, false, true, false, false);
                        ServicoNotificacao.notificarChamadoAtendente(FabTipoNotificacao.NOTIFICACAO_RESPONSAVEIS_CHAMADO_TRANSFERIDO_PARA_VOCE, pChamado);
                    } else {
                        try {
                            UtilCRMChat.gerarSalaChamado(pChamado);
                            ServicoNotificacao.notificarChamadoAtendente(FabTipoNotificacao.NOTIFICACAO_RESPONSAVEIS_CHAMADO_ABERTO, pChamado);
                        } catch (ErroConexaoServicoChat | ErroRegraDeNEgocioChat ex) {
                            Logger.getLogger(ModuloCRMAtendimentoChamado.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                if (usuarioNovo == null) {
                    throw new ErroRegraDeNegocio("Selecione um responsável");
                }

                if (usuarioAntigo != null) {
                    if (usuarioAntigo.equals(usuarioNovo)) {
                        throw new ErroRegraDeNegocio(pChamado.getAtendenteResponsavel().getNome() + " já é responsável pelo chamado");
                    }
                }
                chamado.setAtendenteAnterior(usuarioAntigo);
                chamado.setAtendenteResponsavel(usuarioNovo);
                chamado.setStatus(FabStatusChamado.EM_ATENDIMENTO.getRegistro());
                chamado = atualizarEntidade(chamado);
                EventoChamado novoEvento = new EventoChamado();
                novoEvento.setAgente((UsuarioCRM) SBCore.getUsuarioLogado());
                novoEvento.setDescricao(SBCore.getUsuarioLogado().getNome() + " definiu " + chamado.getAtendenteResponsavel().getNome() + " como responsável");
                novoEvento.setChamado(chamado);
                novoEvento.setDataHora(new Date());
                atualizarEntidade(novoEvento);
                setProximoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_LISTAR_TODOS_STATUS.getRegistro().getComoFormulario());
            }
        }.getResposta();
    }

    private static String getUrlChamadoAcessoCLiente(ChamadoCliente pChamado) {
        ItfTokenAcessoDinamico token = SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR, pChamado, pChamado.getUsuarioCliente().getEmail());
        String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB, token);

        url = url.replace("crm.", "atendimento.");
        return url;
    }

    private static String getUrlChamadoAcessoAtendimento(ChamadoCliente pChamado) {
        return SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER, pChamado);
    }

}
