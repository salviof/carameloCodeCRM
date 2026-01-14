package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrm.CPTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaFisica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.ListasProspectos;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioConvidado.UsuarioConvidado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.PROSPECTO_CTR_ATIVIDADE_SALVAR_DADOS_DINAMICOS;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_ARQUIVOS;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_E_EXECUTAR_ATIVIDADE;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_LOGO_MARCA_ENCONTRADA;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_MERGE;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.PROSPECTO_FRM_ENCONTRAR_LOGOMARCA;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.PROSPECTO_FRM_LISTAR;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;

import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;

import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCEmail;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

import java.util.Date;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
@InfoPagina(nomeCurto = "Prospectos", tags = {"Prospectos"})
@Named
@ViewScoped
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.PROSPECTO_MB_GERENCIAR)
public class PgProspecto extends MB_paginaCadastroEntidades<Pessoa> implements ItfPaginaComModalProspecto {

    private List<TipoRelacionamento> relacionamentosExistentes;
    private TipoRelacionamento tipoRelacionamentoSelecionado;

    private List<OrigemProspecto> origens;
    private OrigemProspecto origem;
    private final ComoAcaoDoSistema acaoNovoProspectoListarOpcoes;

    private String logomarcaSelecionada;
    private boolean alterarLogo;
    private int indiceAcaoAtual = -1;

    @InfoParametroURL(nome = "ProspectoPF", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = Pessoa.class, obrigatorio = false, representaEntidadePrincipalMB = true)
    private ParametroURL prPessoa;

    @Override
    public void onModalProspectoClose(SelectEvent event) {

        renovarEMPagina();

        if (getEntidadeSelecionada() != null) {
            setEntidadeSelecionada(UtilSBPersistencia.loadEntidade(getEntidadeSelecionada(), getEMPagina()));
            Collections.replaceAll(getEntidadesListadas(), getEntidadeSelecionada(), getEntidadeSelecionada());
        }
        try {
            UtilSBWP_JSFTools.atualizaPorId("dadosDaEmpresa", false);
        } catch (Throwable t) {

        }
    }

    @Override
    public void onModalProspectoOpen(AjaxBehaviorEvent event) {
        renovarEMPagina();
        if (getEntidadeSelecionada() != null) {
            UtilSBPersistencia.loadEntidade(getEntidadeSelecionada(), getEMPagina());
        }
    }

    private enum TIPO_PESQUISA {
        RELACIONAMENTO_E_ORIGEM, ORIGEM, RELACIONAMENTO, TODOS
    };

    public int getIndiceAcaoAtual() {
        if (indiceAcaoAtual == -1) {
            int i = 0;
            for (ComoAcaoDoSistema ac : getAcoesRegistros()) {
                if (ac.getEnumAcaoDoSistema().equals(getAcaoSelecionada().getEnumAcaoDoSistema())) {
                    indiceAcaoAtual = i;
                    break;
                }
                i++;
            }
        }
        return indiceAcaoAtual;
    }

    @Override
    public Pessoa getEntidadeSelecionada() {
        Pessoa pessoa = super.getEntidadeSelecionada();

        if (pessoa != null) {
            if (pessoa.getId() != null && pessoa.getId() != 0) {

                pessoa = UtilSBPersistencia.loadEntidade(pessoa, getEMPagina());
            }
        }
        setEntidadeSelecionada(pessoa);
        return pessoa;
    }

    @Override
    protected void autoexecEntidadeNova() {
        setEntidadeSelecionada(new PessoaJuridica());
    }

    private String termoPesquisa;

