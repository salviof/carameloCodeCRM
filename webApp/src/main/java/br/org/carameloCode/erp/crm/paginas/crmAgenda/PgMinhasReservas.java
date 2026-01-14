/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAgenda;

import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.CPReservaHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.CPUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.FabAcaoCrmAtendimentoAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.InfoAcaoCRMAgenda;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.ModuloAgendamentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabStatusReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraPresencial;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraRemotoVideo;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.FabTipoAgendamentoPublicoCrm;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author sfurbino
 */
@InfoPagina(nomeCurto = "mreservas", tags = "Minhas reservas")
@ViewScoped
@Named
@InfoAcaoCRMAgenda(acao = FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_MB_GESTAO)
public class PgMinhasReservas
        extends MB_paginaCadastroEntidades<ReservaHorario> implements ItfPaginaListaDeHorariosDisponiveis {

    @InfoParametroURL(nome = "Usuarário da agenda", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = UsuarioCRM.class, obrigatorio = false)
    private ParametroURL prUsuario;

    @InfoParametroURL(nome = "reserva", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = ReservaHorario.class, obrigatorio = false, representaEntidadePrincipalMB = true)
    private ParametroURL prReserva;

    private UsuarioCRM usuario;

    private ScheduleModel agendaReservas;
    private EscopoPesquisaMelhorHorario escopoPesquisa;
    private List<HorarioDisponivelAtendimentoPublico> horariosDisponiveis;
    private HorarioDisponivelAtendimentoPublico horarioDisponivelSelecionado;

    @Override
    public ReservaHorario getEntidadeSelecionada() {
        if (super.getEntidadeSelecionada() == null) {
            if (getHorarioDisponivelSelecionado() != null) {
                autoexecEntidadeNova();
            }
        }
        return super.getEntidadeSelecionada(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void renovarEMPagina() {
        super.renovarEMPagina(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void autoexecEntidadeNova() {
        if (getHorarioDisponivelSelecionado() != null) {
            if (getHorarioDisponivelSelecionado().getTipoAgendamento().isUmAtendimentoRemoto()) {
                setEntidadeSelecionada(new ReservaHoraRemotoVideo());
            } else {
                setEntidadeSelecionada(new ReservaHoraPresencial());
            }
            try {
                getEntidadeSelecionada().prepararNovoObjeto(getHorarioDisponivelSelecionado());
            } catch (ErroPreparandoObjeto ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha definindo parametros iniciais da reserva a partir dos horarios disponíveis", ex);
            }
        } else {
            setEntidadeSelecionada(null);
        }

    }

    @PostConstruct
    public void inicio() {

        UsuarioCRM usuarioLogado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
        if (getParametroInstanciado(prUsuario).isValorDoParametroFoiConfigurado()) {
            if (usuarioLogado.getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
                usuario = (UsuarioCRM) getParametroInstanciado(prUsuario).getValor();
            }
        }

        if (usuario == null) {
            usuario = usuarioLogado;
        }

        escopoPesquisa = new EscopoPesquisaMelhorHorario();
        escopoPesquisa.adicionarAtendente(usuario);
        escopoPesquisa.setDiasDaSemana("1111111");
        escopoPesquisa.setTipoAgendamento(FabTipoAgendamentoPublicoCrm.CONFERENCIA.getRegistro());
        escopoPesquisa.adicionarAtendente((UsuarioSB) SBCore.getUsuarioLogado());
        try {
            escopoPesquisa.setHorarioinicio(new SimpleDateFormat("hh:mm").parse("5:00"));
            escopoPesquisa.setHorarioFinal(new SimpleDateFormat("hh:mm").parse("23:00"));
        } catch (ParseException t) {
            System.out.println("Para de show velhinho");
        }
        escopoPesquisa.setDataInicial(new Date());

    }

    private void buildAgenda() {

    }

    public ScheduleModel getAgendaReservas() {
        if (agendaReservas == null) {
            agendaReservas = new DefaultScheduleModel();
            ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ReservaHorario.class, getEMPagina());
            consulta.addCondicaoManyToOneIgualA(CPReservaHorario.atendenteresponsavel, usuario);
            consulta.addCondicaoDataHoraMaiorOuIgualA(CPReservaHorario.inicioreservaatendente, UtilCRCDataHora.decrementarDias(new Date(), 1));

            List<ReservaHorario> reservas = consulta.resultadoRegistros();

            reservas.stream().forEach(reserva -> {

                DefaultScheduleEvent<?> novoEvento = DefaultScheduleEvent.builder()
                        .title(reserva.getPessoaRelacionada().getNome()
                                + " com " + reserva.getAtendidoResponsavel().getNome()
                                + " Tipo:" + reserva.getTipoAgendamento().getNome()
                        )
                        .startDate(LocalDateTime.ofInstant(reserva.getInicioReservaAtendente().toInstant(),
                                ZoneId.systemDefault()))
                        .endDate(LocalDateTime.ofInstant(reserva.getFinalReservaAtendente().toInstant(),
                                ZoneId.systemDefault()))
                        .description(reserva.getDescricao())
                        .url(SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_VER_RESERVA, reserva, usuario))
                        .borderColor("orange")
                        .build();
                agendaReservas.addEvent(novoEvento);

            });

            buildAgenda();
        }
        return agendaReservas;
    }

    public List<HorarioDisponivelAtendimentoPublico> getHorariosDisponiveisAtendimentoPublico() {
        if (horariosDisponiveis == null || horariosDisponiveis.isEmpty()) {
            atualizarListaHorariosDisponiveis();
        }
        return horariosDisponiveis;
    }

    public EscopoPesquisaMelhorHorario getEscopoPesquisa() {
        return escopoPesquisa;
    }

    public void atualizarListaHorariosDisponiveis() {
        horariosDisponiveis = (List) escopoPesquisa.getCPinst(CPEscopoPesquisaMelhorHorario.listahorariosdisponiveis).getValor();
    }

    @Override
    public HorarioDisponivelAtendimentoPublico getHorarioDisponivelSelecionado() {
        return horarioDisponivelSelecionado;
    }

    @Override
    public void setHorarioDisponivelSelecionado(HorarioDisponivelAtendimentoPublico horarioDisponivelSelecionado) {
        this.horarioDisponivelSelecionado = horarioDisponivelSelecionado;
    }

    @Override
    public ItfAcaoFormulario getAcaoFormularioNovaReservaConferenciaRemota() {
        return FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_EDITAR_RESERVA_CONFERENCIA.getRegistro().getComoFormulario();
    }

    @Override
    public ItfAcaoFormulario getAcaoFormularioNovaReservaVisitaLocal() {
        return FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_EDITAR_RESERVA_NO_LOCAL.getRegistro().getComoFormulario();
    }

    public LocalDate getHojeDataLocal() {
        return LocalDate.now();
    }

    public UsuarioCRM getUsuario() {
        return usuario;
    }

    public EscopoPesquisaMelhorHorario getEscopoAgendaCliente() {
        return (EscopoPesquisaMelhorHorario) usuario.getCampoInstanciadoByNomeOuAnotacao(CPUsuarioCRM.escopoagendaclientes).getValor();
    }

    public void salvarEscopoReservaCliente() {
        ItfRespostaAcaoDoSistema resposta = ModuloAgendamentoPublico.atualizarMeuEscopoClientePadrao(usuario);
        resposta.dispararMensagens();
        if (resposta.isSucesso()) {
            UtilSBWP_JSFTools.vaParaPaginaInicial();
        }
    }

    public synchronized ReservaHorario getNovaReserva() {
        if (getEntidadeSelecionada() == null) {
            setEntidadeSelecionada(new ReservaHoraRemotoVideo());
            getEntidadeSelecionada().setStatus(FabStatusReservaHorario.AGENDADO.getRegistro());
            getEntidadeSelecionada().setAtendidoResponsavel((UsuarioCrmCliente) getUsuario());
            getEntidadeSelecionada().setPessoaRelacionada(((UsuarioCrmCliente) getUsuario()).getContatoClienteVinculado().getProspecto());
            //setTipoAgendamento(pHorario.getTipoAgendamento());
            getEntidadeSelecionada().setAtendenteResponsavel((UsuarioSB) SBCore.getUsuarioLogado());
            //setInicioReservaAtendente(pHorario.getDataHoraIicialAtendente());
            //setFinalReservaAtendente(pHorario.getDatahoraFinalAtendente());
        }
        return getEntidadeSelecionada();
    }

}
