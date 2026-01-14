/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.loginSocial;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioConvidado.UsuarioConvidado;
import com.super_bits.integracoes.modelController.socialAutenticador.FabAcaoSocialAutenticador;
import com.super_bits.integracoes.modelController.socialAutenticador.FabTipoAutenticacaoSocial;
import com.super_bits.integracoes.modelController.socialAutenticador.InfoAcaoSocialAutenticador;
import com.super_bits.integracoes.modelController.socialAutenticador.TipoCredencialSocial;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoComGestaoEntityManager;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaSession;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.util.SocialAuthUtil;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author SalvioF
 */
@ViewScoped
@InfoAcaoSocialAutenticador(acao = FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_MB)
@Named
@InfoPagina(nomeCurto = "AutSoc", tags = {"Autenticador Social"})
public class PgAutendicadorSocial extends MB_PaginaSession {

    private String redeSocialSelecionada;

    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.OBJETO_COM_CONSTRUCTOR,
            tipoEntidade = TipoCredencialSocial.class,
            fabricaObjetosRelacionada = FabTipoAutenticacaoSocial.class,
            nome = "tipoAutenticador", obrigatorio = false)
    private ParametroURL prRedeSelecionada;

    private Profile profile;
    private String paginaSucesso;

    @Inject
    private LoginSocialColetivoJava loginSocial;

    @Inject
    private SessaoSocialManager sessaoLoginSocial;

    private String urlExterna;

    private AuthProvider provider;

    @Override
    public void executarAcaoSelecionada() {
        if (!acaoSelecionada.isUmaAcaoController()) {
            super.executarAcaoSelecionada(); //To change body of generated methods, choose Tools | Templates.
        }
        FabAcaoSocialAutenticador acao = (FabAcaoSocialAutenticador) getEnumAcaoAtual();
        if (acao == null) {
            return;
        }
        switch (acao) {

            case AUTENTICACAO_SOCIAL_FRM_LISTAR_OPCOES:
                provider = null;
                profile = null;
                break;
            case AUTENTICACAO_SOCIAL_FRM_AUTENTICADO_SUCESSO:

                break;

            case AUTENTICACAO_SOCIAL_CTR_CRIAR_CADASTRO: {
                try {
                    Profile profile = sessaoLoginSocial.getManager().getProvider(getTipoAutenticacao().getNome()).getUserProfile();
                    if (profile == null) {
                        executaAcaoSelecionadaPorEnum(FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_FRM_AUTENTICAR_EXTERNO);
                    } else {
                        String email = profile.getEmail();
                        ComoUsuario usuario = SBCore.getServicoPermissao().getUsuarioByEmail(email);
                        if (usuario == null) {
                            UsuarioConvidado novoUsuarioConvidado = new UsuarioConvidado();
                            novoUsuarioConvidado.setEmail(profile.getEmail());
                            novoUsuarioConvidado.setNome(profile.getFullName());
                            novoUsuarioConvidado.setApelido(profile.getFirstName());
                            novoUsuarioConvidado.setGrupo(FabGruposIntranetCasaNova.CRM_CONVIDADO.getRegistro());
                            novoUsuarioConvidado = UtilSBPersistencia.mergeRegistro(novoUsuarioConvidado, getEMPagina());
                            SBCore.getServicoSessao().getSessaoAtual().setUsuario(novoUsuarioConvidado);
                            getPaginaUtil().irParaURL(usuario.getGrupo().getPaginaInicial().getRegistro());
                        } else {
                            SBCore.getServicoSessao().getSessaoAtual().setUsuario(usuario);
                            getPaginaUtil().irParaURL(usuario.getGrupo().getPaginaInicial().getRegistro());
                        }

                    }
                } catch (Exception ex) {
                    Logger.getLogger(PgAutendicadorSocial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case AUTENTICACAO_SOCIAL_CTR_ATUALIZAR_CADASTRO:
                if (SBCore.getControleDeSessao().getSessaoAtual().isIdentificado() && profile != null) {

                    new ExecucaoComGestaoEntityManager() {
                        @Override
                        public void regraDeNegocio() {
                            try {
                                UsuarioSB usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
                                usuario.setEmail(profile.getEmail());
                                usuario.setNome(profile.getFullName());
                                usuario.setApelido(profile.getDisplayName());

                                InputStream input = new URL(profile.getProfileImageURL()).openStream();
                                usuario.uploadFotoTamanhoPequeno(input);
                                atualizarEntidade(usuario);
                            } catch (ErroEmBancoDeDados t) {
                                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro salvando configurações do usuário", t);
                            } catch (MalformedURLException ex) {
                                Logger.getLogger(PgAutendicadorSocial.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(PgAutendicadorSocial.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                }
                renovarEMPagina();
                break;
            case AUTENTICACAO_SOCIAL_CTR_logar:
                try {

                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    HttpServletRequest request = (HttpServletRequest) context.getRequest();
                    Map<String, String> parametros = SocialAuthUtil.getRequestParametersMap(request);
                    //     sessaoLoginSocial.getManager().createAccessGrant(getRedeSocialSelecionada(), parametros, sessaoLoginSocial.getPaginaSucesso());
                    sessaoLoginSocial.getManager().connect(parametros);
                    Profile usuarioIdentificado = sessaoLoginSocial.getManager().getProvider(getTipoAutenticacao().getNome()).getUserProfile();
                    if (usuarioIdentificado != null) {
                        String email = usuarioIdentificado.getEmail();
                        ComoUsuario usuario = SBCore.getServicoPermissao().getUsuarioByEmail(email);
                        if (usuario != null) {
                            SBCore.getServicoSessao().getSessaoAtual().setUsuario(usuario);
                            getPaginaUtil().irParaURL(usuario.getGrupo().getPaginaInicial().getRegistro());
                        } else {
                            executaAcaoSelecionadaPorEnum(FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_CTR_CRIAR_CADASTRO);
                        }
                    }
                    //    provider = sessaoLoginSocial.getManager().connect(SocialAuthUtil.getRequestParametersMap(request));
                    // profile = provider.getUserProfile();
                    if (profile != null) {
                        executaAcaoSelecionadaPorEnum(FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_CTR_CRIAR_CADASTRO);
                    }
                } catch (Throwable t) {
                    executaAcaoSelecionadaPorEnum(FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_FRM_AUTENTICAR_EXTERNO);

                }
                break;

            case AUTENTICACAO_SOCIAL_FRM_AUTENTICAR_EXTERNO:
                try {

                    FabTipoAutenticacaoSocial tipo = FabTipoAutenticacaoSocial.getFabricaPorString(getRedeSocialSelecionada());
                    switch (tipo) {
                        case FACEBOOK:
                        case GOOGLE:
                        case GITHUB:

                            //   provider = sessaoLoginSocial.getManager().getProvider(redeSocialSelecionada);
                            String url = sessaoLoginSocial.getManager().getAuthenticationUrl(getTipoAutenticacao().getNome(), loginSocial.getUrlReceberCodigoSolicitacao(getTipoAutenticacao()));
                            //         String url = sessaoLoginSocial.getManager().getAuthenticationUrl(redeSocialSelecionada, sessaoLoginSocial.getPaginaSucesso());

                            if (url != null) {
                                //   executaAcaoSelecionadaPorEnum(FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_FRM_AUTENTICADO_SUCESSO);
                                urlExterna = url;
                                getPaginaUtil().irParaURL(url);

                                //       FacesContext.getCurrentInstance().getExternalContext().redirect(url);
                            }

                            break;

                    }
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro verificando Login", t);

                }
                break;
            default:
                throw new AssertionError(acao.name());

        }

    }

    public Profile getProfile() {
        try {
            if (profile == null) {

                return profile;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo perfil", t);
            executaAcaoSelecionadaPorEnum(FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_FRM_LISTAR_OPCOES);
            return null;
        }
        return profile;
    }

    @PostConstruct
    public void inicio() {

        try {
            paginaSucesso = sessaoLoginSocial.getPaginaSucesso();

            if (profile == null) {
                executaAcaoSelecionadaPorEnum(FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_FRM_LISTAR_OPCOES);
            }
        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Inicializando Conexão Social", e);
        }

    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return null;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {

    }

    public List<TipoCredencialSocial> getCredenciais() {
        return sessaoLoginSocial.getCredenciais();
    }

    public String getRedeSocialSelecionada() {
        if (redeSocialSelecionada == null && getParametroInstanciado(prRedeSelecionada).isValorDoParametroFoiConfigurado()) {
            TipoCredencialSocial tipoCredencial = (TipoCredencialSocial) getParametroInstanciado(prRedeSelecionada).getValor();
            redeSocialSelecionada = tipoCredencial.getNome();
        }
        return redeSocialSelecionada;
    }

    public void setRedeSocialSelecionada(String redeSocialSelecionada) {
        this.redeSocialSelecionada = redeSocialSelecionada;
    }

    public String getUrlExterna() {
        return urlExterna;
    }

    public ComoAcaoDoSistema getAcaoLogar() {
        return FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_CTR_logar.getRegistro();
    }

    public TipoCredencialSocial getTipoAutenticacao() {
        return (TipoCredencialSocial) getParametroInstanciado(prRedeSelecionada).getValor();
    }

}
