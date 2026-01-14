/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmPublico;

import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.GrupoUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.FabUsuarioPadraoMarketingParaWeb;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.FabAcaoAcessoAnonimoIntranet;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.InfoAcaoaAcessoAnonimoCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.ModuloCRMCliente;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto.ValorLogicoContatoProspectoUsuarioVinculado;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servlets.servletWebPaginas.EstruturaDeFormulario;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCCriptrografia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
@Named
@InfoPagina(nomeCurto = "GPL", tags = {"Pagina Inicial"})
@ViewScoped
@InfoAcaoaAcessoAnonimoCRM(acao = FabAcaoAcessoAnonimoIntranet.LOGIN_MB_GERENCIAR)
public class PgPaginaInicial extends MB_PaginaConversation {

    @Inject
    private ControleDeSessaoWeb sessao;

    private String emailOuTelefone;

    private UsuarioCrmCliente usuarioemLogin;

    @InfoParametroURL(nome = "ContatoSelecionado", tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false, tipoEntidade = ContatoProspecto.class)
    private ParametroURL pPararametroContatoPrimeiroAcesso;

    public String getEnderecoTeste() {
        String url = UtilSBWPServletTools.getSessaoAtual().getUrlHostDaSessao();
        System.out.println("URL encontrada=" + url);

        return url;
    }

