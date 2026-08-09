package br.org.carameloCode.erp.crm.paginas.crmCliente;

import br.org.carameloCode.erp.crm.paginas.servico.ServicoChatView;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.dataListLasy.BP_DataModelLasy;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.HorarioDisponivelAtendimentoPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraPresencial;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

import br.org.carameloCode.erp.crm.paginas.crmAgenda.ItfPaginaListaDeHorariosDisponiveis;
import br.org.carameloCode.erp.crm.paginas.crmReservas.ServicoCrmReserva;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.AgendaDisponibilidade;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.CPUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.CPUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.InfoAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 * @author sfurbino
 */
@ViewScoped
@Named
@InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.RESERVAS_MB_GESTAO)
@InfoPagina(nomeCurto = "RESCLI", tags = {"Minhas reservas com consultores"})
public class PgReservasCliente extends MB_paginaCadastroEntidades<ReservaHorarioCRM> implements ItfPaginaListaDeHorariosDisponiveis {

    @InfoParametroURL(nome = "Reserva", tipoParametro = TIPO_PARTE_URL.ENTIDADE, representaEntidadePrincipalMB = true, tipoEntidade = ReservaHoraRemotoVideo.class, obrigatorio = false)
    private ParametroURL parametroReservaRemoto;
    @InfoParametroURL(nome = "Reserva Presencial", tipoParametro = TIPO_PARTE_URL.ENTIDADE, representaEntidadePrincipalMB = true, tipoEntidade = ReservaHoraPresencial.class, obrigatorio = false)
    private ParametroURL parametroReservaPresencial;

    private UsuarioCrmCliente usuarioLogado;
    private UsuarioCRM usrRepComercial;
    private UsuarioCRM usrGestaoSucesso;

    private UsuarioCRM usrAtendenteSelecionado;

    private boolean temRepresentanteComercial;

    private boolean temGestorDeSucesso;

    private ComoChatSalaBean canalRocketChat;

    private AgendaDisponibilidade agendaDisponibilidade;

    @Inject
    private ServicoChatView servicoMatrix;

    @InfoParametroURL(tipoEntidade = TipoAgendamentoAtdmPublico.class, nome = "Tipo de reserva", tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false)
    private ParametroURL prTipoReserva;
    @InfoParametroURL(nome = "PrAtendentes", tipoParametro = TIPO_PARTE_URL.ENTIDADE, representaEntidadePrincipalMB = false, tipoEntidade = UsuarioCRM.class, obrigatorio = false)
    private ParametroURL prAtendentes;

    private List<TipoAgendamentoAtdmPublico> tiposDisponiveis;
    private EscopoPesquisaMelhorHorario escopoPesquisa;

    @Override
    public AgendaDisponibilidade getAgendaDisponibilidade() {
        return agendaDisponibilidade;
    }

    @Override
    public void setAgendaDisponibilidade(AgendaDisponibilidade agendaDisponibilidade) {
        this.agendaDisponibilidade = agendaDisponibilidade;
    }

    @Override
    public EscopoPesquisaMelhorHorario getEscopoPesquisa() {
        if (usrAtendenteSelecionado != null) {
            if (escopoPesquisa == null) {
                escopoPesquisa = UtilSBPersistencia.loadEntidade((EscopoPesquisaMelhorHorario) usrAtendenteSelecionado.getCPinst(CPUsuarioCRM.escopoagendaclientes).getValor(), getEMPagina());
            }
        }
        return escopoPesquisa;
    }

