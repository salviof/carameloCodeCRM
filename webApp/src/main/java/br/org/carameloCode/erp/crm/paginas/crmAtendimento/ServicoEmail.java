/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.ItemSimilarArquivoAnexo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.EmailCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.ItemSimilarContatoCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EMAILS_FRM_EMAILS_DO_PROSPECTO;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmEmail.ModuloCRMAtendimentoEmail;
import com.google.common.collect.Lists;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtil;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author sfurbino
 */
@ViewScoped()
@Named
public class ServicoEmail implements Serializable {

    private List<ContatoProspecto> contatosDoUsuario;
    private EnvioEmail emailSelecionado;
    private final static String PARAMETRO_FORM_NOVO_CONTATO_NOME = "parametroNovoContatoNome";
    private final static String PARAMETRO_FORM_NOVO_CONTATO_EMAIL = "parametroNovoContatoEmail";
    private final static String PARAMETRO_FORM_NOVO_CONTATO_PESSOA = "parametroNovoContatoid_pessoa";
    @Inject
    private PgUtil pgUtil;
    private AcaoDoSistema acaoDeEmailExecucao;

    private final ContatoProspecto contatoNovo = new ContatoProspecto();

    private final ComoAcaoDoSistema[] acoesEmailRecebido
            = new ComoAcaoDoSistema[]{
                FabAcaoCRMAtendimento.EMAILS_FRM_RESPONDER.getRegistro(),
                FabAcaoCRMAtendimento.EMAILS_FRM_ENCAMINHAR.getRegistro()};
    private final ComoAcaoDoSistema[] acoesEmailEnviado = new ComoAcaoDoSistema[]{
        FabAcaoCRMAtendimento.EMAILS_FRM_ENCAMINHAR.getRegistro(),
        FabAcaoCRMAtendimento.EMAILS_FRM_EMAILS_DO_PROSPECTO.getRegistro()};
    private ComoAcaoDoSistema[] acoesEmailRespostaEmail;
    private final ComoAcaoDoSistema[] acoesEmailAtivoRascunho = new ComoAcaoDoSistema[]{
        FabAcaoCRMAtendimento.EMAILS_CTR_REMOVER_EMAIL_ENVIADO.getRegistro(),
        FabAcaoCRMAtendimento.EMAILS_CTR_SALVAR_RASCUNHO.getRegistro(),
        FabAcaoCRMAtendimento.EMAILS_CTR_ENVIAR_SALVANDO_RASCUNHO.getRegistro(),
        FabAcaoCRMAtendimento.EMAILS_FRM_AGENDAR_ENVIO.getRegistro()

    };
    private final ComoAcaoDoSistema[] acoesEmailPassivoRascunho = new ComoAcaoDoSistema[]{
        FabAcaoCRMAtendimento.EMAILS_FRM_ENCAMINHAR.getRegistro(),
        FabAcaoCRMAtendimento.EMAILS_FRM_ENCAMINHAR.getRegistro()};

    @Inject
    private PgUtil formularioUtilitario;

    @Inject
    private PgModalEmail modalEmail;

    private boolean pessoaFoiDefinida = false;

    private List<ContatoProspecto> contatosSelecionados;

    private List<ArquivoAnexado> arquivosByFiltro;

    @PostConstruct()
    public void inicio() {

        String nome = (String) UtilSBWPServletTools.getRequestParametro(PARAMETRO_FORM_NOVO_CONTATO_NOME);
        String email = (String) UtilSBWPServletTools.getRequestParametro(PARAMETRO_FORM_NOVO_CONTATO_EMAIL);
        String codigoPessoa = (String) UtilSBWPServletTools.getRequestParametro(PARAMETRO_FORM_NOVO_CONTATO_PESSOA);
        if (nome == null && email == null && codigoPessoa == null) {
            if (UtilSBWP_JSFTools.getIDSCaminhoAbsoluto("campo-email-para", true) != null) {

                ItfCampoInstanciado campoInstanciado = UtilSBWP_JSFTools.getCampoInstanciadoByIdNoCOntextoAtual("campo-email-para");
                if (campoInstanciado != null) {
                    emailSelecionado = (EnvioEmail) campoInstanciado.getObjetoDoAtributo();
                }
            }
        }
        if (!UtilCRCStringValidador.isNuloOuEmbranco(nome)
                || !UtilCRCStringValidador.isNuloOuEmbranco(email)
                || !UtilCRCStringValidador.isNuloOuEmbranco(codigoPessoa)) {
            if (!UtilCRCStringValidador.isNuloOuEmbranco(nome)) {
                contatoNovo.setNome(nome);
            }
            if (!UtilCRCStringValidador.isNuloOuEmbranco(email)) {
                contatoNovo.setEmail(email);
            }
            if (!UtilCRCStringValidador.isNuloOuEmbranco(codigoPessoa)) {
                contatoNovo.setProspecto(UtilSBPersistencia.getRegistroByID(Pessoa.class, Long.valueOf(codigoPessoa), modalEmail.getEMPagina()));
                pessoaFoiDefinida = true;
            }
        } else {
            contatosDoUsuario = UtilSBPersistencia.getListaTodos(ContatoProspecto.class, em);
        }

    }

