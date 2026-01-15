/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalEmail;
import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.CPEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.ComparadorEmailCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.EmailCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal.CaixaPostal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.categoriaMailRecebido.CategoriaEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.categoriaMailRecebido.FabCategoriaEmailRecebido;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.categoriaMailRecebido.FabCategoriaEmailRecebido.DESCLASSIFICADO;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.categoriaMailRecebido.FabCategoriaEmailRecebido.EMAIL_PRIVADO;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.categoriaMailRecebido.FabCategoriaEmailRecebido.EMAIL_PROSPECTO;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.FabStatusEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoColaborador.ContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoal.ContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_CTR_ATRIBUIR_PESSSOA;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_CTR_ATRIBUIR_PRIVADO;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_CTR_RELATAR_SPAN;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_FRM_EMAILS_DO_PROSPECTO;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_FRM_ENCAMINHAR;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_ENVIADOS;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_RASCUNHOS;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_RECEBIDO;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_ULTMOS_EMAILS;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_FRM_NOVO;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_FRM_RESPONDER;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_FRM_VISUALIZAR_EMAIL;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_MB_GESTAO;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmEmail.ModuloCRMAtendimentoEmail;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCEmailObjetos;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.FabTipoPesquisaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author SalvioF
 */
@ViewScoped
@InfoPagina(nomeCurto = "AdminMail", tags = {"Administração de Emails"})
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EMAILS_MB_GESTAO)
@Named
public class PgEmails extends MB_paginaCadastroEntidades<EmailCrm> implements ItfPaginaComModalEmail, ItfPaginaComModalProspecto {

    @InfoParametroURL(nome = "Prospecto selecionado", tipoParametro = TIPO_PARTE_URL.ENTIDADE,
            tipoEntidade = PessoaJuridica.class, obrigatorio = false)
    private ParametroURL parametroProspectoSelecionado;
    @InfoParametroURL(nome = "Email recebido referência", tipoParametro = TIPO_PARTE_URL.ENTIDADE,
            tipoEntidade = EmailRecebido.class, obrigatorio = false)
    private ParametroURL parametroEmailRecebidoReferencia;

    @InfoParametroURL(nome = "Email enviado referência", tipoParametro = TIPO_PARTE_URL.ENTIDADE,
            tipoEntidade = EnvioEmail.class, obrigatorio = false)
    private ParametroURL parametroEmailEnviadoReferencia;

    @InfoParametroURL(nome = "Email Atividade enviado referência", tipoParametro = TIPO_PARTE_URL.ENTIDADE,
            tipoEntidade = EnvioEmailAtividade.class, obrigatorio = false)
    private ParametroURL parametroEmailAtiviadeEnviadoReferencia;

    @InfoParametroURL(nome = "Contato referência", tipoParametro = TIPO_PARTE_URL.ENTIDADE,
            tipoEntidade = ContatoProspecto.class, obrigatorio = false)
    private ParametroURL parametroContatoPessoa;

    private String termoPesquisa;

    private final Comparator<EmailCrm> ordenadorPadraoEmail;

    private int indiceExibicaoEmailRecebido;

    private CategoriaEmailRecebido categoriaSelecionada;