    private TIPO_PESQUISA getTIpoPesquisa() {
        if (origem != null && tipoRelacionamentoSelecionado == null) {
            return TIPO_PESQUISA.ORIGEM;
        }
        if (tipoRelacionamentoSelecionado != null && origem == null) {
            return TIPO_PESQUISA.RELACIONAMENTO;
        }

        if (tipoRelacionamentoSelecionado != null && origem != null) {
            return TIPO_PESQUISA.RELACIONAMENTO_E_ORIGEM;

        }

        return TIPO_PESQUISA.TODOS;
    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        TIPO_PESQUISA tipo = getTIpoPesquisa();

        switch (tipo) {
            case RELACIONAMENTO_E_ORIGEM:
                setEntidadesListadas(ListasProspectos.PROSPECTOS_DESTE_RELACIONAMENTO_NESTA_ORIGEM.getLista(getEMPagina(), tipoRelacionamentoSelecionado, origem));
                break;
            case ORIGEM:
                setEntidadesListadas(ListasProspectos.PROSPECTOS_DESTA_PROGIEM.getLista(getEMPagina(), origem));
                break;
            case RELACIONAMENTO:
                setEntidadesListadas(ListasProspectos.PROSPECTOS_DESTE_RELACIONAMENTO.getLista(getEMPagina(), tipoRelacionamentoSelecionado));
                break;
            case TODOS:
                setEntidadesListadas(UtilSBPersistencia.getListaTodos(Pessoa.class, getEMPagina()));
                break;
            default:
                throw new AssertionError(tipo.name());

        }
    }

    public String getTextoPesquisa() {
        TIPO_PESQUISA tipo = getTIpoPesquisa();
        String resp = "Você está pesquisando em prospectos";
        switch (tipo) {
            case RELACIONAMENTO_E_ORIGEM:
                return resp += "que se relacionam como " + tipoRelacionamentoSelecionado.getNome() + " e são de " + origem.getNome();

            case ORIGEM:
                return resp += "que são de " + origem.getNome();

            case RELACIONAMENTO:
                return resp += "que se relacionam como " + tipoRelacionamentoSelecionado.getNome();

            case TODOS:
                return "Você está pesquisndo entre TODOS os prospectos";
            default:
                throw new AssertionError(tipo.name());

        }
    }