    public List<ComoAcaoDoSistema> getAcoesDoEmail(EmailCrm pEmail) {

        if (pEmail.isUmEmailRecebido()) {
            return Lists.newArrayList(acoesEmailRecebido);
        } else {
            if (pEmail instanceof EmailRecebido) {
                return Lists.newArrayList(acoesEmailRecebido);

            } else {
                if (pEmail.isUmEmailModoRascunho() || 0 == 0) {
                    return Lists.newArrayList(acoesEmailAtivoRascunho);
                }
                if (pEmail.isUmEmailResposta()) {
                    return Lists.newArrayList(acoesEmailPassivoRascunho);
                }
            }

        }

        return new ArrayList<>();
    }

    public void iniciarAtividadeDeEmail(EmailCrm pEmail) {
        try {

            FabAcaoCRMAtendimento acao = (FabAcaoCRMAtendimento) acaoDeEmailExecucao.getEnumAcaoDoSistema();
            String urlAtividade;
            switch (acao) {
                case EMAILS_FRM_EMAILS_DO_PROSPECTO:
                    urlAtividade = MapaDeFormularios.getUrlFormulario(acaoDeEmailExecucao, pEmail.getProspecto());
                    break;
                default:
                    urlAtividade = MapaDeFormularios.getUrlFormulario(acaoDeEmailExecucao, pEmail);
            }

            UtilSBWP_JSFTools.vaParaPagina(urlAtividade);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao iniciar ", t);
            SBCore.enviarMensagemUsuario("Não foi possível iniciar a atividade :(", FabMensagens.AVISO);
        }
    }

    public List<ArquivoAnexado> anexosDisponiveisByFiltroTermo(String pTermo) {

        ItfCampoInstanciado campoInstanciado = UtilSBWP_JSFTools.getCampoInstanciadoByIdNoCOntextoAtual("campo-email-anexos");
        emailSelecionado = (EnvioEmail) campoInstanciado.getObjetoDoAtributo();
        List<ArquivoAnexado> todosArquivos = (List<ArquivoAnexado>) emailSelecionado.getCampoInstanciadoByNomeOuAnotacao("arquivosDisponiveis").getValor();
        arquivosByFiltro = filtrarOrdenandoMaisParecidosArquivo(todosArquivos, pTermo, 5);
        arquivosByFiltro.removeAll(emailSelecionado.getArquivosAnexados());
        return arquivosByFiltro;
    }

    public static <T> List<T> filtrarOrdenandoMaisParecidosArquivo(List<T> pLista, String pParametro, int pLimite) {
        List resp = new ArrayList();
        Map<Long, ItemSimilarArquivoAnexo> itens = pLista.stream().parallel()
                .collect(Collectors.toMap(n -> ((ComoEntidadeSimples) n).getId(),
                        n -> new ItemSimilarArquivoAnexo((ComoEntidadeSimples) n, pParametro)));

//(prodsml->produtosOrdenados.add(new ItemSimilar(prodsml, "coca ")));
        List<ItemSimilarArquivoAnexo> itensOrdenados = itens.values().stream().parallel().collect(Collectors.toList());
        Collections.sort(itensOrdenados);
        itensOrdenados.stream().limit(pLimite).forEach(item -> resp.add(item.getObjetoAnalizado()));
        return resp;
    }

