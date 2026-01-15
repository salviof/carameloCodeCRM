/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalProspecto;
import br.org.carameloCode.erp.crm.paginas.util.ModalHelper;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.FabStatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.codigoAcesso.CodigoConviteAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.FabUsuarioPadraoMarketingParaWeb;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_COM_EMAIL;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_CONVITE;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.beans.InfoMB_Bean;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.SelectEvent;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author sfurbino
 */
public abstract class MB_PaginaDeAtividade extends MB_PaginaConversation implements ItfPaginaComModalProspecto {

    @InfoMB_Bean(descricao = "Teste")
    protected Pessoa prospecto;
    protected AtividadeCRM atividade;
    protected List<ComoAcaoDoSistema> acoesDeProspecto;
    protected TipoAtividadeCRM tipoAtividadeSelecionada;
    protected boolean vaiAgendar;

    protected boolean vaiEditarDados;
    protected tipoExibicao tipoExibicaoPagina;
    protected EnvioEmailAtividade emailAtividade;

    protected List<ItfAcaoFormulario> etapasFormularios;
    protected String horarioTexto;
    protected Date diaAgendamento;
    private final ComoAcaoDoSistema acaoAgendar;
    private final ComoAcaoDoSistema acaoContinuarMaisTarde;

    protected enum tipoExibicao {
        ESCOLHA_DE_ATIVIDADE, NOVA_ATIVIDADE_ESPECIFICA, EDITAR_ATIVIDADE, ATIVIDADE_CONVIDADO
    }

    public MB_PaginaDeAtividade() {
        this.acaoContinuarMaisTarde = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_CONCLUIR_MAIS_TARDE.getRegistro();
        this.acaoAgendar = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_CTR_AGENDAR.getRegistro();
    }

    public abstract ItfParametroRequisicaoInstanciado getParametroProspecto();

    public abstract ItfParametroRequisicaoInstanciado getParametroAtividade();

    public abstract ItfParametroRequisicaoInstanciado getParaetroCodigoAcesso();

    public abstract ItfParametroRequisicaoInstanciado getParaetroTipoAtividade();

    protected void autoexecDefineTipoAtividade() {
        tipoExibicaoPagina = tipoExibicao.NOVA_ATIVIDADE_ESPECIFICA;
        if (getParaetroCodigoAcesso() != null && getParaetroCodigoAcesso().isValorDoParametroFoiConfigurado()) {
            tipoExibicaoPagina = tipoExibicao.ATIVIDADE_CONVIDADO;
            UsuarioCRM usuariocrm = FabUsuarioPadraoMarketingParaWeb.USUAIRO_CONVIDADO.getRegistro();
            SBCore.getServicoSessao().logarEmailESenha(usuariocrm.getEmail(), usuariocrm.getSenha());
            return;
        }
        if (getParametroAtividade().isValorDoParametroFoiConfigurado()) {
            tipoExibicaoPagina = tipoExibicao.EDITAR_ATIVIDADE;
            return;
        } else if (getParaetroTipoAtividade().isValorDoParametroFoiConfigurado()) {
            tipoAtividadeSelecionada = (TipoAtividadeCRM) UtilSBPersistencia.loadEntidade((TipoAtividadeCRM) getParaetroTipoAtividade().getValor(), getEMPagina());
            if (tipoAtividadeSelecionada == null) {
                throw new UnsupportedOperationException("O tipo de atividade não pôde ser determinado por" + getParaetroTipoAtividade().getValor());
            }
            tipoExibicaoPagina = tipoExibicao.NOVA_ATIVIDADE_ESPECIFICA;
        } else {
            tipoExibicaoPagina = tipoExibicao.ESCOLHA_DE_ATIVIDADE;
        }

    }