    public PgEmails() {
        super();
        ordenadorPadraoEmail = (EmailCrm o1, EmailCrm o2) -> {
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return 2;
            }
            if (o1 instanceof EmailRecebido && o2 instanceof EmailRecebido) {
                return (o1.getComoEmailRecebido().getDataHoraRecebimentoServerMail().getTime() < o2.getComoEmailRecebido().getDataHoraRecebimentoServerMail().getTime() ? 1
                        : (o1.getComoEmailRecebido().getDataHoraRecebimentoServerMail().getTime() == o2.getComoEmailRecebido().getDataHoraRecebimentoServerMail().getTime() ? 0 : -1));
            }
            if (o1 instanceof EnvioEmail) {

            }
            if (o1.getDataHoraEmailArmazenado() != null && o2.getDataHoraEmailArmazenado() != null) {
                return (o1.getDataHoraEmailArmazenado().getTime() < o2.getDataHoraEmailArmazenado().getTime() ? 1
                        : (o1.getDataHoraEmailArmazenado().getTime() == o2.getDataHoraEmailArmazenado().getTime() ? 0 : -1));
            }
            return (o1.getId() < o2.getId() ? 1
                    : (o1.getId() == o2.getId() ? 0 : -1));
        };

    }

    public void abrirEmail() {

        String urlAtividade = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.EMAILS_FRM_VISUALIZAR_EMAIL.getRegistro(), getEntidadeSelecionada());
        getPaginaUtil().irParaURL(urlAtividade);
    }

    public CaixaPostal getCaixaPostalDoUsuarioLogado() {
        UsuarioCRM usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
        return usuario.getCaixaPostalPrincipal();
    }

    public void defineAbaExibicaoInicial() {
        if (getProspectoSelecionado() != null) {
            setIndiceExibicaoEmailRecebido(0);
        }
    }

    public PessoaJuridica getProspectoSelecionado() {

        if (getParametroInstanciado(parametroProspectoSelecionado).getValor() != null) {
            return UtilSBPersistencia.loadEntidade((PessoaJuridica) getParametroInstanciado(parametroProspectoSelecionado).getValor(), getEMPagina());
        } else {
            if (getEntidadeSelecionada() != null) {
                if (getEntidadeSelecionada().isUmEmailDeProspecto()) {
                    return UtilSBPersistencia.loadEntidade((PessoaJuridica) getEntidadeSelecionada().getProspecto(), getEMPagina());
                }
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    protected void autoExecRotasAlternativasExecucaoDeAcaoAtual(EmailCrm pEntidade) {
        super.autoExecRotasAlternativasExecucaoDeAcaoAtual(pEntidade);
        if (pEntidade != null) {
            if (pEntidade.getComoEnvioEmail() != null) {
                if (isAcaoSelecionadaIgualA(FabAcaoCRMAtendimento.EMAILS_FRM_VISUALIZAR_EMAIL)) {

                    if (pEntidade.getComoEnvioEmail().getStatusEnvio() == null || pEntidade.getComoEnvioEmail().getStatusEnvio().equals(FabStatusEnvioEmail.RASCUNHO.getRegistro())) {
                        setAcaoSelecionada(FabAcaoCRMAtendimento.EMAILS_FRM_EDITAR.getRegistro());
                    }
                }

            }
        }
    }

    @Override
    protected void autoexecEntidadeNova() {
        super.autoexecEntidadeNova(); //To change body of generated methods, choose Tools | Templates.
    }

    private void registrarLeitura(EmailRecebido mailRecebido) {
        if (mailRecebido != null) {
            ModuloCRMAtendimentoEmail.registrarLeituraEmailRecebido(mailRecebido);
        }
    }

    @Override
    public void executarAcao(EmailCrm pEntidadeSelecionada) {
        if (!SBCore.getServicoSessao().getSessaoAtual().isIdentificado()
                || !(SBCore.getUsuarioLogado() instanceof UsuarioCRM)) {
            return;
        }

        FabAcaoCRMAtendimento acao = getEnumAcaoAtual();
        switch (acao) {
            case EMAILS_FRM_VISUALIZAR_EMAIL:
                if (getParametroInstanciado(parametroEmailRecebidoReferencia).isValorDoParametroFoiConfigurado()) {
                    pEntidadeSelecionada = (EmailCrm) getParametroInstanciado(parametroEmailRecebidoReferencia).getValor();
                }
                if (pEntidadeSelecionada != null && pEntidadeSelecionada instanceof EmailRecebido) {
                    registrarLeitura((EmailRecebido) pEntidadeSelecionada);
                }
                if (pEntidadeSelecionada == null) {
                    if (getParametroInstanciado(parametroEmailRecebidoReferencia).isValorDoParametroFoiConfigurado()) {
                        pEntidadeSelecionada = (EmailCrm) getParametroInstanciado(parametroEmailRecebidoReferencia).getValor();
                    }
                    if (getParametroInstanciado(parametroEmailEnviadoReferencia).isValorDoParametroFoiConfigurado()) {
                        pEntidadeSelecionada = (EnvioEmail) getParametroInstanciado(parametroEmailEnviadoReferencia).getValor();
                    }
                    if (getParametroInstanciado(parametroEmailAtiviadeEnviadoReferencia).isValorDoParametroFoiConfigurado()) {
                        pEntidadeSelecionada = (EnvioEmailAtividade) getParametroInstanciado(parametroEmailAtiviadeEnviadoReferencia).getValor();
                    }

                }

                super.executarAcao(pEntidadeSelecionada);
                break;
            case EMAILS_FRM_NOVO:
                super.executarAcao(pEntidadeSelecionada);
                getEntidadeSelecionada().setCaixaPostal(((UsuarioCRM) SBCore.getUsuarioLogado()).getCaixaPostalPrincipal());
                if (getParametroInstanciado(parametroContatoPessoa).isValorDoParametroFoiConfigurado()) {
                    ContatoProspecto contato = (ContatoProspecto) getParametroInstanciado(parametroContatoPessoa).getValor();
                    getEntidadeSelecionada().setProspecto(contato.getProspecto());
                    getEntidadeSelecionada().getComoEnvioEmail().adicionarContato(contato);
                }
                break;
            case EMAILS_FRM_ENCAMINHAR:
                if ((!getXhtmlAcaoAtual().equals(FabAcaoCRMAtendimento.EMAILS_FRM_ENCAMINHAR.getRegistro().getComoFormulario().getXhtml()))) {

                    if (pEntidadeSelecionada.getProspecto() == null) {
                        String url = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.EMAILS_FRM_ENCAMINHAR.getRegistro(), pEntidadeSelecionada);

                        UtilSBWP_JSFTools.vaParaPagina(url);
                        break;
                    } else {
                        String url = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.EMAILS_FRM_ENCAMINHAR.getRegistro(), pEntidadeSelecionada.getProspecto(), pEntidadeSelecionada);

                        UtilSBWP_JSFTools.vaParaPagina(url);
                        break;
                    }

                } else {
                    EmailCrm encaminhamentoEmailREferencia = null;
                    if (getParametroInstanciado(parametroEmailEnviadoReferencia).isValorDoParametroFoiConfigurado()) {
                        encaminhamentoEmailREferencia = (EnvioEmail) getParametroInstanciado(parametroEmailEnviadoReferencia).getValor();
                    }
                    if (getParametroInstanciado(parametroEmailAtiviadeEnviadoReferencia).isValorDoParametroFoiConfigurado()) {
                        encaminhamentoEmailREferencia = (EnvioEmail) getParametroInstanciado(parametroEmailAtiviadeEnviadoReferencia).getValor();
                    }
                    if (getParametroInstanciado(parametroEmailRecebidoReferencia).isValorDoParametroFoiConfigurado()) {
                        encaminhamentoEmailREferencia = (EmailCrm) getParametroInstanciado(parametroEmailRecebidoReferencia).getValor();
                    }
                    setEntidadeSelecionada(new EnvioEmail());
                    getEntidadeSelecionada().setEmailSolicitante(encaminhamentoEmailREferencia);
                    getEntidadeSelecionada().setAssunto("Enc: " + getEntidadeSelecionada().getEmailSolicitante().getAssunto());
                    getEntidadeSelecionada().setTexto("E-mail encaminhado de: " + encaminhamentoEmailREferencia.getEmailRemetente() + "<br/>" + encaminhamentoEmailREferencia.getTexto());

                }
                break;
            case EMAILS_FRM_RESPONDER:
                adicionarAcaoNoHistorico(FabAcaoCRMAtendimento.EMAILS_FRM_RESPONDER.getRegistro());

                if (pEntidadeSelecionada instanceof EmailRecebido) {
                    if (pEntidadeSelecionada.getProspecto() == null) {
                        String url = MapaDeFormularios.getUrlFormulario(acao.getRegistro(), pEntidadeSelecionada);
                        registrarLeitura((EmailRecebido) pEntidadeSelecionada);
                        UtilSBWP_JSFTools.vaParaPagina(url);
                        break;
                    } else {
                        String url = MapaDeFormularios.getUrlFormulario(acao.getRegistro(), pEntidadeSelecionada.getProspecto(), pEntidadeSelecionada);
                        registrarLeitura((EmailRecebido) pEntidadeSelecionada);
                        UtilSBWP_JSFTools.vaParaPagina(url);
                        break;
                    }

                } else {
                    if (pEntidadeSelecionada == null) {

                        if (getEntidadeSelecionada() == null) {
                            setEntidadeSelecionada(new EnvioEmail());

                            if (!getParametroInstanciado(parametroEmailRecebidoReferencia).isValorDoParametroFoiConfigurado()) {
                                setAcaoSelecionada(FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_RECEBIDO.getRegistro());
                                super.executarAcao(null);
                                break;

                            }
                            if (getParametroInstanciado(parametroProspectoSelecionado).isValorDoParametroFoiConfigurado()) {
                                try {
                                    getEntidadeSelecionada().prepararNovoObjeto(getParametroInstanciado(parametroProspectoSelecionado).getValor());
                                } catch (ErroPreparandoObjeto ex) {
                                    Logger.getLogger(PgEmails.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                try {
                                    getEntidadeSelecionada().prepararNovoObjeto();
                                } catch (ErroPreparandoObjeto ex) {
                                    Logger.getLogger(PgEmails.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            getEntidadeSelecionada().setEmailSolicitante((EmailCrm) getParametroInstanciado(parametroEmailRecebidoReferencia).getValor());
                            getEntidadeSelecionada().setAssunto("Re: " + getEntidadeSelecionada().getEmailSolicitante().getAssunto());
                            getEntidadeSelecionada().setCaixaPostal(((UsuarioCRM) SBCore.getUsuarioLogado()).getCaixaPostalPrincipal());
                            if (getEntidadeSelecionada().getEmailSolicitante().getProspecto() != null) {
                                List<ContatoProspecto> contatos = new ArrayList<>();
                                String emailRementente = UtilCRCEmailObjetos.extrairTextoEamailDeStringProtocoloPadrao(getEntidadeSelecionada().getEmailSolicitante().getEmailRemetente());
                                Optional<ContatoProspecto> contatoFind
                                        = getEntidadeSelecionada().getEmailSolicitante().getProspecto()
                                                .getContatosComEmail().stream()
                                                .filter(contato -> contato.getEmail() != null && contato.getEmail().equals(emailRementente))
                                                .findFirst();
                                if (contatoFind.isPresent()) {
                                    contatos.add(contatoFind.get());
                                }
                                try {
                                    getEntidadeSelecionada().getCampoInstanciadoByNomeOuAnotacao(CPEnvioEmail.contatos).setValorSeValido(contatos);
                                } catch (ErroValidacao ex) {
                                    Logger.getLogger(PgEmails.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                EmailRecebido emailRecebido = (EmailRecebido) getParametroInstanciado(parametroEmailRecebidoReferencia).getValor();
                                String emailRemetenteTeste = emailRecebido.getEmailRemetente();
                                List<ContatoProspecto> contatos = new ArrayList<>();
                                List<UsuarioCRM> usuarios = UtilSBPersistencia.getListaTodos(UsuarioCRM.class, getEMPagina());
                                String emailRemetente = UtilCRCEmailObjetos.extrairTextoEamailDeStringProtocoloPadrao(getEntidadeSelecionada().getEmailSolicitante().getEmailRemetente());
                                Optional<ContatoColaborador> contatoFind = usuarios.stream().map(usr -> usr.getContatoVinculado()).
                                        filter(contato -> contato != null && !UtilCRCStringValidador.isNuloOuEmbranco(contato.getEmail()) && contato.getEmail().equals(emailRemetente))
                                        .findFirst();
                                if (contatoFind.isPresent()) {
                                    contatos.add(contatoFind.get());
                                } else {

                                }
                                try {
                                    UsuarioCRM usuarioLogado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
                                    Optional<ContatoPessoal> contatoPessoal = usuarioLogado.getContatosPrivados().stream().filter(ct -> ct.getEmail().equals(emailRemetente)).findFirst();
                                    if (contatoPessoal.isPresent()) {
                                        contatos.add(contatoPessoal.get());
                                    } else {
                                        ContatoPessoal novocontato = new ContatoPessoal();
                                        novocontato.setEmail(emailRemetente);
                                        novocontato.setResponsavel(usuarioLogado);
                                        contatos.add(novocontato);

                                    }
                                    getEntidadeSelecionada().getCampoInstanciadoByNomeOuAnotacao(CPEnvioEmail.contatos).setValorSeValido(contatos);
                                } catch (ErroValidacao ex) {
                                    Logger.getLogger(PgEmails.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        }
                    }

                }

                break;
            default:
                super.executarAcao(pEntidadeSelecionada); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    protected void autoExecProximaAcaoAposController(ComoAcaoController pAcaoController, ItfRespostaAcaoDoSistema pResposta) {
        super.autoExecProximaAcaoAposController(pAcaoController, pResposta); //To change body of generated methods, choose Tools | Templates.
    }

    @PostConstruct
    public void inicio() {

        if (!SBCore.getServicoSessao().getSessaoAtual().isIdentificado()
                || !(SBCore.getUsuarioLogado() instanceof UsuarioCRM)) {
            return;
        }

        try {
            UsuarioCRM usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
            if (usuario.getCaixaPostalPrincipal() == null) {
                SBCore.enviarAvisoAoUsuario("Nenhuma caixa vinculada ao seu usuário");
                setAcaoSelecionada(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro());
                xhtmlAcaoAtual = getAcaoSelecionada().getComoFormulario().getXhtml();
                return;
            }

        } catch (Throwable t) {
            setAcaoSelecionada(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro());
            xhtmlAcaoAtual = getAcaoSelecionada().getComoFormulario().getXhtml();
            return;
        }

        if (getXhtmlAcaoAtual() == null) {

            setAcaoSelecionada(FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_RECEBIDO.getRegistro());
            xhtmlAcaoAtual = getAcaoSelecionada().getComoFormulario().getXhtml();
            if (getParametroInstanciado(parametroProspectoSelecionado).isValorDoParametroFoiConfigurado()
                    && (!getParametroInstanciado(parametroEmailRecebidoReferencia).isValorDoParametroFoiConfigurado()
                    && !getParametroInstanciado(parametroEmailEnviadoReferencia).isValorDoParametroFoiConfigurado())) {
                setAcaoSelecionada(FabAcaoCRMAtendimento.EMAILS_FRM_EMAILS_DO_PROSPECTO.getRegistro());
            } else {
                if (getAcaoSelecionada() == null) {

                    setAcaoSelecionada(FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_RECEBIDO.getRegistro());
                }
            }
            adicionarAcaoNoHistorico(acaoSelecionada);

        }
        defineCategoriaPadrao();

    }

    private void defineCategoriaPadrao() {
        if (getProspectoSelecionado() != null) {
            categoriaSelecionada = FabCategoriaEmailRecebido.EMAIL_PROSPECTO.getRegistro();
        } else {
            UsuarioCRM usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
            categoriaSelecionada = FabCategoriaEmailRecebido.EMAIL_PROSPECTO.getRegistro();
        }
    }

    private void listarDadosDefinindoLimite(final int pLimite) {
        boolean pessoaFoiSelecionada = getProspectoSelecionado() != null;
        //NADA AQUI
        FabAcaoCRMAtendimento acao = (FabAcaoCRMAtendimento) getEnumAcaoAtual();
        if (acaoSelecionada.getComoFormulario().getComoFormularioEntidade().getTipoAcaoGenerica().equals(FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR)) {
            setEntidadesListadas(new ArrayList());
        }
        String condicaoPessoa;
        if (pessoaFoiSelecionada) {
            condicaoPessoa = "and prospecto_id = " + getProspectoSelecionado().getId();
        } else {
            condicaoPessoa = "";
        }

        List<EmailCrm> emailsListados = new ArrayList<>();
        switch (acao) {

            case EMAILS_MB_GESTAO:

            case EMAILS_FRM_LISTAR_RECEBIDO:
                FabCategoriaEmailRecebido categoria = categoriaSelecionada.getEnumVinculado();
                List<EmailCrm> recebidos = null;
                switch (categoria) {

                    case EMAIL_PROSPECTO:

                        recebidos = UtilSBPersistencia.getListaRegistrosByHQL(" from EmailCrm where tipoEmail='" + EmailRecebido.class.getSimpleName() + "' \n"
                                + " and caixaPostal_id = " + ((UsuarioCRM) SBCore.getUsuarioLogado()).getCaixaPostalPrincipal().getId() + " \n"
                                + " and categoriaEmailRecebido_id = " + categoriaSelecionada.getId()
                                + " and prospecto_id != null order by dataHoraArmazenamento DESC"
                                + "",
                                pLimite, getEMPagina()
                        );
                        break;
                    case EMAIL_PRIVADO:
                    case DESCLASSIFICADO:
                        recebidos = UtilSBPersistencia.getListaRegistrosByHQL(" from EmailCrm where tipoEmail='" + EmailRecebido.class.getSimpleName() + "' \n"
                                + " and caixaPostal_id = " + ((UsuarioCRM) SBCore.getUsuarioLogado()).getCaixaPostalPrincipal().getId() + " \n"
                                + " and categoriaEmailRecebido_id = " + categoriaSelecionada.getId()
                                + " and prospecto_id = null order by id DESC"
                                + "",
                                pLimite, getEMPagina()
                        );
                        break;
                    default:
                        throw new AssertionError(categoria.name());

                }

                emailsListados.addAll(recebidos);

                Collections.sort(emailsListados, ordenadorPadraoEmail);
                setEntidadesListadas(emailsListados);
                break;

            case EMAILS_FRM_LISTAR_ENVIADOS:

                List<EmailCrm> enviados = UtilSBPersistencia
                        .getListaRegistrosByHQL(" from EmailCrm where (statusEnvio_id= " + FabStatusEnvioEmail.ENVIADO.getRegistro().getId() + " \n"
                                + " or statusEnvio_id= " + FabStatusEnvioEmail.FORMATADO.getRegistro().getId() + ") "
                                + "and usuarioCriou_id=" + SBCore.getUsuarioLogado().getId() + " \n"
                                + condicaoPessoa
                                + " order by datadisparo DESC"
                                + "", pLimite, getEMPagina());
                setEntidadesListadas(enviados);

                break;
            case EMAILS_FRM_LISTAR_RASCUNHOS:
                List<EmailCrm> rascunhos = UtilSBPersistencia.getListaRegistrosByHQL(" from EmailCrm where statusEnvio_id= "
                        + FabStatusEnvioEmail.RASCUNHO.getRegistro().getId() + " \n"
                        + "and usuarioCriou_id=" + SBCore.getUsuarioLogado().getId() + " \n"
                        + condicaoPessoa
                        + "order by id DESC"
                        + "", pLimite, getEMPagina());
                setEntidadesListadas(rascunhos);

                break;

            case EMAILS_FRM_EMAILS_DO_PROSPECTO:
                if (getParametroInstanciado(parametroProspectoSelecionado).getValor() == null) {
                    executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_RECEBIDO);
                } else {
                    PessoaJuridica prosp = (PessoaJuridica) getParametroInstanciado(parametroProspectoSelecionado).getValor();
                    List<EmailCrm> emails = UtilSBPersistencia.getListaRegistrosByHQL(" from EmailCrm where prospecto_id = ?0 order by dataHoraArmazenamento DESC", 100, getEMPagina(), prosp.getId());
                    setEntidadesListadas(emails);
                }

                break;
            case EMAILS_FRM_LISTAR_ULTMOS_EMAILS:
                List<EmailCrm> ultimosEmails = UtilSBPersistencia.getListaRegistrosByHQL(" from EmailCrm "
                        + "where caixaPostal_id = " + ((UsuarioCRM) SBCore.getUsuarioLogado()).getCaixaPostalPrincipal().getId() + " \n"
                        + " order by id DESC", 100, getEMPagina());
                setEntidadesListadas(ultimosEmails);
                Collections.sort(getEntidadesListadas(), ordenadorPadraoEmail);

                break;

        }
    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        listarDadosDefinindoLimite(1000);

    }

    public void tabEmailRecebidoChange(TabChangeEvent event) {
        String id = event.getTab().getId();
        if (id.equals("listaConhecidos")) {
            categoriaSelecionada = FabCategoriaEmailRecebido.EMAIL_PROSPECTO.getRegistro();
        }
        if (id.equals("listaPessoal")) {
            categoriaSelecionada = FabCategoriaEmailRecebido.EMAIL_PRIVADO.getRegistro();
        }
        if (id.equals("listaSemCategoria")) {
            categoriaSelecionada = FabCategoriaEmailRecebido.DESCLASSIFICADO.getRegistro();
        }
        setEntidadesListadas(null);

        listarDados();

    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada(); //To change body of generated methods, choose Tools | Templates.

    }

    public boolean isModo() {
        return getParametroInstanciado(parametroProspectoSelecionado).isValorDoParametroFoiConfigurado();
    }

    public List<EmailCrm> getListaEmails() {
        return getEntidadesListadas();
    }

    @Override
    public void onModalNovoContatoEmailClose(SelectEvent event) {
        System.out.println("Fechando email");
        System.out.println(event.getObject());
        renovarEMPagina();

    }

    @Override
    public void onModalNovoContatoEmailOpen(AjaxBehaviorEvent event) {
        System.out.println("Nada aqui");
    }

    @Override
    public void onModalAnexoEmailClose(SelectEvent event) {
        System.out.println("Nada aqui");
    }

    public void salvarRascunho() {
        renovarEntityManager();
        UsuarioCRM usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
        int tamanhoAssinatura = 30;

        if (getEntidadeSelecionada().getTexto() != null && getEntidadeSelecionada().getTexto().length() > tamanhoAssinatura) {
            ItfResposta resp = ModuloCRMAtendimentoEmail.salvarrascunho((EnvioEmail) getEntidadeSelecionada());
            if (resp.isSucesso()) {

                renovarEMPagina();
                setEntidadeSelecionada(UtilSBPersistencia.loadEntidade((EnvioEmail) resp.getRetorno(), getEMPagina()));
            }

        }
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public void classificarEmail() {

        FabAcaoCRMAtendimento classificar = getEnumAcaoAtual();
        ItfRespostaAcaoDoSistema resposta = null;
        switch (classificar) {

            case EMAILS_CTR_RELATAR_SPAN:
                resposta = ModuloCRMAtendimentoEmail.emailDeclararSpan((EmailRecebido) getEntidadeSelecionada()).dispararMensagens();
                break;
            case EMAILS_CTR_ATRIBUIR_PESSSOA:
                resposta = ModuloCRMAtendimentoEmail.emailAtribuirPessoa((EmailRecebido) getEntidadeSelecionada()).dispararMensagens();
                break;
            case EMAILS_CTR_ATRIBUIR_PRIVADO:
                resposta = ModuloCRMAtendimentoEmail.emailatribuirPrivado((EmailRecebido) getEntidadeSelecionada()).dispararMensagens();
                break;

        }
        if (resposta == null) {
            SBCore.enviarMensagemUsuario("Falha identificando tipo de classificação", FabMensagens.ERRO);
            setAcaoSelecionada(getAcaoUltimoFormularioExecutado());
            return;
        }

        if (resposta.isSucesso()) {
            setEntidadesListadas(null);
            renovarEMPagina();
            setAcaoSelecionada(getAcaoUltimoFormularioExecutado());
            listarDadosDefinindoLimite(1000);
        }
        setAcaoSelecionada(getAcaoUltimoFormularioExecutado());

    }

    public void filtrar() {
        if (termoPesquisa == null) {
            return;
        }

        listarDadosDefinindoLimite(-1);
        List<ComparadorEmailCrm> emailComparacao = new ArrayList();
        FabTipoPesquisaGenerica tipoPesquisa = FabTipoPesquisaGenerica.getTipoPesquisaByTermo(termoPesquisa);
        getEntidadesListadas().stream().forEach(mail -> emailComparacao.add(new ComparadorEmailCrm(mail, termoPesquisa, tipoPesquisa)));
        Collections.sort(emailComparacao);
        Collections.reverse(emailComparacao);
        List<EmailCrm> listaPorPesquisa = (List) emailComparacao.stream().map(item -> item.getObjetoAnalizado()).limit(500).collect(Collectors.toList());
        setEntidadesListadas(listaPorPesquisa);
    }

    @Override
    public void renovarEntityManager() {
        renovarEMPagina();
    }

    @Override
    public void onModalProspectoClose(SelectEvent event) {

        if (getEntidadeSelecionada() instanceof EnvioEmail) {
            EnvioEmail envioemail = (EnvioEmail) getEntidadeSelecionada();
            ModuloCRMAtendimentoEmail.salvarrascunho(envioemail);
            renovarEntityManager();
            setEntidadeSelecionada(UtilSBPersistencia.loadEntidade(envioemail, getEMPagina()));
        }

    }

    public void onModalProspectoClose() {
        if (getEntidadeSelecionada() instanceof EnvioEmail) {
            EnvioEmail envioemail = (EnvioEmail) getEntidadeSelecionada();
            ModuloCRMAtendimentoEmail.salvarrascunho(envioemail);
            renovarEntityManager();
            setEntidadeSelecionada(UtilSBPersistencia.loadEntidade(envioemail, getEMPagina()));
        }
    }

    @Override
    public void onModalProspectoOpen(AjaxBehaviorEvent event) {

    }

    public void voltarEdicaoEmailEmAgendamento() {

        Optional<ComoAcaoDoSistema> acaoedicaoEmail = getAcoesHistoricoOrdemUltimaExecucao().stream()
                .filter(acao
                        -> (acao.equals(FabAcaoCRMAtendimento.EMAILS_FRM_NOVO.getRegistro())
                || acao.equals(FabAcaoCRMAtendimento.EMAILS_FRM_EDITAR.getRegistro())
                || acao.equals(FabAcaoCRMAtendimento.EMAILS_FRM_RESPONDER.getRegistro())
                || acao.equals(FabAcaoCRMAtendimento.EMAILS_FRM_ENCAMINHAR.getRegistro())))
                .findFirst();

        if (acaoedicaoEmail.isPresent()) {
            setAcaoSelecionada(acaoedicaoEmail.get());
            xhtmlAcaoAtual = acaoedicaoEmail.get().getComoFormulario().getXhtml();
        }

    }

    public int getIndiceExibicaoEmailRecebido() {

        switch (categoriaSelecionada.getEnumVinculado()) {
            case DESCLASSIFICADO:
                indiceExibicaoEmailRecebido = 2;
                break;
            case EMAIL_PROSPECTO:
                indiceExibicaoEmailRecebido = 0;
                break;
            case EMAIL_PRIVADO:
                indiceExibicaoEmailRecebido = 1;
                break;
            default:
                throw new AssertionError(categoriaSelecionada.getEnumVinculado().name());

        }
        return indiceExibicaoEmailRecebido;
    }

    public void setIndiceExibicaoEmailRecebido(int indiceExibicaoEmailRecebido) {
        this.indiceExibicaoEmailRecebido = indiceExibicaoEmailRecebido;
    }

}