    @Override
    public String getXhtmlAcaoAtual() {
        String xhtml = super.getXhtmlAcaoAtual();
        if (xhtml != null) {
            if (xhtml.equals(FabAcaoCRMCliente.RESERVAS_FRM_HORARIOS_DISPONIVEIS.getRegistro().getComoFormulario().getXhtml())) {
                if (getEscopoPesquisa() != null) {
                    if (getTipoAgendamento() == null) {
                        setAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_ESCOLHER_TIPO_DE_RESERVA);
                        xhtmlAcaoAtual = FabAcaoCRMCliente.RESERVAS_FRM_ESCOLHER_TIPO_DE_RESERVA.getRegistro().getComoFormulario().getXhtml();
                        return xhtmlAcaoAtual;
                    }
                }
            }
        }
        xhtml = super.getXhtmlAcaoAtual();
        return xhtml;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void executarAcao(ReservaHorarioCRM pEntidadeSelecionada) {
        super.executarAcao(pEntidadeSelecionada); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    private void atualizarDisponibilidades() {

        if (getEscopoPesquisa() != null) {
            if (usrAtendenteSelecionado != null) {
                EscopoPesquisaMelhorHorario escopoAtual = UtilSBPersistencia.loadEntidade((EscopoPesquisaMelhorHorario) usrAtendenteSelecionado.getCPinst(CPUsuarioCRM.escopoagendaclientes).getValor(), getEMPagina());
                if (!escopoAtual.equals(escopoPesquisa)) {
                    escopoPesquisa = escopoAtual;
                }
            }

        } else {
            return;
        }

        if (getParametroInstanciado(parametroReservaPresencial)
                .isValorDoParametroFoiConfigurado()
                || getParametroInstanciado(parametroReservaRemoto).isValorDoParametroFoiConfigurado()) {

        }
        if (getEscopoPesquisa().getTiposAgendamentosDisponiveis()
                .size() > 1) {
            if (isAcaoSelecionadaIgualA(FabAcaoCRMCliente.RESERVAS_FRM_LISTAR)) {
                if (getParametroInstanciado(prTipoReserva).isValorDoParametroFoiConfigurado()) {
                    if (getEscopoPesquisa().getTiposAgendamentosDisponiveis().contains((TipoAgendamentoAtdmPublico) getParametroInstanciado(prTipoReserva).getValor())) {
                        getEntidadeSelecionada().setTipoAgendamento((TipoAgendamentoAtdmPublico) getParametroInstanciado(prTipoReserva).getValor());
                    }
                }
            }
            if (getAgendaDisponibilidade() == null || getAgendaDisponibilidade().getTipoAgendamento() == null) {
                if (getUsuarioAtendente() != null) {
                    executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_ESCOLHER_TIPO_DE_RESERVA);
                } else {
                    executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_ESCOLHA_ATENDENTE);
                }
                return;
            }
        } else {
            if (getEscopoPesquisa().getTiposAgendamentosDisponiveis().size() == 1) {
                defineTipoDeReserva(getEscopoPesquisa().getTiposAgendamentosDisponiveis().get(0));

            } else {
                if (getEscopoPesquisa().getTiposAgendamentosDisponiveis().isEmpty()) {
                    tiposDisponiveis = new ArrayList<>();
                    ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(DisponibilidadeAtdmtPublico.class, getEMPagina());
                    consulta.addCondicaoManyToManyContendoObjeto("atendentesDisponiveis", usrAtendenteSelecionado);
                    List<DisponibilidadeAtdmtPublico> disponibilidades = consulta.resultadoRegistros();
                    disponibilidades.stream().forEach(disp -> {
                        disp.getTiposAgendamentosPublicos().stream().forEach(tipoAgenda -> {
                            if (!tiposDisponiveis.contains(tipoAgenda)) {
                                tiposDisponiveis.add(tipoAgenda);
                            }
                        });
                    });
                    if (!tiposDisponiveis.isEmpty()) {
                        executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_ESCOLHER_TIPO_DE_RESERVA);
                        return;
                    } else {
                        executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_SEM_ATENDIMENTO);
                    }
                }
            }
        }
        if (getAgendaDisponibilidade() != null && !getAgendaDisponibilidade().getHorariosDisponiveis().isEmpty()) {
            executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_SEM_ATENDIMENTO);
        }

    }

    @PostConstruct
    public void inicio() {
        if (!SBCore.getServicoSessao().getSessaoAtual().isIdentificado()) {
            executaAcaoSelecionadaPorEnum(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM);
            return;
        }
        usuarioLogado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
        usrRepComercial = UtilSBPersistencia.loadEntidade((UsuarioCRM) usuarioLogado.getRepresentanteLegal().getUsuarioResponsavel(), getEMPagina());
        if (usuarioLogado.getRepresentanteLegal().getUsuarioAtendimento() != null) {
            usrGestaoSucesso = UtilSBPersistencia.loadEntidade((UsuarioCRM) usuarioLogado.getRepresentanteLegal().getUsuarioAtendimento(), getEMPagina());
        }
        temRepresentanteComercial = usrRepComercial != null;
        temGestorDeSucesso = usrGestaoSucesso != null;

        if (getParametroInstanciado(parametroReservaPresencial).isValorDoParametroFoiConfigurado()
                || getParametroInstanciado(parametroReservaRemoto).isValorDoParametroFoiConfigurado()) {
            if (getParametroInstanciado(parametroReservaPresencial).isValorDoParametroFoiConfigurado()) {
                executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_VISUALIZAR_PRESENCIAL);
                setEntidadeSelecionada((ReservaHorarioCRM) getParametroInstanciado(parametroReservaRemoto).getValor());

            }
            if (getParametroInstanciado(parametroReservaRemoto).isValorDoParametroFoiConfigurado()) {
                setEntidadeSelecionada((ReservaHorarioCRM) getParametroInstanciado(parametroReservaRemoto).getValor());
                executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_VISUALIZAR_REMOTO);
                renovarEMPagina();
            }
            UtilSBPersistencia.loadEntidade(getEntidadeSelecionada(), getEMPagina());
        } else {

            if (temRepresentanteComercial && temGestorDeSucesso) {
                executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_ESCOLHA_ATENDENTE);
            } else {
                if (temRepresentanteComercial) {
                    usrAtendenteSelecionado = usrRepComercial;
                    atualizarDisponibilidades();
                } else {
                    executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_SEM_ATENDIMENTO);
                }
            }
        }

        ReservaHorarioCRM reserva = (ReservaHorarioCRM) getUsuarioLogado().getCampoInstanciadoByNomeOuAnotacao(CPUsuarioCrmCliente.reservaativamaisproxima).getValor();
        if (reserva != null) {
            setEntidadeSelecionada(reserva);
            if (reserva.getTipoAgendamento().isUmAtendimentoRemoto()) {
                executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_VISUALIZAR_REMOTO);
            } else {
                executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_VISUALIZAR_PRESENCIAL);
            }
        }
        if (getParametroInstanciado(prAtendentes).isValorDoParametroFoiConfigurado()) {
            setUsrAtendenteSelecionado((UsuarioCRM) getParametroInstanciado(prAtendentes).getValor());
        }
    }

    public void setUsrAtendenteSelecionado(UsuarioCRM pUsrAtendenteSelecionado) {

        boolean mudouAtendente = pUsrAtendenteSelecionado != usrAtendenteSelecionado;
        this.usrAtendenteSelecionado = pUsrAtendenteSelecionado;
        if (mudouAtendente) {
            atualizarDisponibilidades();
        }
    }

    public UsuarioCRM getUsrAtendenteSelecionado() {
        return usrAtendenteSelecionado;
    }

    public List<TipoAgendamentoAtdmPublico> getTiposDisponiveis() {

        if (tiposDisponiveis == null || tiposDisponiveis.isEmpty()) {
            if (!getEscopoPesquisa().getTiposAgendamentosDisponiveis().isEmpty()) {
                tiposDisponiveis = new ArrayList<>();
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(DisponibilidadeAtdmtPublico.class,
                        getEMPagina());
                consulta.addCondicaoManyToManyContendoObjeto("atendentesDisponiveis", usrAtendenteSelecionado);
                List<DisponibilidadeAtdmtPublico> disponibilidades = consulta.resultadoRegistros();
                disponibilidades.stream().forEach(disp -> {
                    disp.getTiposAgendamentosPublicos().stream().forEach(tipoAgenda -> {
                        if (!tiposDisponiveis.contains(tipoAgenda)) {
                            tiposDisponiveis.add(tipoAgenda);
                        }
                    });
                });
                if (!tiposDisponiveis.isEmpty()) {
                    executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_ESCOLHER_TIPO_DE_RESERVA);
                }
            }
        }

        return tiposDisponiveis;
    }

    public void defineTipoDeReserva(TipoAgendamentoAtdmPublico tipoAgendamento) {
        if (tipoAgendamento != null) {

            getEntidadeSelecionada().setTipoAgendamento(tipoAgendamento);

            //   escopoSelecionado.getCampoInstanciadoByNomeOuAnotacao(CPEscopoPesquisaMelhorHorario.listahorariosdisponiveis).getValor();
        }

    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public void atualizarTelaSelecao() {

        if (getEscopoPesquisa() != null && (getEscopoPesquisa().getAtendentes() == null || getEscopoPesquisa().getAtendentes().isEmpty())) {
            executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_ESCOLHA_ATENDENTE);
        } else if (getEscopoPesquisa() != null && getTipoAgendamento() == null) {

            executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_ESCOLHER_TIPO_DE_RESERVA);

        } else if (getEscopoPesquisa() != null && getAgendaDisponibilidade() != null && (getAgendaDisponibilidade().getHorariosDisponiveis().isEmpty() || getAgendaDisponibilidade().isTemAgenda())) {
            executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_HORARIOS_DISPONIVEIS);
        } else {
            executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.RESERVAS_FRM_SEM_ATENDIMENTO);
        }
    }

    private HorarioDisponivelAtendimentoPublico horarioDisponivelSelecionado;

    @Override
    public HorarioDisponivelAtendimentoPublico getHorarioDisponivelSelecionado() {
        return horarioDisponivelSelecionado;
    }

    @Override
    public void setEntidadeSelecionada(ReservaHorarioCRM entidadeSelecionada) {
        super.setEntidadeSelecionada(entidadeSelecionada); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHorarioDisponivelSelecionado(HorarioDisponivelAtendimentoPublico pHorarioDisponivel) {

        if (pHorarioDisponivel.getTipoAgendamento().isUmAtendimentoRemoto()) {
            setEntidadeSelecionada(new ReservaHoraRemotoVideo());
            try {
                getEntidadeSelecionada().prepararNovoObjeto(pHorarioDisponivel);
                getEntidadeSelecionada().setPessoaRelacionada(usuarioLogado.getRepresentanteLegal());
                getEntidadeSelecionada().setAtendidoResponsavel(usuarioLogado);
                getEntidadeSelecionada().setAtendidos(new ArrayList<>());
                getEntidadeSelecionada().getAtendidos().add(usuarioLogado);
                getEntidadeSelecionada().setContatosAtendidos(new ArrayList());
                getEntidadeSelecionada().getContatosAtendidos().add(usuarioLogado.getContatoClienteVinculado());
                horarioDisponivelSelecionado = pHorarioDisponivel;

            } catch (ErroPreparandoObjeto ex) {
                Logger.getLogger(PgReservasCliente.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            setEntidadeSelecionada(new ReservaHoraPresencial());
            try {
                getEntidadeSelecionada().prepararNovoObjeto(pHorarioDisponivel);
                getEntidadeSelecionada().setPessoaRelacionada(usuarioLogado.getRepresentanteLegal());
                getEntidadeSelecionada().setAtendidoResponsavel(usuarioLogado);
                getEntidadeSelecionada().setAtendidos(new ArrayList<>());
                getEntidadeSelecionada().getAtendidos().add(usuarioLogado);
                getEntidadeSelecionada().setContatosAtendidos(new ArrayList());
                getEntidadeSelecionada().getContatosAtendidos().add(usuarioLogado.getContatoClienteVinculado());
                horarioDisponivelSelecionado = pHorarioDisponivel;

            } catch (ErroPreparandoObjeto ex) {
                Logger.getLogger(PgReservasCliente.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    public ItfAcaoFormulario getAcaoFormularioNovaReservaConferenciaRemota() {
        return FabAcaoCRMCliente.RESERVAS_FRM_VISUALIZAR_REMOTO.getRegistro().getComoFormulario();
    }

    @Override
    public ItfAcaoFormulario getAcaoFormularioNovaReservaVisitaLocal() {
        return FabAcaoCRMCliente.RESERVAS_FRM_VISUALIZAR_PRESENCIAL.getRegistro().getComoFormulario();
    }

    public ComoChatSalaBean getCanalRocketChat() {

        return servicoMatrix.getSalaAtendimento(usuarioLogado.getRepresentanteLegal());

    }

    public UsuarioCrmCliente getUsuarioLogado() {
        return usuarioLogado;
    }

    public UsuarioCRM getUsrRepComercial() {
        return usrRepComercial;
    }

    public UsuarioCRM getUsrGestaoSucesso() {
        return usrGestaoSucesso;
    }

    public boolean isTemRepresentanteComercial() {
        return temRepresentanteComercial;
    }

    public boolean isTemGestorDeSucesso() {
        return temGestorDeSucesso;
    }

    public boolean isListarEntidadesLasyMode() {
        return listarEntidadesLasyMode;
    }

    public ItfAcaoFormularioEntidade getAcaoListarRegistros() {
        return acaoListarRegistros;
    }

    public ItfAcaoFormularioEntidade getAcaoNovoRegistro() {
        return acaoNovoRegistro;
    }

    public ComoAcaoControllerEntidade getAcaoSalvarAlteracoes() {
        return acaoSalvarAlteracoes;
    }

    public boolean isListarApenasRegistroCriadoAoListar() {
        return listarApenasRegistroCriadoAoListar;
    }

    public boolean isListarApenasRegistrosAtivos() {
        return listarApenasRegistrosAtivos;
    }

    public BP_DataModelLasy<ReservaHorarioCRM> getListaEntidadeLasy() {
        return listaEntidadeLasy;
    }

    @Override
    public ComoUsuario getUsuarioAtendente() {
        return getUsrAtendenteSelecionado();
    }

    public boolean isUmAtendidoConsultorVendas() {
        if (getUsuarioAtendedido() == null || getUsuarioAtendente() == null || getUsrGestaoSucesso() == null) {
            return false;
        }

        if (getUsrGestaoSucesso().equals(getUsuarioAtendente())) {
            return true;
        }

        return false;
    }

    public boolean isUmAtendidoGestorDeContrato() {
        if (getUsuarioAtendedido() == null || getUsuarioAtendente() == null || getUsrGestaoSucesso() == null) {
            return false;
        }

        if (getUsrGestaoSucesso().equals(getUsuarioAtendente())) {
            return true;
        }

        return false;
    }

    @Override
    public ComoUsuario getUsuarioAtendedido() {
        return getUsuarioLogado();
    }

    @Override
    public TipoAgendamentoAtdmPublico getTipoAgendamento() {
        if (getParametroInstanciado(prTipoReserva).isValorDoParametroFoiConfigurado()) {
            if (getEscopoPesquisa().getTiposAgendamentosDisponiveis().contains((TipoAgendamentoAtdmPublico) getParametroInstanciado(prTipoReserva).getValor())) {
                return (TipoAgendamentoAtdmPublico) getParametroInstanciado(prTipoReserva).getValor();
            }
        }
        if (getAgendaDisponibilidade() != null) {
            if (getAgendaDisponibilidade().getTipoAgendamento() != null) {
                return getAgendaDisponibilidade().getTipoAgendamento();
            }
        }
        return null;
    }

}