    @PostConstruct
    public void init() {
        try {
            if (xhtmlAcaoAtual == null) {
                xhtmlAcaoAtual = FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_REALIZAR_LOGIN.getRegistro().getComoFormularioEntidade().getXhtml();
            }
            HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url = origRequest.getRequestURL().toString();
            if (url.startsWith("https://atendimento") || url.contains("atendimento.")) {
                xhtmlAcaoAtual = FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_REALIZAR_LOGIN_DO_CLIENTE.getRegistro().getComoFormulario().getXhtml();
                setAcaoSelecionada(FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_REALIZAR_LOGIN_DO_CLIENTE.getRegistro());

            } else {

                if (SBCore.getControleDeSessao().getSessaoAtual().isIdentificado() && !SBCore.getUsuarioLogado().equals(FabUsuarioPadraoMarketingParaWeb.USUAIRO_CONVIDADO.getRegistro())) {
                    GrupoUsuarioCRM grupoATualizado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado().getGrupo(), getEMPagina());
                    EstruturaDeFormulario est = MapaDeFormularios.getEstruturaByXHTMLDeGestao(grupoATualizado.getPaginaInicial().getRegistro().getComoFormulario().getXhtml());
                    getPaginaUtil().irParaURL(est.getUrlPadrao());
                }
                if (SBCore.getUsuarioLogado().equals(FabUsuarioPadraoMarketingParaWeb.USUAIRO_CONVIDADO.getRegistro())) {
                    MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_MB_PADRAO.getRegistro());
                }
            }
            if (getParametroInstanciado(pPararametroContatoPrimeiroAcesso).isValorDoParametroFoiConfigurado()) {
                ContatoProspecto contatoParametro = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(pPararametroContatoPrimeiroAcesso).getValor(), getEMPagina());
                if (contatoParametro.getUsuarioVinculado() == null) {
                    boolean tokenAtivoExistente = SBCore.getServicoPermissao().isTokenDinamicoExiste(FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_CLIENTE_CADASTRAR_SENHA_PRIMEIRO_ACESSO, contatoParametro, contatoParametro.getEmail());
                    if (!tokenAtivoExistente) {
                        SBCore.enviarAvisoAoUsuario("Nenhum token ativo foi encontrado, solicite um token para seu consultor, ou clique no botão Meu Primeiro Acesso");
                        executaAcaoSelecionadaPorEnum(FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_REALIZAR_LOGIN_DO_CLIENTE);
                    } else {
                        defineContatoEncontrado(contatoParametro);
                    }
                } else {
                    executaAcaoSelecionadaPorEnum(FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_REALIZAR_LOGIN_DO_CLIENTE);
                    SBCore.enviarMensagemUsuario("Olá, " + contatoParametro.getNome() + ", você já tem um usuário registrado, utilize a opção recuperar senha para alterar sua senha. ", FabMensagens.AVISO);
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro inicializando pagina inicial" + t.getMessage(), t);
        }

    }

    public ControleDeSessaoWeb getSessao() {
        return sessao;
    }

    public void setSessao(ControleDeSessaoWeb sessao) {
        this.sessao = sessao;
    }

    @Override
    protected String defineTitulo() {
        return "Intranet Casa Nova Digital - Home";
    }

    @Override
    protected String defineNomeLink() {
        return "HOME";
    }

    @Override
    protected String defineDescricao() {
        return "Pagina Inicial IntranetCasaNovaDigital";
    }

    @Override
    public Long getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAcaoSelecionada(ComoAcaoDoSistema acaoSelecionada) {
        super.setAcaoSelecionada(acaoSelecionada); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getEmailOuTelefone() {
        return emailOuTelefone;
    }

    public void setEmailOuTelefone(String emailOuTelefone) {
        this.emailOuTelefone = emailOuTelefone;
    }

    public UsuarioCrmCliente getUsuarioemLogin() {
        return usuarioemLogin;
    }

    public void encontrarUsuario() {
        StringBuilder queryDadosCliente = new StringBuilder();

        boolean pesquisaenviada = (emailOuTelefone != null && emailOuTelefone.length() > 7);
        ContatoProspecto contatoEncontrado = null;
        String parametroPesquisa = "";
        if (pesquisaenviada) {

            if (emailOuTelefone.contains("@")) {
                queryDadosCliente.append(" from ContatoProspecto where email = :pParametro");
                parametroPesquisa = emailOuTelefone;
            } else {
                if (emailOuTelefone.length() > 7) {
                    queryDadosCliente.append(" from ContatoProspecto where celular like :pParametro");
                    parametroPesquisa = "%" + emailOuTelefone;
                }
            }

            try {
                Query consulta = getEMPagina().createQuery(queryDadosCliente.toString(), ContatoProspecto.class);
                consulta.setParameter("pParametro", parametroPesquisa);
                contatoEncontrado = (ContatoProspecto) consulta.getSingleResult();
            } catch (NoResultException t) {
                usuarioemLogin = null;
                SBCore.enviarAvisoAoUsuario("Dados do usuário não encontrados no sistema, entre em contato com seu consultor");
            } catch (NonUniqueResultException naoUnici) {
                SBCore.enviarMensagemUsuario("Seu usuário está registrado para diversas empresas.", FabMensagens.ALERTA);
                SBCore.enviarAvisoAoUsuario("Por favor, entre em contato com nosso atendimento.");
            }
        } else {
            SBCore.enviarAvisoAoUsuario("Informe um e-mail ou telefone");
        }
        if (contatoEncontrado != null) {
            defineContatoEncontrado(contatoEncontrado);
        }

    }

    private void defineContatoEncontrado(ContatoProspecto pContato) {
        usuarioemLogin = (UsuarioCrmCliente) pContato.getCampoInstanciadoByNomeOuAnotacao(CPContatoProspecto.usuariovinculado).getValor();
        if (usuarioemLogin.getSenha() == null || usuarioemLogin.getSenha().isEmpty() || confereSenha(ValorLogicoContatoProspectoUsuarioVinculado.VALOR_PADRAO_SENHA, usuarioemLogin.getSenha())) {
            setAcaoSelecionada(FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_CLIENTE_CADASTRAR_SENHA_PRIMEIRO_ACESSO.getRegistro());
            xhtmlAcaoAtual = FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_CLIENTE_CADASTRAR_SENHA_PRIMEIRO_ACESSO.getRegistro().getComoFormulario().getXhtml();
            atualizarIdAreaExibicaoAcaoSelecionada();
        } else {
            setAcaoSelecionada(FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_REALIZAR_PRIMEIRO_ACESSO_PELO_EMAIL.getRegistro());
            sessao.setUsuarioLogar(usuarioemLogin.getEmail());
            sessao.esqueceuaSenha();
            xhtmlAcaoAtual = FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_REALIZAR_PRIMEIRO_ACESSO_PELO_EMAIL.getRegistro().getComoFormulario().getXhtml();
            usuarioemLogin = null;
            SBCore.enviarAvisoAoUsuario("Este usuário já tem senha definida, as instruções para alterar a senha foram enviadas para seu e-mail");

        }
    }

    public void cadastrarnovasenha() {
        ItfRespostaAcaoDoSistema resposta = ModuloCRMCliente.cadastrarNovaSenhaUsuario(usuarioemLogin).dispararMensagens();

        if (resposta.isSucesso()) {
            SBCore.getServicoSessao().logarEmailESenha(usuarioemLogin.getEmail(), usuarioemLogin.getSenha());
            UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO.getRegistro()));
        }
    }

    private boolean confereSenha(String senhaEnviada, String senhaArmazenada) {
        if (senhaArmazenada == null || senhaEnviada == null) {
            return false;
        }
        if (senhaArmazenada.length() > 40) {
            return UtilCRCCriptrografia.checarCriptografiaTextoSimetricoSaltAleatorio(senhaEnviada, senhaArmazenada);
        } else {
            return senhaArmazenada.equals(senhaEnviada);
        }
    }
}
