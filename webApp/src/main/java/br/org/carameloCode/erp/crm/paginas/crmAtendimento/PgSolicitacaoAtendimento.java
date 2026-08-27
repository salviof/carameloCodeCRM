/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_CLIENTE;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MINHAS_PENDENCIAS_ABERTAS;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_SOLICITACOES_PESSOA;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_ARQUIVO_CLIENTE;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_ARQUIVO_EQUIPE;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_CONFIRMACAO_CLIENTE;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_CONFIRMACAO_EQUIPE;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimentoSolicitacoes;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.CPSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author salvio
 */
@InfoPagina(nomeCurto = "Solicitação Equipe", tags = {"Solicitações"})
@Named
@ViewScoped
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SOLICITACAO_MB_GESTAO)
public class PgSolicitacaoAtendimento extends MB_paginaCadastroEntidades<Solicitacao> implements ItfPaginaComModalProspecto {

    @InfoParametroURL(nome = "prPessoa", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = Pessoa.class, obrigatorio = false)
    private ParametroURL prPessoa;

    @InfoParametroURL(nome = "prContatoCliente", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = ContatoProspecto.class, obrigatorio = false)
    private ParametroURL prContatoSolicitacao;

    @InfoParametroURL(nome = "prUsuarioCliente", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = UsuarioCrmCliente.class, obrigatorio = false)
    private ParametroURL prUsuarioCliente;

    @InfoParametroURL(nome = "prUsuarioEquipe", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = UsuarioCRM.class, obrigatorio = false)
    private ParametroURL prUsuarioEquipe;

    @InfoParametroURL(nome = "prSolicitacao", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = Solicitacao.class, representaEntidadePrincipalMB = true, obrigatorio = false)
    private ParametroURL prSolicitacao;

    @InfoParametroURL(nome = "prArquivo", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = ArquivoAnexado.class, obrigatorio = false)
    private ParametroURL prArquivoSelecionado;

    @InfoParametroURL(nome = "prChamado", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = ChamadoCliente.class, obrigatorio = false)
    private ParametroURL prChamado;

    @InfoParametroURL(nome = "catEquipe", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = CategoriaArquivoEquipe.class, obrigatorio = false)
    private ParametroURL prCategoriaEquipe;

    @InfoParametroURL(nome = "prOrcamento", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = Orcamento.class, obrigatorio = false)
    private ParametroURL prORcamento;

    private Pessoa pessoa;
    private UsuarioCRM usuarioEquipe;
    private ContatoProspecto contatoCliente;

    private CategoriaArquivoEquipe categoriaEquipe;

    private boolean prPessoaFoiDefinido;

    @Inject
    private ServicosCRM servicoCRM;
    private ArquivoAnexado arquivoSelecionado;

    public ArquivoAnexado getArquivoSelecionado() {
        if (getParametroInstanciado(prArquivoSelecionado).isValorDoParametroFoiConfigurado()) {
            arquivoSelecionado = (ArquivoAnexado) getParametroInstanciado(prArquivoSelecionado).getValor();
        }
        return arquivoSelecionado;
    }

    private ComoAcaoDoSistema acaoUnicaListagem;

    @PostConstruct
    public void inicio() {
        definirPArametros();
        definirAcoesDisponiveis();
    }

    public void definirAcoesDisponiveis() {
        acaoUnicaListagem = FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO.getRegistro();
        FabAcaoCRMAtendimento acao = getEnumAcaoAtual();
        switch (acao) {
            case SOLICITACAO_FRM_LISTAR_MINHAS_PENDENCIAS_ABERTAS:
                acaoUnicaListagem = FabAcaoCRMAtendimento.SOLICITACAO_CTR_ABRIR_FORMULARIO_RESOLUCAO.getRegistro();
                break;
            case SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS:
            case SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_CLIENTE:
            case SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE:
                acaoUnicaListagem = FabAcaoCRMAtendimento.SOLICITACAO_FRM_REVISAR_SOLICITACAO.getRegistro();
                break;
            case SOLICITACAO_FRM_NOVA_NOTIFICACAO_CLIENTE:
            case SOLICITACAO_FRM_NOVA_NOTIFICACAO_EQUIPE:
                acaoUnicaListagem = FabAcaoCRMAtendimento.SOLICITACAO_FRM_REVISAR_SOLICITACAO.getRegistro();
                break;

            default:
                return;
        }
    }