    public List<ContatoProspecto> contatosDoUsuarioByFiltroTermo(String pTermo) {

        ItfCampoInstanciado campoInstanciado = UtilSBWP_JSFTools.getCampoInstanciadoByIdNoCOntextoAtual("campo-email-para");
        emailSelecionado = (EnvioEmail) campoInstanciado.getObjetoDoAtributo();
        List<ContatoProspecto> todosContatos = (List<ContatoProspecto>) emailSelecionado.getCampoInstanciadoByNomeOuAnotacao("contatosDisponiveis").getValor();
        todosContatos.removeAll(emailSelecionado.getContatos());
        if (todosContatos == null) {
            System.out.println("opa");
        }
        contatosSelecionados = filtrarOrdenandoMaisParecidosContato(todosContatos, pTermo, 5);

        atualizarValorPadraoPorTermo(pTermo);

        return contatosSelecionados;
    }

    public static <T> List<T> filtrarOrdenandoMaisParecidosContato(List<T> pLista, String pParametro, int pLimite) {
        List resp = new ArrayList();
        Map<Long, ItemSimilarContatoCrm> itens = pLista.stream().parallel()
                .collect(Collectors.toMap(n -> ((ComoEntidadeSimples) n).getId(),
                        n -> new ItemSimilarContatoCrm((ComoEntidadeSimples) n, pParametro)));

//(prodsml->produtosOrdenados.add(new ItemSimilar(prodsml, "coca ")));
        List<ItemSimilarContatoCrm> itensOrdenados = itens.values().stream().parallel().collect(Collectors.toList());
        Collections.sort(itensOrdenados);

        itensOrdenados.stream().limit(pLimite).forEach(item -> resp.add(item.getObjetoAnalizado()));
        return resp;
    }

    public void atualizarValorPadraoPorTermo(String pTermo) {

        if (pTermo.contains("@")) {
            contatoNovo.setEmail(pTermo);
            contatoNovo.setNome("");
        } else {
            contatoNovo.setNome(pTermo);
            contatoNovo.setEmail("");
        }
        if (contatosSelecionados != null && !(contatosSelecionados.stream().filter(ct -> ct.getId() == null)).findFirst().isPresent()) {
            contatosSelecionados.add(contatoNovo);
        }

    }

    @Inject
    private EntityManager em;

    public List<ContatoProspecto> getContatosDoUsuario() {
        return contatosDoUsuario;
    }

    public void setContatosDoUsuario(List<ContatoProspecto> contatosDoUsuario) {
        this.contatosDoUsuario = contatosDoUsuario;
    }

    public List<ContatoProspecto> getContatosSelecionados() {
        return contatosSelecionados;
    }

    public void setContatosSelecionados(List<ContatoProspecto> contatosSelecionados) {
        this.contatosSelecionados = contatosSelecionados;
    }

    public char getTipoDeRegistro(ContatoProspecto pcontato) {
        if (pcontato.getId() == null) {
            return '0';
        } else {
            return '1';
        }

    }

    public void abrirModalNovoContato(EnvioEmail pEmail) {
        if (emailSelecionado == null) {
            emailSelecionado = pEmail;
        }
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("closable", false);
        options.put("modal", true);
        options.put("width", "300px");
        options.put("height", "50%");
        options.put("responsive", true);
        options.put("position", "left");
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        options.put("showEffect", "slide");
        options.put("nome", "joao");

        Map<String, List<String>> parametros = new HashMap();

        if (!UtilCRCStringValidador.isNuloOuEmbranco(contatoNovo.getNome())) {
            parametros.put(PARAMETRO_FORM_NOVO_CONTATO_NOME, Lists.newArrayList(contatoNovo.getNome()));
        }
        if (!UtilCRCStringValidador.isNuloOuEmbranco(contatoNovo.getEmail())) {
            parametros.put(PARAMETRO_FORM_NOVO_CONTATO_EMAIL, Lists.newArrayList(contatoNovo.getEmail()));
        }
        if (emailSelecionado != null && emailSelecionado.getProspecto() != null) {
            parametros.put(PARAMETRO_FORM_NOVO_CONTATO_PESSOA, Lists.newArrayList(String.valueOf(emailSelecionado.getProspecto().getId())));
        }

        formularioUtilitario.exibirModal(options, parametros, FabAcaoCRMAtendimento.MODAL_EMAIL_FRM_NOVO_CONTATO.getRegistro().getComoFormulario().getXhtml());

    }

    public EmailCrm getEmailSelecionado() {
        return emailSelecionado;
    }