    protected void autoexecDefineProspectoDaatividade() {

        switch (tipoExibicaoPagina) {
            case ESCOLHA_DE_ATIVIDADE:
                if (!getParametroProspecto().isValorDoParametroFoiConfigurado()) {
                    UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_PARAMETRO_NAO_ENCONTRADO_MB.getRegistro()));
                    return;
                }
                prospecto = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroProspecto().getValor(), getEMPagina());
                break;
            case NOVA_ATIVIDADE_ESPECIFICA:
                if (!getParametroProspecto().isValorDoParametroFoiConfigurado()) {
                    UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_PARAMETRO_NAO_ENCONTRADO_MB.getRegistro()));
                    return;
                }
                prospecto = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroProspecto().getValor(), getEMPagina());
                break;
            case EDITAR_ATIVIDADE:
                if (getParametroAtividade() == null || !getParametroAtividade().isValorDoParametroFoiConfigurado()) {
                    throw new UnsupportedOperationException("O prospecto não foi definido");
                }
                prospecto = ((AtividadeCRM) UtilSBPersistencia
                        .loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroAtividade().getValor(), getEMPagina())).getProspectoEmpresa();
                break;
            case ATIVIDADE_CONVIDADO:
                if (atividade == null || atividade.getProspectoEmpresa() == null) {
                    throw new UnsupportedOperationException("O prospecto não foi definido");
                }
                prospecto = UtilSBPersistencia.loadEntidade(atividade.getProspectoEmpresa(), getEMPagina());
                break;
            default:
                throw new AssertionError(tipoExibicaoPagina.name());

        }

    }

    protected void autoexecDefineAtividadeAtual() {

        switch (tipoExibicaoPagina) {
            case ESCOLHA_DE_ATIVIDADE:
                break;
            case NOVA_ATIVIDADE_ESPECIFICA:
                ItfResposta resp = ModuloCRMEmail.iniciarAtendimento(tipoAtividadeSelecionada, prospecto, false);
                renovarEMPagina();
                if (resp.isSucesso()) {

                    atividade = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) resp.getRetorno(), getEMPagina());
                    atividade.setProspectoEmpresa(prospecto);

                } else {
                    atividade = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) resp.getRetorno(), getEMPagina());
                }
                break;
            case EDITAR_ATIVIDADE:
                atividade = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroAtividade().getValor(), getEMPagina());
                prospecto = atividade.getProspectoEmpresa();
                tipoAtividadeSelecionada = atividade.getTipoAtividade();
                break;
            case ATIVIDADE_CONVIDADO:
                CodigoConviteAtividade codigo = (CodigoConviteAtividade) getParametroInstanciado(getParaetroCodigoAcesso()).getValor();
                atividade = codigo.getAtividade();
                break;
            default:
                throw new AssertionError(tipoExibicaoPagina.name());

        }

    }

    protected void autoexecDefineAcaoSelecionada() {
        switch (tipoExibicaoPagina) {
            case ESCOLHA_DE_ATIVIDADE:
                acaoSelecionada = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro();
                break;
            case NOVA_ATIVIDADE_ESPECIFICA:

                if (atividade == null) {
                    setAcaoSelecionada(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ACESSO_NEGADO.getRegistro());
                } else {
                    acaoSelecionada = (ComoAcaoDoSistema) atividade.getCPinst(CPAtividadeCRM.acaoformularioexecucao).getValor();
                }
                break;
            case EDITAR_ATIVIDADE:
                FacesContext contexto = FacesContext.getCurrentInstance();
                if (contexto != null) {
                    String viewId = contexto.getViewRoot().getViewId();
                    if (viewId != null) {
                        if (viewId.equals(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB.getRegistro().getComoFormulario().getXhtml())) {
                            acaoSelecionada = (ComoAcaoDoSistema) atividade.getCPinst(CPAtividadeCRM.acaoformularioexecucao).getValor();
                        }
                    }
                }
                break;
            case ATIVIDADE_CONVIDADO:
                acaoSelecionada = atividade.getAcaoFormularioExecucao();
                break;
            default:
                throw new AssertionError(tipoExibicaoPagina.name());

        }

    }

    protected void autoexecDefineFormularioAtual() {
        try {
            switch (tipoExibicaoPagina) {
                case ESCOLHA_DE_ATIVIDADE:

                    xhtmlAcaoAtual = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro().getComoFormulario().getXhtml();
                    break;
                case NOVA_ATIVIDADE_ESPECIFICA:
                    if (atividade != null) {
                        xhtmlAcaoAtual = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_SOBRESCREVER_ATIVIDADE.getRegistro().getComoFormulario().getXhtml();
                        if (atividade.getStatusAtividade().equals(FabStatusAtividade.CONCLUIDA_COM_SUCESSSO.getRegistro())) {
                            xhtmlAcaoAtual = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro().getComoFormulario().getXhtml();
                            atividade = null;
                            return;
                        }

                        xhtmlAcaoAtual = (String) atividade.getCPinst(CPAtividadeCRM.formularioexecucao).getValor();
                    } else {
                        if (acaoSelecionada != null && acaoSelecionada.isUmaAcaoFormulario()) {
                            xhtmlAcaoAtual = acaoSelecionada.getComoFormulario().getXhtml();
                        }

                    }

                    break;

                case EDITAR_ATIVIDADE:
                    if (atividade == null) {
                        xhtmlAcaoAtual = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro().getComoFormulario().getXhtml();
                    } else {
                        FacesContext contexto = FacesContext.getCurrentInstance();
                        if (contexto != null) {
                            String viewId = contexto.getViewRoot().getViewId();
                            if (viewId != null) {
                                if (viewId.equals(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB.getRegistro().getComoFormulario().getXhtml())) {
                                    xhtmlAcaoAtual = (String) atividade.getCPinst(CPAtividadeCRM.formularioexecucao).getValor();
                                }
                            }
                        }
                    }
                    break;
                case ATIVIDADE_CONVIDADO:

                    if (atividade == null) {
                        xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();
                        UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_MB_PADRAO.getRegistro()));
                    } else {
                        atividade = UtilSBPersistencia.loadEntidade(getAtividade(), getEMPagina());
                        prospecto = atividade.getProspectoEmpresa();
                        tipoAtividadeSelecionada = atividade.getTipoAtividade();
                        xhtmlAcaoAtual = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_POR_CONVIDADO.getRegistro().getComoFormulario().getXhtml();
                    }
                    break;
                default:
                    throw new AssertionError(tipoExibicaoPagina.name());

            }
            switch (tipoExibicaoPagina) {
                case ATIVIDADE_CONVIDADO:
                    break;
                default:
                    if (prospecto != null && tipoAtividadeSelecionada == null) {
                        xhtmlAcaoAtual = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro().getComoFormulario().getXhtml();
                    }

            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro iniciando pagina de Atividade", t);
            SBCore.enviarMensagemUsuario("Houve um erro definindo o tipo de atividade", FabMensagens.ERRO);
            if (getProspecto() == null) {
                xhtmlAcaoAtual = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro().getComoFormulario().getXhtml();
            }
        }
    }

    @PostConstruct
    public void inicio() {

        acoesDeProspecto = new ArrayList<>();

        acoesDeProspecto.add(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro());
        acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO.getRegistro());
        acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_VISUALIZAR.getRegistro());
        acoesDeProspecto.add(FabAcaoCRMAtendimento.EMAILS_FRM_EMAILS_DO_PROSPECTO.getRegistro());
        acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_VER_ATIVIDADES.getRegistro());
        acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_PESSOA_JURIDICA.getRegistro());

        acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_SERVICOS.getRegistro());
        acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_ENCONTRAR_LOGOMARCA.getRegistro());
        //adicionarAcaoDeEntidade(FabAcaoCRMAtendimento.PROSPECTO_FRM_PRE_ANALISE);
        acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_ALTERAR_RELACIONAMENTO.getRegistro());
        acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_EXCLUIR_EMPRESA.getRegistro());
        acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_EDITAR_DADOS_DINAMICOS.getRegistro());
        acoesDeProspecto.add(FabAcaoCRMAtendimento.PROSPECTO_FRM_TAGS.getRegistro());

        try {
            autoexecDefineTipoAtividade();
            autoexecDefineProspectoDaatividade();
            if (prospecto == null) {
                UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_MB_PADRAO.getRegistro()));
                return;
            }
            if (prospecto.isUmPerfilPrivado()) {
                if (!prospecto.isUsuarioLogadoPermitido()) {
                    UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_MB_PADRAO.getRegistro()));
                    return;
                }
            }
            autoexecDefineAtividadeAtual();
            autoexecDefineAcaoSelecionada();
            autoexecDefineFormularioAtual();

        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro iniciando pagina de Atividade", t);
            SBCore.enviarMensagemUsuario("Houve um erro definindo o tipo de atividade", FabMensagens.ERRO);
            if (getProspecto() == null) {
                xhtmlAcaoAtual = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro().getComoFormulario().getXhtml();
            }
        }

        System.out.println("carregando" + xhtmlAcaoAtual);
    }

    public void autoExecAatualizarDadosAtividade() {
        renovarEMPagina();
    }

    @Override
    protected void renovarEMPagina() {
        super.renovarEMPagina(); //To change body of generated methods, choose Tools | Templates.
        if (prospecto == null) {
            if (getAtividade() != null) {
                prospecto = getAtividade().getProspectoEmpresa();

            }
        }

        prospecto = UtilSBPersistencia.loadEntidade(prospecto, getEMPagina());

        if (tipoAtividadeSelecionada != null) {
            tipoAtividadeSelecionada = UtilSBPersistencia.loadEntidade(tipoAtividadeSelecionada, getEMPagina());

        }

        if (atividade != null) {
            if (atividade.getId() != 0) {
                atividade = (AtividadeCRM) UtilSBPersistencia.loadEntidade(atividade, getEMPagina());
            }
        }

    }

    @Override
    public void onModalProspectoClose(SelectEvent event) {

        ItfAcaoFormulario acao = MapaAcoesSistema.getAcaoDoSistemaByFormulario(getXhtmlAcaoAtual());
        if (acao == null) {
            //atualizarFormularioExecucao();
            ModalHelper.atualizarCardEmpresaBasico(prospecto);
            autoExecAatualizarDadosAtividade();
        } else {
            FabAcaoCRMAtendimento acaoAtual = (FabAcaoCRMAtendimento) acao.getEnumAcaoDoSistema();
            switch (acaoAtual) {
                case EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_CONVITE:
                    autoExecAatualizarDadosAtividade();
                    return;
            }
            switch (acaoAtual) {

                case EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_COM_EMAIL:

                    ModalHelper.atualizarCardOpcoesEmail(prospecto);
                    //UtilSBWP_JSFTools.atualizaPorId("cardEmpresaBasico", false);
                    manterConexaoMail();
                default: {
                    UtilSBWP_JSFTools.atualizaPorId("cardEmpresaBasico", false);
                    renovarEMPagina();
                }
            }
        }

    }

    public synchronized void manterConexaoMail() {
        ModuloCRMAtendimento.atividadeEmailSalvarRascunhoEmail(atividade);
        //  renovarEMPagina();
    }

    @Override
    public void onModalProspectoOpen(AjaxBehaviorEvent event) {

        autoExecAatualizarDadosAtividade();
        ItfAcaoFormulario acao = MapaAcoesSistema.getAcaoDoSistemaByFormulario(getXhtmlAcaoAtual());
        if (acao == null) {
            //atualizarFormularioExecucao();
            //ModalHelper.atualizarCardEmpresaBasico(prospecto);

        } else {
            FabAcaoCRMAtendimento acaoAtual = (FabAcaoCRMAtendimento) acao.getEnumAcaoDoSistema();
            switch (acaoAtual) {
                case EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_CONVITE:
                    atualizarConvite();
            }
            switch (acaoAtual) {
                case EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_COM_EMAIL:
                    ModalHelper.atualizarCardContato(prospecto);
                default:
                    ModalHelper.atualizarCardEmpresaBasico(prospecto);
            }
        }

    }

    public void atualizarConvite() {
        if (getAtividade() != null && getAtividade().getConvitePrincipal() != null) {
            UtilSBPersistencia.mergeRegistro(getAtividade().getConvitePrincipal());
        }

    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return atividade;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        System.out.println("Set bean selecionado não implementado");
    }

    public AtividadeCRM getAtividade() {
        if (atividade == null) {
            return null;
        }
        try {
            if (atividade.getEmailComoEnvio() != null) {
                atividade.getEmailComoEnvio().getArquivosAnexados().size();
                //  atividade.setEmailVinculado(UtilSBPersistencia.loadEntidade(atividade.getEmailVinculado(), getEMPagina()));
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        return UtilSBPersistencia.loadEntidade(atividade, getEMPagina());
    }

    public void sobrescreverPrevalexendoAtividadeAtual() {
        try {

            ItfResposta resp = ModuloCRMEmail.atividadecancelaAtividadeAnteriorEmAberto(atividade);
            if (resp.isSucesso()) {
                atividade = (AtividadeCRM) resp.getRetorno();
                atualizarFormularioExecucao();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro sobrescrevendo atividade Anterior", t);
        }
    }

    public void sobrescreverPrevalecendoAtividadeAnterior() {
        try {
            ItfResposta resp = ModuloCRMEmail.atividadeExcluiEstaAtividadeERetomaAAnterior(atividade);
            if (resp.isSucesso()) {
                atividade = (AtividadeCRM) resp.getRetorno();
                atualizarFormularioExecucao();

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro excluindo atividade para continuar atividade anterior", t);
        }
    }

    public void emailEnviarEConcluir() {
        try {

            ItfResposta resp = ModuloCRMAtendimento.atividadeEmailEnviareConcluir(atividade);
            if (resp.isSucesso()) {
                atualizarFormularioExecucao();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro sobrescrevendo atividade Anterior", t);
        }
    }

    public void emailEnviar() {
        try {

            ItfResposta resp = ModuloCRMAtendimento.atividadeEmailEnviar(atividade);
            if (resp.isSucesso()) {
                atualizarFormularioExecucao();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro sobrescrevendo atividade Anterior", t);
        }
    }

    public void emailSalvarVersaoOficial() {
        try {
            ItfResposta resp = ModuloCRMAtendimento.atividadeEmailFormatado(atividade);
            if (resp.isSucesso()) {
                atualizarFormularioExecucao();
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro sobrescrevendo atividade Anterior", t);
        }
    }

    public void atualizarFormularioExecucao() {
        atualizarAreaSelecionadaEAtividade(null);
    }

    public ComoAcaoDoSistema getAcaoAgendar() {
        return acaoAgendar;
    }

    public ComoAcaoDoSistema getAcaoContinuarMaisTarde() {
        return acaoContinuarMaisTarde;
    }

    public Pessoa getProspecto() {
        return prospecto;
    }

    public TipoAtividadeCRM getTipoAtividadeSelecionada() {
        return tipoAtividadeSelecionada;
    }

    public void atualizarAreaSelecionadaEAtividade(String pArea) {
        String xhtlAnterior = xhtmlAcaoAtual;
        try {
            renovarEMPagina();
            switch (tipoExibicaoPagina) {
                case ATIVIDADE_CONVIDADO:
                    setAcaoSelecionada(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_POR_CONVIDADO.getRegistro());
                    xhtmlAcaoAtual = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_POR_CONVIDADO.getRegistro().getComoFormulario().getXhtml();
                    break;
                default:
                    if (getAtividade().isTemCodigoRemoto()) {
                        setAcaoSelecionada(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_CONVITE.getRegistro());
                        xhtmlAcaoAtual = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_CONVITE.getRegistro().getComoFormulario().getXhtml();

                    } else {
                        xhtmlAcaoAtual = atividade.getCampoInstanciadoByNomeOuAnotacao(CPAtividadeCRM.formularioexecucao).getValor().toString();

                    }

            }
            boolean tipoEtapamudou = !xhtlAnterior.equals(xhtmlAcaoAtual);

            if (pArea == null) {

                if (tipoEtapamudou) {
                    String url = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB.getRegistro().getAcaoDeGestaoEntidade(), getAtividade().getProspectoEmpresa(), getAtividade());
                    UtilSBWP_JSFTools.vaParaPagina(url);
                } else {
                    atualizarIdAreaExibicaoAcaoSelecionada();
                }
            } else {
                getPaginaUtil().atualizaTelaPorID(pArea);
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro atualizando formulario de execucao", t);
        }

    }

    public boolean isVaiAgendar() {
        return vaiAgendar;
    }

    public boolean isVaiEditarDados() {
        return vaiEditarDados;
    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