    @PostConstruct
    public void inicio() {

        relacionamentosExistentes = UtilSBPersistencia.getListaTodos(TipoRelacionamento.class, getEMPagina());
        origens = UtilSBPersistencia.getListaTodos(OrigemProspecto.class, getEMPagina());
        getAcoesRegistros().clear();
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_VISUALIZAR);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.EMAILS_FRM_EMAILS_DO_PROSPECTO);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_VER_ATIVIDADES);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_PESSOA_JURIDICA);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_INTEGRACAO);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_SERVICOS);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.MEUS_ORCAMENTOS_FRM_LISTAR_ORCAMENTOS_DA_PESSOA);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_ENCONTRAR_LOGOMARCA);
        //adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_PRE_ANALISE);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_ALTERAR_RELACIONAMENTO);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_EXCLUIR_EMPRESA);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_DADOS_DINAMICOS);
        adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_TAGS);

        if (getEntidadeSelecionada() != null) {
            if (isAcaoSelecionadaIgualA(FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_ENDERECO_PESSOA_JURIDICA)) {
                if (getEntidadeSelecionada().getLocalizacao() == null) {
                    getEntidadeSelecionada().instanciarNovoEndereco();
                }
            }
            if (!getEntidadeSelecionada().isUsuarioLogadoPermitido()) {
                if (!SBCore.getUsuarioLogado().getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
                    executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.PROSPECTO_FRM_ACESSO_PESSOA_NEGADO);
                }

            } else {
                atualizarResponsaveisProspecto();
            }
        }

    }

    public int getSomaTotalInteracoes() {
        return getProspecto().getChamadosAbertos().size() + getProspecto().getAtividadesEmAndamento().size() + getProspecto().getSolicitacoesAbertas().size();
    }

    private void atualizarResponsaveisProspecto() {
        try {
            if (isAcaoSelecionadaIgualA(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO)) {
                if (getEntidadeSelecionada() != null) {
                    if (getEntidadeSelecionada().getDataHoraAlterouProspecto() == null || UtilCRCDataHora.intervaloTempoMinutos(getEntidadeSelecionada().getDataHoraAlterouProspecto(), new Date()) > 5) {
                        upRep();
                    } else {
                        if (UtilCRCDataHora.intervaloTempoMinutos(getEntidadeSelecionada().getDataHoraAlterouProspecto(), new Date()) > 1) {
                            new Thread() {
                                @Override
                                public void run() {
                                    upRep();
                                }

                            }.start();
                        }
                    }
                }
            }
        } catch (Throwable t) {

        }
    }

    private void atualizarRepresentante() {
        try {
            TipoDadoCRM tipodado = (TipoDadoCRM) new ConsultaDinamicaDeEntidade(TipoDadoCRM.class, getEMPagina())
                    .addcondicaoCampoIgualA(CPTipoDadoCRM.campoprospectocorrespondente, CPPessoa.usuarioresponsavel).getPrimeiroRegistro();
            if (tipodado != null) {
                DadoCRM novoDadoCRM = new DadoCRM();
                novoDadoCRM.setProspectoEmpresa((Pessoa) getEntidadeSelecionada());

                novoDadoCRM.setNome(tipodado.getLabel());
                novoDadoCRM.setTipoDadoCRM(tipodado);

                String emailUsuario = (String) novoDadoCRM.getCampoInstanciado().getValor();
                if (emailUsuario != null) {
                    UsuarioCRM responsavel = (UsuarioCRM) SBCore.getServicoPermissao().getUsuarioByEmail(emailUsuario);
                    if (responsavel != null) {
                        if (getEntidadeSelecionada().getUsuarioResponsavel() != null) {
                            if (getEntidadeSelecionada().getUsuarioResponsavel().equals(responsavel)) {
                                getEntidadeSelecionada().setDataHoraAlterouProspecto(new Date());
                                UtilSBPersistencia.mergeRegistro(getEntidadeSelecionada());
                                return;
                            }
                        }
                        getEntidadeSelecionada().setUsuarioResponsavel(responsavel);
                        if (!getEntidadeSelecionada().getUsuariosResponsaveis().contains(responsavel)) {
                            getEntidadeSelecionada().getUsuariosResponsaveis().add(responsavel);
                        }
                        getEntidadeSelecionada().setDataHoraAlterouProspecto(new Date());
                        UtilSBPersistencia.mergeRegistro(getEntidadeSelecionada());
                    }
                }
            }
        } catch (Throwable t) {

        }
    }

    private void atualizarAtendimento() {
        try {
            TipoDadoCRM tipodado = (TipoDadoCRM) new ConsultaDinamicaDeEntidade(TipoDadoCRM.class, getEMPagina())
                    .addcondicaoCampoIgualA(CPTipoDadoCRM.campoprospectocorrespondente, CPPessoa.usuarioatendimento).getPrimeiroRegistro();
            if (tipodado != null) {
                DadoCRM novoDadoCRM = new DadoCRM();
                novoDadoCRM.setProspectoEmpresa((Pessoa) getEntidadeSelecionada());

                novoDadoCRM.setNome(tipodado.getLabel());
                novoDadoCRM.setTipoDadoCRM(tipodado);

                String emailGestor = (String) novoDadoCRM.getCampoInstanciado().getValor();
                if (emailGestor != null) {
                    UsuarioCRM responsavel = (UsuarioCRM) SBCore.getServicoPermissao().getUsuarioByEmail(emailGestor);
                    if (responsavel != null) {

                        if (getEntidadeSelecionada().getUsuarioAtendimento() != null) {
                            if (getEntidadeSelecionada().getUsuarioAtendimento().equals(responsavel)) {
                                getEntidadeSelecionada().setDataHoraAlterouProspecto(new Date());
                                UtilSBPersistencia.mergeRegistro(getEntidadeSelecionada());
                                return;
                            }
                        }

                        getEntidadeSelecionada().setUsuarioAtendimento((UsuarioCRM) SBCore.getServicoPermissao().getUsuarioByEmail(emailGestor));
                        if (getEntidadeSelecionada().getUsuarioAtendimento() != null) {
                            if (!getEntidadeSelecionada().getUsuariosResponsaveis().contains(getEntidadeSelecionada().getUsuarioAtendimento())) {
                                getEntidadeSelecionada().getUsuariosResponsaveis().add(getEntidadeSelecionada().getUsuarioAtendimento());
                            }
                        }
                        getEntidadeSelecionada().setDataHoraAlterouProspecto(new Date());
                        UtilSBPersistencia.mergeRegistro(getEntidadeSelecionada());
                    }

                }
            }

        } catch (Throwable t) {

        }
    }

    @Override
    public List<ItfParametroRequisicaoInstanciado> getParametrosURL() {
        return super.getParametrosURL(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    private void upRep() {

        atualizarRepresentante();
        atualizarAtendimento();
    }

    public PgProspecto() {
        super(FabAcaoCRMAtendimento.PROSPECTO_MB_GERENCIAR.getRegistro().getComoGestaoEntidade());
        acaoNovoProspectoListarOpcoes = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_E_EXECUTAR_ATIVIDADE.getRegistro();
    }

    @Override
    protected void autoExecProximaAcaoAposController(ComoAcaoController pAcaoController, ItfRespostaAcaoDoSistema pResposta) {

        if (pResposta.isSucesso() && pResposta.getAcaoVinculada().isUmaAcaoController()) {
            if (pResposta.getRetorno() != null && pResposta.getRetorno() instanceof Pessoa) {
                setEntidadeSelecionada((Pessoa) pResposta.getRetorno());
            }
            atualizarEntidadeSelecionada();
            pResposta.dispararMensagens();
            if (pResposta.getAcaoVinculada().getEnumAcaoDoSistema().equals(FabAcaoCRMAtendimento.PROSPECTO_CTR_REMOVER)) {
                executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR);
            } else {
                executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO);
            }
        } else {

            super.autoExecProximaAcaoAposController(pAcaoController, pResposta); //To change body of generated methods, choose Tools | Templates.
            pResposta.dispararMensagens();
        }
    }

    @Override
    public void executarAcao(Pessoa pEntidadeSelecionada) {

        FabAcaoCRMAtendimento acaoExecutada = (FabAcaoCRMAtendimento) getEnumAcaoAtual();
        if (acaoExecutada.equals(FabAcaoCRMAtendimento.PROSPECTO_FRM_ACESSO_PESSOA_NEGADO)) {
            if (getEntidadeSelecionada() != null) {
                if (!getEntidadeSelecionada().isUsuarioLogadoPermitido()) {
                    if (!SBCore.getUsuarioLogado().getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
                        executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.PROSPECTO_FRM_ACESSO_PESSOA_NEGADO);
                    }
                    return;
                }

            }
        }

        switch (acaoExecutada) {

            case PROSPECTO_CTR_SALVAR_MERGE:
                ItfRespostaAcaoDoSistema resp = ModuloCRMAtendimento.prospectoSalvar(pEntidadeSelecionada).dispararMensagens();

                if (resp.isSucesso()) {
                    if (getAcaoUltimoFormularioExecutado().equals(FabAcaoCRMAtendimento.PROSPECTO_FRM_NOVO_CADASTRO_RAPIDO.getRegistro())) {
                        setEntidadeSelecionada(new PessoaJuridica());

                    } else {
                        if (getAcaoUltimoFormularioExecutado().equals(FabAcaoCRMAtendimento.PROSPECTO_FRM_NOVO.getRegistro())) {
                            if (resp.isSucesso()) {
                                renovarEMPagina();
                                setEntidadeSelecionada(UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) resp.getRetorno(), getEMPagina())); //  executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR);
                            }
                        }
                        autoExecProximaAcaoAposController(getAcaoSelecionada().getComoController(), resp);
                    }

                }
                break;
            case PROSPECTO_FRM_ENCONTRAR_LOGOMARCA:

                atualizarIdAreaExibicaoAcaoSelecionada();
                break;

            case PROSPECTO_CTR_SALVAR_LOGO_MARCA_ENCONTRADA:
                ItfResposta respLogo = ModuloCRMAtendimento.prospectoSalvarLogoEncontrada(getEntidadeSelecionada(), logomarcaSelecionada);
                respLogo.dispararMensagens();
                if (respLogo.isSucesso()) {
                    atualizarEntidadeSelecionada();
                    alterarLogo = false;
                }
                executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.PROSPECTO_FRM_ENCONTRAR_LOGOMARCA);

                break;
            case PROSPECTO_FRM_LISTAR:
                super.executarAcao(pEntidadeSelecionada);
                listarDados();
                break;
            case PROSPECTO_CTR_SALVAR_ARQUIVOS:
                if (isAcaoSelecionadaIgualA(FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_ARQUIVOS)) {
                    ItfResposta respProsp = ModuloCRMAtendimento.prospectoSalvar(getEntidadeSelecionada());
                    if (respProsp.isSucesso()) {
                        setAcaoSelecionada(getAcaoNovoRegistro());
                        executarAcaoSelecionada();
                    } else {
                        acaoSelecionada = getAcaoAnteriorExecutada();
                        xhtmlAcaoAtual = acaoSelecionada.getXhtmlVisao();
                    }

                }
                break;

            case PROSPECTO_CTR_SALVAR_E_EXECUTAR_ATIVIDADE:
                if (isAcaoSelecionadaIgualA(FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_E_EXECUTAR_ATIVIDADE)) {
                    ItfResposta respSalvarEExecutar = ModuloCRMAtendimento.prospectoSalvar(getEntidadeSelecionada());
                    if (respSalvarEExecutar.isSucesso()) {
                        String urlAtividade = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro(), respSalvarEExecutar.getRetorno());
                        UtilSBWP_JSFTools.vaParaPagina(urlAtividade);
                    } else {
                        acaoSelecionada = getAcaoUltimoFormularioExecutado();
                        xhtmlAcaoAtual = acaoSelecionada.getComoFormulario().getXhtml();
                    }
                    atualizarIdAreaExibicaoAcaoSelecionada();
                }
                break;
            case PROSPECTO_CTR_ATIVIDADE_SALVAR_DADOS_DINAMICOS:
                break;

            default:
                super.executarAcao(pEntidadeSelecionada);
                return;

        }

        if (!getAcaoSelecionada().isUmaAcaoFormulario()) {
            setAcaoSelecionada(getAcaoUltimoFormularioExecutado());
        }

    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada();

    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public Pessoa getProspecto() {
        return getEntidadeSelecionada();
    }

    public ComoAcaoDoSistema getAcaoNovoProspectoListarOpcoes() {
        return acaoNovoProspectoListarOpcoes;
    }

    public List<TipoRelacionamento> getRelacionamentosExistentes() {
        return relacionamentosExistentes;
    }

    public void setRelacionamentosExistentes(List<TipoRelacionamento> relacionamentosExistentes) {
        this.relacionamentosExistentes = relacionamentosExistentes;
    }

    public TipoRelacionamento getTipoRelacionamentoSelecionado() {
        return tipoRelacionamentoSelecionado;
    }

    public void setTipoRelacionamentoSelecionado(TipoRelacionamento tipoRelacionamentoSelecionado) {
        this.tipoRelacionamentoSelecionado = tipoRelacionamentoSelecionado;
    }

    public List<OrigemProspecto> getOrigens() {
        return origens;
    }

    public void setOrigens(List<OrigemProspecto> origens) {
        this.origens = origens;
    }

    public OrigemProspecto getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemProspecto origem) {
        this.origem = origem;
    }

    public void limparLogoAtual() {
        alterarLogo = true;
        executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.PROSPECTO_FRM_ENCONTRAR_LOGOMARCA);
    }

    public String getLogomarcaSelecionada() {
        return logomarcaSelecionada;
    }

    public void setLogomarcaSelecionada(String logomarcaSelecionada) {
        this.logomarcaSelecionada = logomarcaSelecionada;
    }

    public String getCaminhoPesquisaImagem() {
        String site = null;
        if (getEntidadeSelecionada() instanceof PessoaFisica) {
            site = ((PessoaFisica) getEntidadeSelecionada()).getSite();
        }
        if (getEntidadeSelecionada() instanceof PessoaJuridica) {
            site = ((PessoaJuridica) getEntidadeSelecionada()).getSite();
        }
        if (!UtilCRCStringValidador.isNuloOuEmbranco(site)) {
            return "https://www.google.com.br/search?q=site:" + site + "&source=lnms&tbm=isch&sa=X";
        } else {
            return "https://www.google.com.br/search?q=" + site + "&source=lnms&tbm=isch&sa=X";
        }
    }

    @Override
    public void metodoRespostaModal(Object... pParametros) {
        if (getEnumAcaoAtual().equals(FabAcaoCRMAtendimento.PROSPECTO_CTR_REMOVER)) {

        } else {
            super.metodoRespostaModal(pParametros); //chamada super do metodo (implementação classe pai)
        }
    }

    public void fecharExluirProspecto(SelectEvent event) {

        FabTipoRespostaComunicacao resp = mapaRespostasComunicacaoTransienteDeAcaoByAcoes.get(FabAcaoCRMAtendimento.PROSPECTO_CTR_REMOVER.getRegistro().getNomeUnico());
        if (resp.isRespostaPositiva()) {
            super.metodoRespostaModal(event.getObject());

            UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.MEUS_PROSPECTOS_FRM_LISTAR.getRegistro()));
        } else {
            executaAcaoSelecionadaPorEnum(getAcaoUltimoFormularioExecutado().getEnumAcaoDoSistema());
        }
        mapaRespostasComunicacaoTransienteDeAcaoByAcoes.clear();
    }

    public void passarABola() {
        ModuloCRMAtendimento.prospectoPassarABola(getProspecto()).dispararMensagens();
    }

    public void atualizarObservacao() {
        String obeservacao = getProspecto().getObservacao();
        renovarEMPagina();
        setEntidadeSelecionada(UtilSBPersistencia.loadEntidade(getProspecto(), getEMPagina()));
        getEntidadeSelecionada().setObservacao(obeservacao);
        setEntidadeSelecionada(UtilSBPersistencia.mergeRegistro(getEntidadeSelecionada(), getEMPagina()));
    }

    private UsuarioConvidado usuarioConvidadoNovo;

    public UsuarioConvidado getUsuarioConvidadoNovo() {
        if (usuarioConvidadoNovo == null) {
            usuarioConvidadoNovo = new UsuarioConvidado();
            usuarioConvidadoNovo.setGrupo(FabGruposIntranetCasaNova.CRM_CONVIDADO.getRegistro());
        }
        return usuarioConvidadoNovo;
    }

    public void criarNovoConvidado() {

        if (!UtilCRCStringValidador.isNuloOuEmbranco(usuarioConvidadoNovo.getEmail())) {
            String email = usuarioConvidadoNovo.getEmail();
            UsuarioCRM usuarioPrecadastrado = (UsuarioCRM) SBCore.getServicoPermissao().getUsuarioByEmail(email);
            if (usuarioPrecadastrado != null) {
                if (!(usuarioPrecadastrado instanceof UsuarioConvidado)) {
                    SBCore.enviarMensagemUsuario(email + "este email já está cadastrado como  " + usuarioPrecadastrado.getGrupo().getNome() + ", por isso não pode ser associado a um convidado", FabMensagens.ERRO);
                    return;
                }

                usuarioConvidadoNovo = (UsuarioConvidado) usuarioPrecadastrado;
            } else {
                try {
                    usuarioConvidadoNovo.getCPinst("email").setValorSeValido(email);
                } catch (ErroValidacao ex) {
                    SBCore.enviarMensagemUsuario(email + " é inválido: " + ex.getMessage(), FabMensagens.ERRO);
                    return;
                }
                if (!UtilCRCStringValidador.isNuloOuEmbranco(usuarioConvidadoNovo.getNome())) {
                    usuarioConvidadoNovo = UtilSBPersistencia.mergeRegistro(usuarioConvidadoNovo);
                    if (usuarioConvidadoNovo == null) {
                        SBCore.enviarAvisoAoUsuario("Falha registrando novo convidado");
                    }
                } else {
                    SBCore.enviarAvisoAoUsuario("Informe o nome, para cadastrar um novo usuário");
                }
            }
            if (!getEntidadeSelecionada().getUsuariosConvidados().stream().filter(usr -> usr.getEmail().equals(usuarioConvidadoNovo.getEmail())).findFirst().isPresent()) {
                getEntidadeSelecionada().getUsuariosConvidados().add(usuarioConvidadoNovo);
                UtilSBPersistencia.mergeRegistro(getEntidadeSelecionada());
                renovarEMPagina();
                UtilCRCEmail.enviarPorServidorPadraoV2(email, "Acesse <a href='https://crm.casanovadigital.com.br'> crm.casanovadigital.com.br</a>, para ter acesso aos arquivos e pastas do cliente", "A pasta de arquivos de " + getEntidadeSelecionada().getNome() + " foi compartilhada com você");
                setEntidadeSelecionada(UtilSBPersistencia.loadEntidade(getEntidadeSelecionada(), getEMPagina()));
                usuarioConvidadoNovo = new UsuarioConvidado();
            } else {
                SBCore.enviarAvisoAoUsuario("Usuário já foi convidado");
            }

        }

    }

}