    public void abrirModalNovoAnexo(EnvioEmail pEmail) {
        if (adicionounovoContato) {
            Map<String, Object> options = new HashMap<>();
            options.put("resizable", true);
            options.put("draggable", true);
            options.put("closable", false);
            options.put("modal", true);
            options.put("width", "300px");
            options.put("height", "50%");
            options.put("responsive", true);
            options.put("position", "left");
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("headerElement", "customheader");
            options.put("showEffect", "slide");

            formularioUtilitario.exibirModal(options, FabAcaoCRMAtendimento.MODAL_EMAIL_FRM_NOVO_ANEXO.getRegistro().getComoFormulario().getXhtml());
        }
        emailSelecionado = pEmail;
    }

    public void setEmailSelecionado(EnvioEmail emailSelecionado) {
        this.emailSelecionado = emailSelecionado;
    }

    boolean adicionounovoContato = false;

    public void adicionarParaAoEmail(SelectEvent<ContatoProspecto> event) {
        //ItfPaginaAtual paginaOrigem = event.getFacesContext().getApplication().evaluateExpressionGet(event.getFacesContext(), "#{paginaAtual}", ItfPaginaAtual.class);
        //paginaOrigem.getInfoPagina().getComoFormularioWeb().getAcaoSelecionada();

        try {
            //ContatoProspecto contato = event.getObject();
            if (event.getObject() == null) {
                adicionounovoContato = false;
            } else {

                ContatoProspecto contato = event.getObject();
                if (contato.getId() == null) {
                    abrirModalNovoContato(emailSelecionado);
                }

            }
        } catch (Throwable t) {
            System.out.println(event.getObject());
        }

    }

    public void removerParaAoEmail(UnselectEvent<ContatoProspecto> event) {
        //ItfPaginaAtual paginaOrigem = event.getFacesContext().getApplication().evaluateExpressionGet(event.getFacesContext(), "#{paginaAtual}", ItfPaginaAtual.class);
        //paginaOrigem.getInfoPagina().getComoFormularioWeb().getAcaoSelecionada();
        try {
            String contatoRapido = UtilSBWP_JSFTools.getIDSCaminhoAbsoluto("contatosRapidoAdd");
            if (contatoRapido != null) {
                pgUtil.atualizaTelaPorID(contatoRapido);
            }
        } catch (Throwable t) {

        }
        System.out.println(event.getObject());

    }

    public boolean isAdicionounovoContato() {
        return adicionounovoContato;
    }

    public void setAdicionounovoContato(boolean adicionounovoContato) {
        this.adicionounovoContato = adicionounovoContato;
    }

    public ContatoProspecto getContatoNovo() {
        return contatoNovo;
    }

    public AcaoDoSistema getAcaoDeEmailExecucao() {
        return acaoDeEmailExecucao;
    }

    public void setAcaoDeEmailExecucao(AcaoDoSistema acaoDeEmailExecucao) {
        this.acaoDeEmailExecucao = acaoDeEmailExecucao;
    }

    public void removerDestinatario(EnvioEmail pEmail) {
        pEmail.getContatos().clear();
        pEmail.setProspecto(null);
    }

    public void salvarNovoContato() {
        ItfRespostaAcaoDoSistema resposta = ModuloCRMAtendimento.contatoSalvarMerge(getContatoNovo());

        if (resposta.isSucesso()) {
            modalEmail.renovarEMPagina();
            //getCampoInstanciado().getValidacaoLogicaEstrategia();
            ContatoProspecto contatoCriado = (ContatoProspecto) resposta.getRetorno();
            List<ContatoProspecto> contatosSelecionados = (List) modalEmail.getCampoInstanciado().getValor();
            contatosSelecionados.add(UtilSBPersistencia.loadEntidade(contatoCriado, modalEmail.getEMPagina()));

            // getCampoInstanciado().getComoCampoComListaDeOpcoes().getSeletor().atualizarListaCompleta();
            ((EnvioEmail) modalEmail.getCampoInstanciado().getObjetoDoAtributo()).getContatosDisponiveis().add(contatoCriado);
            ModuloCRMAtendimentoEmail.salvarrascunho((EnvioEmail) modalEmail.getCampoInstanciado().getObjetoDoAtributo());
            modalEmail.fecharModal();

        } else {
            resposta.dispararMensagens();
        }
    }

    public boolean isPessoaFoiDefinida() {
        return pessoaFoiDefinida;
    }

    public void relatarSpan(EmailRecebido pEmail) {
        ModuloCRMAtendimentoEmail.emailDeclararSpan(pEmail).dispararMensagens();
    }

}
