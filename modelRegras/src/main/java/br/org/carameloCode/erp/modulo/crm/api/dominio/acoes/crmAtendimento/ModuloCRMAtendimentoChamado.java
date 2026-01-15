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
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.CPUsuarioCrmCliente;

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
                    setRetorno(chamadoAssumirResponsavel(pChamado).getRetorno());
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                if (pChamado.getUsuarioCliente() == null) {
                    throw new ErroRegraDeNegocio("O cliente responsável  não foi definido");
                }
                if (pChamado.getTipoChamado() == null) {
                    throw new ErroRegraDeNegocio("O tipo  não foi definido");
                }
                if (UtilCRCStringValidador.isNuloOuEmbranco(pChamado.getDescricao())
                        || pChamado.getDescricao().length() < 20) {
                    throw new ErroRegraDeNegocio("Por favor descreva melhor a situação");
                }
                UsuarioCrmCliente usuarioCliente = UtilSBPersistencia.loadEntidade(pChamado.getUsuarioCliente(), getEm());
                pChamado.setUsuarioCliente(usuarioCliente);

                if (usuarioCliente.getContatoClienteVinculado() == null) {
                    throw new ErroRegraDeNegocio("Os dados do contato não foram encontrados");
                }

                if (usuarioCliente.getContatoClienteVinculado().getCelular() == null) {
                    throw new ErroRegraDeNegocio("Para abrir o chamado, cadastre o telefone de: " + pChamado.getUsuarioCliente().getNome());
                }

                UtilSBPersistencia.executaSQL(getEMResposta(), "update UsuarioSB set telefone = '" + usuarioCliente.getContatoClienteVinculado().getCelular() + "' where id = " + usuarioCliente.getId());
                pChamado.setStatus(FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro());
                setRetorno(atualizarEntidade(pChamado));

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

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_ASSUMIR_CHAMADO)
    public static ItfRespostaAcaoDoSistema chamadoAssumirResponsavel(final ChamadoCliente pChamado) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pChamado), pChamado) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();

                if (isSucesso()) {
                    try {
                        ChamadoCliente chamadoAtualizado = (ChamadoCliente) getRetorno();
                        UtilCRMChat.gerarSalaChamado(chamadoAtualizado);

                    } catch (ErroConexaoServicoChat | ErroRegraDeNEgocioChat ex) {
                        addErro("Falha criando integração do chamado com Matrix");
                        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                        try {
                            ChamadoCliente chamadoReversaoFalhaMatrix = UtilSBPersistencia.loadEntidade(pChamado, em);
                            chamadoReversaoFalhaMatrix.setStatus(FabStatusChamado.RASCUNHO.getRegistro());
                            UtilSBPersistencia.mergeRegistro(chamadoReversaoFalhaMatrix, em);
                        } finally {
                            UtilSBPersistencia.fecharEM(em);
                        }
                    }

                }

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                ChamadoCliente chamado = loadEntidade(pChamado);

                chamado.setStatus(FabStatusChamado.EM_ATENDIMENTO.getRegistro());

                UsuarioCRM usuario = (UsuarioCRM) loadEntidade(SBCore.getUsuarioLogado());
                chamado.setAtendenteResponsavel(usuario);
                atualizarEntidade(chamado);

                String url = getUrlChamadoAcessoCLiente(pChamado);
                String conteudoemail = "Olá, " + chamado.getAtendenteResponsavel().getNome() + " assumiu o chamado " + "Cod." + chamado.getId() + "do dipo " + chamado.getTipoChamado().getNome()
                        + " você pode interajir com este chamado "
                        + "<h1><a href='"
                        + url + "'>CLICANDO AQUI "
                        + "</a> </h1> <br/>"
                        + "O assunto do chamado é: <h3> "
                        + "" + chamado.getDescricao() + "</h3>"
                        + "";
                try {
                    if (ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(usuario, chamado.getUsuarioCliente(),
                            "Chamado do tipo " + chamado.getTipoChamado().getNome() + ", foi assumido por" + chamado.getAtendenteResponsavel().getNome(),
                            conteudoemail)) {

                        addAviso("O email para " + pChamado.getUsuarioCliente().getEmail() + " foi enviado");
                    } else {
                        addAlerta("Falhando enviando email para " + pChamado.getUsuarioCliente().getEmail());
                    }
                } catch (ErroEnvioEmail ex) {
                    addAlerta("Falhando enviando email para " + pChamado.getUsuarioCliente().getEmail());
                }
                if (chamado.getUsuarioCliente().getContatoClienteVinculado().isPossuiTelefone()) {
                    if (FabIntegracaoSMS.ENVIAR_MENSAGEM.getAcao(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(chamado.getUsuarioCliente().getContatoClienteVinculado().getCelular()),
                            "Chamado do tipo " + chamado.getTipoChamado().getNome() + ", foi assumido por" + chamado.getAtendenteResponsavel().getNome() + " para interajir com o chamado acesse: " + url).getResposta().isSucesso()) {
                        addAviso("Um sms foi enviado para " + pChamado.getUsuarioCliente().getContatoClienteVinculado().getCelular());
                    } else {
                        addAlerta("Falhando enviando SMS para " + chamado.getUsuarioCliente().getContatoClienteVinculado().getCelular());
                    }
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

                    try {

                        String urlSiteIntranet = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR, chamadoNotificacao);
                        urlSiteIntranet = urlSiteIntranet.replace("https://crm.", "https://atendimento.");
                        ItfTokenAcessoDinamico token = SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR, chamadoNotificacao, chamadoNotificacao.getUsuarioCliente().getEmail());
                        String urlEMail = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB, token);
                        urlEMail = urlEMail.replace("https://crm.", "https://atendimento.");
                        //

                        String conteudo = SBCore.getUsuarioLogado().getNome() + " fechou o chamado código " + chamadoNotificacao.getId() + "  do tipo " + chamadoNotificacao.getTipoChamado().getNome() + " "
                                + " você pode dar uma olhada nas ultimas interações ou reabri-lo  ";
                        String conteudoEMail = conteudo + " <h1><a href='" + urlEMail + "'> </a> </h1>";

                        UtilCRMChat.notificarSalaAtendimentoGeral(chamadoNotificacao.getUsuarioCliente(), conteudo + " utilizando este link: " + urlSiteIntranet);

                        try {
                            ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(), pChamado.getCliente(), "O chamado Cód " + chamadoNotificacao.getId()
                                    + "Casanova digital foi encerrado", conteudoEMail);
                        } catch (ErroEnvioEmail ex) {
                            addAviso("O chamado foi fechado, mas houve erro notificando");
                        }

                        setProximoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_EM_ATENDIMENTO.getRegistro().getComoFormulario());
                    } catch (ErroNotificacao ex) {

                    }

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

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_CHAMADOS_CTR_DEFINIR_RESPONSAVEL)
    public static ItfRespostaAcaoDoSistema chamadoDefinirResponsavel(final ChamadoCliente pChamado) {
        if (pChamado.getAtendenteResponsavel() != null && pChamado.getAtendenteResponsavel().equals(SBCore.getUsuarioLogado())) {
            return chamadoAssumirResponsavel(pChamado);
        }

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pChamado), pChamado) {

            private UsuarioCRM usuarioNovo;
            private UsuarioCRM usuarioAntigo;
            UsuarioCRM usuarioAcao;
            private ChamadoCliente chamado = loadEntidade(pChamado);
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
                }
                usuarioAcao = (UsuarioCRM) loadEntidade(SBCore.getUsuarioLogado());
            }

            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais();
                EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
                try {
                    chamado = UtilSBPersistencia.loadEntidade(chamado, em);
                    if (isSucesso()) {

                        if (chamado.getAtendenteResponsavel().getCodigoMatrix() != null) {
                            try {
                                String tipoChamado = pChamado.getTipoChamado().getNome();
                                String nomePessoa = chamado.getPessoa().getNome();
                                String nomeUsuarioAceo = usuarioAcao.getNome();
                                servicoChat.enviarDirect(chamado.getAtendenteResponsavel().getCodigoMatrix(), "Olá, " + nomeUsuarioAceo + " transferiu seu chamado " + " de " + nomePessoa
                                        + "COD: " + chamado.getId() + " sobre " + tipoChamado + "para "
                                        + pChamado.getAtendenteResponsavel().getNome());
                            } catch (Throwable t) {
                                System.out.println(t.getMessage());
                            }
                        }

                        if (usuarioNovo.getCodigoMatrix() != null) {
                            try {
                                servicoChat.enviarDirect(pChamado.getAtendenteResponsavel().getCodigoMatrix(), "Olá, " + usuarioNovo.getNome() + " transferiu o chamado " + " de " + chamado.getPessoa().getNome()
                                        + "COD: " + pChamado.getId() + " sobre " + pChamado.getTipoChamado().getNome() + " de " + usuarioNovo.getNome() + " para você");
                            } catch (Throwable t) {
                                System.out.println(t.getMessage());
                            }
                        }
                    }
                    String chamadoTexto = "O Chamado cod " + pChamado.getId() + " foi assumido por " + pChamado.getAtendenteResponsavel().getNome() + " para interagir com o chamado acesse: ";
                    String linkCLiente = getUrlChamadoAcessoCLiente(pChamado);
                    String linkChamadoSMS = linkCLiente;
                    String linkcChamadoEMail = "<h1>" + ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().gerarTagLink("ESTE LINK", linkCLiente) + "</h1>";

                    String conteudoemail;

                    conteudoemail = chamadoTexto + linkcChamadoEMail;

                    if (chamado.getUsuarioCliente().getContatoClienteVinculado().isPossuiTelefone()) {
                        if (FabIntegracaoSMS.ENVIAR_MENSAGEM.getAcao(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(chamado.getUsuarioCliente().getContatoClienteVinculado().getCelular()),
                                chamadoTexto + linkChamadoSMS).getResposta().isSucesso()) {
                            addAlerta("Um sms foi enviado para " + pChamado.getUsuarioCliente().getContatoClienteVinculado().getCelular());
                        }
                    }
                    try {
                        if (chamado.getUsuarioCliente().getContatoClienteVinculado().isPossuiEmail()) {
                            if (ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(),
                                    chamado.getUsuarioCliente().getContatoClienteVinculado(), "Chamado do tipo " + chamado.getTipoChamado().getNome() + ", foi assumido por" + chamado.getAtendenteResponsavel().getNome(),
                                    conteudoemail)) {
                                addAlerta("O email para " + pChamado.getUsuarioCliente().getEmail() + " foi enviado");
                            }
                        }
                    } catch (ErroEnvioEmail ex) {
                        Logger.getLogger(ModuloCRMAtendimentoChamado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } finally {
                    UtilSBPersistencia.fecharEM(em);
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

                chamado.setAtendenteResponsavel(usuarioNovo);
                chamado.setStatus(FabStatusChamado.EM_ATENDIMENTO.getRegistro());
                chamado = atualizarEntidade(chamado);
                EventoChamado novoEvento = new EventoChamado();
                novoEvento.setAgente((UsuarioCRM) SBCore.getUsuarioLogado());
                novoEvento.setDescricao(SBCore.getUsuarioLogado().getNome() + " definiu " + chamado.getAtendenteResponsavel().getNome() + " como responsável");
                novoEvento.setChamado(chamado);
                novoEvento.setDataHora(new Date());
                atualizarEntidade(novoEvento);
                setProximoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_TODOS_STATUS.getRegistro().getComoFormulario());
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
