/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.moduloWeb;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.coletivoJava.integracoes.amazonSMS.FabIntegracaoSMS;
import br.org.carameloCode.erp.modulo.crm.api.email.ErroEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.FabAcaoAcessoAnonimoIntranet;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.FabAcaoCrmAtendimentoAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.InfoAcaoCRMAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.marketing.Util.UtilCRCEmailAvancado;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtilTelefone;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.Date;
import jersey.repackaged.com.google.common.collect.Lists;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.FabAcaoAgendaMentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.InfoAcaoAgendamentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.ModuloAgendamentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesqHorarioPublicado;
import org.coletivojava.fw.utilCoreBase.UtilCRCComunicacao;
import org.json.simple.JSONObject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author sfurbino
 */
public class ModuloCRMAcoesWeb extends ControllerAbstratoSBPersistencia {

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_ABRIR_CHAT_WHATSAPP)
    public static ItfRespostaAcaoDoSistema contatoAbrirWhatsapp(final ContatoProspecto pContato) {

        String numerointernacional = (String) pContato.getCPinst(CPContatoProspecto.celularformatointernacional).getValor();
        if (numerointernacional == null) {
            SBCore.enviarAvisoAoUsuario("O número não é compatível com o formato internacional");
        }

        if (numerointernacional != null) {
            PrimeFaces.current().executeScript("window.open(\"https://api.whatsapp.com/send?phone=" + numerointernacional + "\");");
        }

        return getNovaResposta(ContatoProspecto.class).addAviso("O chat foi Aberto").setProximoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO.getRegistro().getComoFormulario());
    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_LIGAR_PELO_CELULAR)
    public static ItfRespostaAcaoDoSistema contatoLigarCelular(final ContatoProspecto pContato) {

        String numerointernacional = (String) pContato.getCPinst(CPContatoProspecto.celularformatointernacional).getValor();
        if (numerointernacional == null) {
            SBCore.enviarAvisoAoUsuario("O número não é compatível com o formato internacional");
        }
        if (numerointernacional != null) {
            PrimeFaces.current().executeScript("window.location.href=\"tel:" + numerointernacional + "\";");
        }

        return getNovaResposta(ContatoProspecto.class).addAviso("O chat foi Aberto").setProximoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO.getRegistro().getComoFormulario());
    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_LIGAR_PABX_ONLINE)
    public static ItfRespostaAcaoDoSistema contatoLigarPABX(final ContatoProspecto pContato) {

        String numerointernacional = (String) pContato.getCPinst(CPContatoProspecto.celularformatointernacional).getValor();
        if (numerointernacional == null) {
            SBCore.enviarAvisoAoUsuario("O número não é compatível com o formato internacional");
        } else {
            SBCore.enviarAvisoAoUsuario("Isto está em desenvolvimento, utilize o aplicativo do PABX online do seu celular");
        }

        return contatoLigarCelular(pContato);
    }

    @InfoAcaoAgendamentoPublico(acao = FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_CTR_ATIVAR)
    public static ItfRespostaAcaoDoSistema escopoPublicoAtivar(final EscopoPesqHorarioPublicado pEscopo) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(ModuloAgendamentoPublico.escopoPublicoSalvarMerge(pEscopo), pEscopo) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                EscopoPesqHorarioPublicado escopo = loadEntidade(pEscopo);
                if (escopo.getTipoEscopo() == null) {
                    throw new ErroRegraDeNegocio("Defina o tipo de agendamento");
                }
                if (escopo.getAtendentes() == null) {
                    throw new ErroRegraDeNegocio("Defina os atendentes disponíveis");
                }
                if (pEscopo.getDataHoraTokenPublicoExpira() == null) {
                    throw new ErroRegraDeNegocio("Defina a data de expiração do token");
                }
                if (UtilCRCDataHora.intervaloTempoHoras(new Date(), escopo.getDataHoraTokenPublicoExpira()) < 5) {
                    throw new ErroRegraDeNegocio("O token precisa ter uma validade mínima de 5 horas");
                }

                TokenAcessoDinamico token = (TokenAcessoDinamico) SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoAcessoAnonimoIntranet.RESERVA_PUBLICA_FRM_LISTAR_HORARIOS,
                        pEscopo, null);
                token.setValidade(pEscopo.getDataHoraTokenPublicoExpira());
                atualizarEntidade(token);
                escopo.setTokenPublicado(token);
                escopo.setPublicado(true);

                String url = MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro(), token);
                url = url.replace("crm.", "atendimento.");
                escopo.setLinkDeAcesso(url);
                atualizarEntidade(escopo);
            }
        }.getResposta();
    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_PRIMEIRO_ACESSO_POR_EMAIL)
    public static ItfRespostaAcaoDoSistema contatoPrimeiroAcessoEmail(final ContatoProspecto pContato) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pContato), pContato) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pContato.getEmail() == null) {
                    throw new ErroRegraDeNegocio("Este contato precisa de um e-mail para usar na criação de token para primeiro acesso");
                }
                TokenAcessoDinamico token = (TokenAcessoDinamico) SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_CLIENTE_CADASTRAR_SENHA_PRIMEIRO_ACESSO,
                        pContato, pContato.getEmail());
                String url = MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro(), token);
                url = url.replace("crm.", "atendimento.");
                UsuarioCRM usuario = (UsuarioCRM) SBCore.getUsuarioLogado();
                String conteudo = "Olá, você conhece o canal <a href='" + url + "'>  atendimento.casanovadigital.com.br </a>? "
                        + "<br/> Utilize este endereço para criar chamados e marcar reuniões direto na minha agenda, <br/> "
                        + "você pode efetuar seu primeiro acesso pelo link: <h1> <a href='" + url + "' > LINK PRIMEIRO ACESSO RÁPIDO </a></h1>";
                JSONObject destino = UtilCRCEmailAvancado.getJsonFromListaContato(Lists.newArrayList(pContato));
                try {
                    ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto()
                            .enviarEMailAplicandoModeloAssinatura(usuario, pContato, "Acesse nossa área do cliente", conteudo);

                } catch (ErroEnvioEmail ex) {
                    throw new ErroRegraDeNegocio("Houve uma falha Enviando o e-mail");
                }
            }
        }.getResposta();
    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_PRIMEIRO_ACESSO_POR_WHATSAPP)
    public static ItfRespostaAcaoDoSistema contatoPrimeiroAcessoWtzp(final ContatoProspecto pContato) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pContato), pContato) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pContato.getCelular() == null) {
                    throw new ErroRegraDeNegocio("Este contato precisa de um e-mail para usar na criação de token para primeiro acesso");
                }
                TokenAcessoDinamico token = (TokenAcessoDinamico) SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_CLIENTE_CADASTRAR_SENHA_PRIMEIRO_ACESSO,
                        pContato, pContato.getEmail());
                String url = MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro(), token);
                url = url.replace("crm.", "atendimento.");
                String conteudo = UtilCRCComunicacao.getSaudacao() + ", " + pContato.getNome() + ", através deste link, você pode criar chamados e marcar reuniões direto na minha agenda: " + url + "  ";
                String javascript = new PgUtilTelefone().gerarJavascriptWhatsqpp(pContato.getCelular(), conteudo);
                UtilSBWP_JSFTools.executarJavaScript(javascript);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_AGENDA_POR_EMAIL)
    public static ItfRespostaAcaoDoSistema contatoAcessoAgendaEmail(final ContatoProspecto pContato) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pContato), pContato) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pContato.getEmail() == null) {
                    throw new ErroRegraDeNegocio("Este contato precisa de um e-mail para usar na criação de token para primeiro acesso");
                }
                TokenAcessoDinamico token = (TokenAcessoDinamico) SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.RESERVAS_FRM_HORARIOS_DISPONIVEIS,
                        pContato, pContato.getEmail());
                String url = MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro(), token);
                url = url.replace("crm.", "atendimento.");

                String conteudo = "<h1> Olá, utilize este link para acessar minha agenda com um click <br/> "
                        + "<h1> <a href='" + url + "' > ACESSO RÁPIDO PARA MINHA AGENDA </a> <br/><br/>";
                UsuarioCRM usuarioLogado = (UsuarioCRM) loadEntidade(SBCore.getUsuarioLogado());

                try {
                    ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto()
                            .enviarEMailAplicandoModeloAssinatura(usuarioLogado, pContato, "Acesse nossa área do cliente", conteudo);
                } catch (ErroEnvioEmail ex) {
                    throw new ErroRegraDeNegocio("Houve uma falha Enviando o e-mail");
                }
            }
        }.getResposta();
    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_AGENDA_POR_WHATSAPP)
    public static ItfRespostaAcaoDoSistema contatoAcessoAgendaWtzp(final ContatoProspecto pContato) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pContato), pContato) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pContato.getCelular() == null) {
                    throw new ErroRegraDeNegocio("Este contato precisa de um e-mail para usar na criação de token para primeiro acesso");
                }
                TokenAcessoDinamico token = (TokenAcessoDinamico) SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.RESERVAS_FRM_HORARIOS_DISPONIVEIS,
                        pContato, pContato.getEmail());
                String url = MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro(), token);
                url = url.replace("crm.", "atendimento.");
                addAviso(url);
                String conteudo = UtilCRCComunicacao.getSaudacao() + ", " + pContato.getNome() + ", através deste link, você pode agendar uma reunião direto na minha agenda " + url;
                String javascript = new PgUtilTelefone().gerarJavascriptWhatsqpp(pContato.getCelular(), conteudo);
                UtilSBWP_JSFTools.executarJavaScript(javascript);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_ACESSO_POR_EMAIL)
    public static ItfRespostaAcaoDoSistema contatoAcessoEmail(final ContatoProspecto pContato) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pContato), pContato) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pContato.getEmail() == null) {
                    throw new ErroRegraDeNegocio("Este contato precisa de um e-mail para usar na criação de token para primeiro acesso");
                }
                TokenAcessoDinamico token = (TokenAcessoDinamico) SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO,
                        pContato, pContato.getEmail());
                String url = MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro(), token);
                url = url.replace("crm.", "atendimento.");

                String conteudo = "<h1> Olá, utilize este link para acessar rapidamente nosso painel de atendimento em https://atendimento.casanovadigital.com.br <br/> "
                        + "<h1> <a href='" + url + "' > LINK DE ACESSO RÁPIDO </a> <br/><br/>"
                        + "Você também pode acessar o painel a qualquer momento, utilizando sua senha de acesso, caso tenha esquecido sua senha atrabés do botão esqueci a senha você pode criar uma nova senha "
                        + "";
                addAviso(url);
                UsuarioCRM usuarioLogado = (UsuarioCRM) loadEntidade(SBCore.getUsuarioLogado());

                try {
                    ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto()
                            .enviarEMailAplicandoModeloAssinatura(usuarioLogado, pContato, "Acesse nossa área do cliente", conteudo);
                } catch (ErroEnvioEmail ex) {
                    throw new ErroRegraDeNegocio("Houve uma falha Enviando o e-mail");
                }
            }
        }.getResposta();
    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_ACESSO_POR_WHATSAPP)
    public static ItfRespostaAcaoDoSistema contatoAcessoWtzp(final ContatoProspecto pContato) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pContato), pContato) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pContato.getCelular() == null) {
                    throw new ErroRegraDeNegocio("Este contato precisa de um e-mail para usar na criação de token para primeiro acesso");
                }
                TokenAcessoDinamico token = (TokenAcessoDinamico) SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO,
                        pContato, pContato.getEmail());
                String url = MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro(), token);
                url = url.replace("crm.", "atendimento.");
                String conteudo = UtilCRCComunicacao.getSaudacao() + ", " + pContato.getNome() + ", através deste link, você pode criar chamados e marcar reuniões direto na minha agenda: " + url + "  ";
                String javascript = new PgUtilTelefone().gerarJavascriptWhatsqpp(pContato.getCelular(), conteudo);
                addAviso(url);

                UtilSBWP_JSFTools.executarJavaScript(javascript);

            }
        }.getResposta();
    }

    @InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_PARA_CHAT)
    public static ItfRespostaAcaoDoSistema contatoConvidarParaCHat(ContatoProspecto pContato) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pContato), pContato) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ContatoProspecto ct = loadEntidade(pContato);
                ct.getCPinst("usuarioVinculado").getValor();
                ItfTokenAcessoDinamico token = SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.CONVERSA_FRM_CHAT, ct.getUsuarioVinculado(), ct.getUsuarioVinculado().getEmail());
                String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB, token);
                url = url.replace("crm", "atendimento");
                String texto = SBCore.getUsuarioLogado().getNome() + "da Casanova digital, convida você para conversar via " + url;
                if (ct.isPossuiTelefone()) {
                    if (FabIntegracaoSMS.ENVIAR_MENSAGEM.getAcao(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(ct.getCelular()), texto).getResposta().isSucesso()) {
                        addAviso("Foi notificado por sms");
                    } else {
                        addAlerta("Falha notificando via sms");
                    }
                }
                if (ct.isPossuiEmail()) {
                    String conteudoEmail = "Oi, precisamos conversar, segue o link para nosso canal de comunicação <h1><a href='" + url + "' >ABRIR CANAL </a> </h1>";
                    try {
                        if (ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto()
                                .enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(), ct, "Link para conversa no canal exclusivo", conteudoEmail)) {

                            addAviso("Foi notificado por email");
                        } else {
                            addAlerta("Falha notificando via email");
                        }
                    } catch (ErroEnvioEmail ex) {
                        addAlerta("Falha notificando via email");
                    }
                }
                setUrlDestinoSucesso(MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO.getRegistro(), ct.getUsuarioVinculado().getRepresentanteLegal()));

            }
        }.getResposta();
    }

}