    @Override
    public void executarAcao(Solicitacao pEntidadeSelecionada) {
        super.executarAcao(pEntidadeSelecionada); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public boolean isPrChamadoDefinido() {
        return getParametroInstanciado(prChamado).isValorDoParametroFoiConfigurado();

    }

    public boolean isPrPessoaFoiDefinido() {
        return getParametroInstanciado(prPessoa).isValorDoParametroFoiConfigurado();

    }

    public ComoAcaoDoSistema getAcaoUnicaListagem() {
        return acaoUnicaListagem;
    }

    @Override
    public List<ItfAcaoFormularioEntidade> getAcoesNovoRegistro() {
        return super.getAcoesNovoRegistro(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void listarDados(boolean mostrarInativos) {

        FabAcaoCRMAtendimento acao = getEnumAcaoAtual();
        ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(Solicitacao.class, getEMPagina());
        consulta.addCondicaoNegativo(CPSolicitacao.foifinalizada);
        switch (acao) {
            case SOLICITACAO_FRM_LISTAR_MINHAS_PENDENCIAS_ABERTAS:
                consulta.addCondicaoManyToOneIgualA(CPSolicitacao.usuariosolicitado, getUsuarioEquipe());
                break;
            case SOLICITACAO_FRM_LISTAR_SOLICITACOES_PESSOA:
                if (getPessoa() != null) {
                    consulta.addCondicaoManyToOneIgualA(CPSolicitacao.pessoa, getPessoa());
                }
                break;
            case SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS:
            case SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE:
            case SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_CLIENTE:
                consulta.addCondicaoManyToOneIgualA(CPSolicitacao.usuariosolicitante, getUsuarioEquipe());

                break;

            default:
                super.listarDados(mostrarInativos);
                return;
        }
        if (acao.equals(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE)
                || acao.equals(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_CLIENTE)) {
            List<Solicitacao> listaCompleta = consulta.gerarResultados();
            List listaTipo = new ArrayList();
            if (acao.equals(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_EQUIPE)) {
                listaCompleta.stream().filter(it
                        -> (!(it.getTipoEntitySoliciatacao().equals(SolicitacaoArquivoCliente.class.getSimpleName())
                        || it.getTipoEntitySoliciatacao().equals(SolicitacaoConfirmacaoCliente.class.getSimpleName())))
                ).forEach(listaTipo::add);

            }
            if (acao.equals(FabAcaoCRMAtendimento.SOLICITACAO_FRM_LISTAR_MEUS_PEDIDOS_ABERTOS_CLIENTE)) {
                listaCompleta.stream().filter(it
                        -> (it.getTipoEntitySoliciatacao().equals(SolicitacaoArquivoCliente.class.getSimpleName())
                        || it.getTipoEntitySoliciatacao().equals(SolicitacaoConfirmacaoCliente.class.getSimpleName()))
                ).forEach(listaTipo::add);
            }
            setEntidadesListadas(listaTipo);
            return;
        } else {

            setEntidadesListadas((List) consulta.gerarResultados());
        }
    }

    public void realizarUpload(FileUploadEvent event) {
        try {
            servicoCRM.enviarArquivosEquipePessoa(event);
            ModuloCRMAtendimentoSolicitacoes.solicitacaoEnviarArquivoEquipe((SolicitacaoArquivoEquipe) getEntidadeSelecionada());
        } catch (Throwable t) {
            CarameloCode.getServicoMensagemFireForget().enviarMsgAlertaAoUsuario("Falha enviando arquivo");
        }
    }

    private void definirPArametros() {
        if (getParametroInstanciado(prPessoa).isValorDoParametroFoiConfigurado()) {
            pessoa = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prPessoa).getValor(), getEMPagina());
        }
        if (getParametroInstanciado(prUsuarioEquipe).isValorDoParametroFoiConfigurado()) {
            usuarioEquipe = UtilSBPersistencia.loadEntidade((UsuarioCRM) getParametroInstanciado(prUsuarioEquipe).getValor(), getEMPagina());
        } else {
            usuarioEquipe = UtilSBPersistencia.loadEntidade(CarameloCode.getUsuarioLogado(), getEMPagina());
        }
        if (getParametroInstanciado(prContatoSolicitacao).isValorDoParametroFoiConfigurado()) {
            contatoCliente = UtilSBPersistencia.loadEntidade((ContatoProspecto) getParametroInstanciado(prContatoSolicitacao).getValor(), getEMPagina());
            pessoa = contatoCliente.getProspecto();
        }
        if (getParametroInstanciado(prSolicitacao).isValorDoParametroFoiConfigurado()) {
            setEntidadeSelecionada(UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prSolicitacao).getValor(), getEMPagina()));
            pessoa = (((Solicitacao) getParametroInstanciado(prSolicitacao).getValor()).getPessoa());
            if (getEntidadeSelecionada() instanceof SolicitacaoArquivoEquipe) {
                categoriaEquipe = getEntidadeSelecionada().getComoSolicitacaoArquivoEquipe().getCategoriaArqEquipe();
            }
        }
    }

    @Override
    protected void autoexecEntidadeNova() {
        if (pessoa == null) {
            definirPArametros();
        }

        FabAcaoCRMAtendimento acao = getEnumAcaoAtual();
        switch (acao) {
            case SOLICITACAO_FRM_NOVO_ORCAMENTO:
                try {
                    setEntidadeSelecionada((Solicitacao) acao.getRegistro().getComoAcaoDeEntidade().getClasseRelacionada().newInstance());
                    if (getParametroInstanciado(prPessoa).isValorDoParametroFoiConfigurado()) {
                        pessoa = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prPessoa).getValor(), getEMPagina());

                        {
                            Orcamento orc = (Orcamento) pessoa.getCPinst(CPPessoa.ultimoorcamento).getValor();
                            if (orc.getId() == null) {
                                pessoa.setUltimoOrcamento(UtilSBPersistencia.mergeRegistro(orc, getEMPagina()));
                            }
                            try {
                                if (getParametroInstanciado(prORcamento).isValorDoParametroFoiConfigurado()) {
                                    getEntidadeSelecionada().prepararNovoObjeto(pessoa, getParametroInstanciado(prORcamento).getValor());
                                } else {
                                    getEntidadeSelecionada().prepararNovoObjeto(pessoa);
                                }

                            } catch (ErroPreparandoObjeto ex) {
                                Logger.getLogger(PgSolicitacaoAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    if (getParametroInstanciado(prORcamento).isValorDoParametroFoiConfigurado()) {
                        Orcamento orcamento = (Orcamento) getParametroInstanciado(prORcamento).getValor();
                        try {
                            getEntidadeSelecionada().prepararNovoObjeto(orcamento.getPessoa(), orcamento);
                        } catch (ErroPreparandoObjeto ex) {
                            Logger.getLogger(PgSolicitacaoAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (InstantiationException | IllegalAccessException ex) {
                    CarameloCode.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha criando solicitação de interação com orçamento", ex);
                }
                break;
            case SOLICITACAO_FRM_NOVO_CONFIRMACAO_CLIENTE:
            case SOLICITACAO_FRM_NOVO_ARQUIVO_CLIENTE:
                try {
                    setEntidadeSelecionada((Solicitacao) acao.getRegistro().getComoAcaoDeEntidade().getClasseRelacionada().newInstance());
                    if (getParametroInstanciado(prPessoa).isValorDoParametroFoiConfigurado()) {
                        pessoa = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prPessoa).getValor(), getEMPagina());
                    }
                    {
                        try {
                            getEntidadeSelecionada().prepararNovoObjeto(pessoa, getUsuarioCliente());
                        } catch (ErroPreparandoObjeto ex) {
                            Logger.getLogger(PgSolicitacaoAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (getUsuarioCliente() != null) {
                        getEntidadeSelecionada().setUsuarioSolicitado(getUsuarioCliente());
                    }
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(PgSolicitacaoAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SOLICITACAO_FRM_NOVO_CONFIRMACAO_EQUIPE:
            case SOLICITACAO_FRM_NOVO_ARQUIVO_EQUIPE: {
                try {
                    setEntidadeSelecionada((Solicitacao) acao.getRegistro().getComoAcaoDeEntidade().getClasseRelacionada().newInstance());
                    if (getParametroInstanciado(prPessoa).isValorDoParametroFoiConfigurado()) {
                        pessoa = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prPessoa).getValor(), getEMPagina());
                    }
                    {
                        try {
                            getEntidadeSelecionada().prepararNovoObjeto(pessoa, getUsuarioEquipe());
                            if (getCategoriaEquipe() != null) {
                                getEntidadeSelecionada().getComoSolicitacaoArquivoEquipe().setCategoriaArqEquipe(getCategoriaEquipe());
                            }
                        } catch (ErroPreparandoObjeto ex) {
                            Logger.getLogger(PgSolicitacaoAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(PgSolicitacaoAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case SOLICITACAO_FRM_NOVO_PEDIDO_ATUALIZAR_ARQUIVO: {
                try {
                    if (!getParametroInstanciado(prArquivoSelecionado).isValorDoParametroFoiConfigurado()) {
                        if (getParametroInstanciado(prPessoa).isValorDoParametroFoiConfigurado()) {
                            String url = CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_ARQUIVOS, getParametroInstanciado(prPessoa).getValor());
                            UtilSBWP_JSFTools.vaParaPagina(url);
                            return;
                        }

                    }
                    setEntidadeSelecionada((Solicitacao) acao.getRegistro().getComoAcaoDeEntidade().getClasseRelacionada().newInstance());

                    arquivoSelecionado = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prArquivoSelecionado).getValor(), getEMPagina());

                    try {
                        getEntidadeSelecionada().prepararNovoObjeto(arquivoSelecionado);
                        if (getParametroInstanciado(prUsuarioEquipe).isValorDoParametroFoiConfigurado()) {
                            getEntidadeSelecionada().setUsuarioSolicitado((UsuarioCRM) getParametroInstanciado(prUsuarioEquipe).getValor());
                        } else {
                            if (arquivoSelecionado.getUsuarioCriou() != null) {
                                if (arquivoSelecionado.getUsuarioCriou() instanceof UsuarioCRM) {
                                    if (!arquivoSelecionado.getUsuarioCriou().equals(CarameloCode.getUsuarioLogado())) {
                                        getEntidadeSelecionada().setUsuarioSolicitado((UsuarioCRM) arquivoSelecionado.getUsuarioCriou());
                                    }
                                }
                            }
                        }

                    } catch (ErroPreparandoObjeto ex) {
                        Logger.getLogger(PgSolicitacaoAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(PgSolicitacaoAtendimento.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            default:
                super.autoexecEntidadeNova();
        }
        if (getEntidadeSelecionada() != null) {
            getEntidadeSelecionada().setUsuarioSolicitante((UsuarioCRM) CarameloCode.getUsuarioLogado());
        }
    }

    @Override
    public void atualizarEntidadeSelecionada() {
        super.atualizarEntidadeSelecionada();
        if (categoriaEquipe != null) {
            categoriaEquipe = UtilSBPersistencia.loadEntidade(categoriaEquipe, getEMPagina());
        }
    }

    private UsuarioCrmCliente usuarioCliente;

    public UsuarioCrmCliente getUsuarioCliente() {
        if (usuarioCliente == null) {
            if (getParametroInstanciado(prUsuarioCliente).isValorDoParametroFoiConfigurado()) {
                usuarioCliente = UtilSBPersistencia.loadEntidade((UsuarioCrmCliente) getParametroInstanciado(prUsuarioCliente).getValor(), getEMPagina());
            } else if (getParametroInstanciado(prContatoSolicitacao).isValorDoParametroFoiConfigurado()) {
                ContatoProspecto ct = UtilSBPersistencia.loadEntidade((ContatoProspecto) getParametroInstanciado(prContatoSolicitacao).getValor(), getEMPagina());
                ct.getCPinst(CPContatoProspecto.usuariovinculado).getValor();
                usuarioCliente = ct.getUsuarioVinculado();
            }
        }
        return usuarioCliente;

    }

    public UsuarioCRM getUsuarioEquipe() {
        if (!getParametroInstanciado(prUsuarioEquipe).isValorDoParametroFoiConfigurado()) {
            if (getPessoa() != null) {

                //  getPessoa().getUsuariosResponsaveis().stream().filter(usr -> usr != null && !usr.equals(CarameloCode.getUsuarioLogado())).findFirst();
                //tentar adivinhar para quem da equipe é a solicitação vale a pena? em que casos?
            }

        } else {
            usuarioEquipe = (UsuarioCRM) getParametroInstanciado(prUsuarioEquipe).getValor();
        }
        return usuarioEquipe;
    }

    public ContatoProspecto getContatoCliente() {
        return contatoCliente;
    }

    public void onModalProspectoClose(SelectEvent event) {

    }

    @Override
    public void onModalProspectoOpen(AjaxBehaviorEvent event) {

    }

    public Pessoa getPessoa() {

        if (getEntidadeSelecionada() != null) {
            if (getEntidadeSelecionada().getPessoa() != null) {
                pessoa = getEntidadeSelecionada().getPessoa();
            }
        }

        return pessoa;
    }

    public CategoriaArquivoEquipe getCategoriaEquipe() {
        if (categoriaEquipe == null && getParametroInstanciado(prCategoriaEquipe).isValorDoParametroFoiConfigurado()) {
            categoriaEquipe = (CategoriaArquivoEquipe) getParametroInstanciado(prCategoriaEquipe).getValor();
        }
        return categoriaEquipe;
    }

    public void informarAtualizacaoArquivo() {
        if (ModuloCRMAtendimentoSolicitacoes.envioArquivoAtualizado(getEntidadeSelecionada().getComoSolicitacaoAtualizacao()).isSucesso()) {
            UtilSBWP_JSFTools.vaParaPagina(CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_FRM_LISTAR_ARQUIVOS_PASTA_EQUIPE, getEntidadeSelecionada().getPessoa(), getEntidadeSelecionada().getComoSolicitacaoAtualizacao().getArquivo().getCategoriaArqEquipe()));
        }
    }

}
