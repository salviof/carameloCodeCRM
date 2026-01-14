/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHoraCalendarioLocal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda.MapaHorariosDisponiveis;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda.TokenLinhaDoTempoDeLorean;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.ErroAtingiuFinalLinhaDoTempoPermita;

/**
 *
 * @author salvio
 */
public class AgendaDisponibilidade {

    private EscopoPesquisaMelhorHorario escopo;
    private Date dataultimaAtualizacaoAgenda;
    private final List<Date> diasDesativados = new ArrayList<>();
    private LocalDate diaSelecionado;
    private boolean temAgenda;
    private final Map<LocalDate, List<HorarioDisponivelAtendimentoPublico>> mapaHorariosDisponiveis = new HashMap<>();
    private List<HorarioDisponivelAtendimentoPublico> horariosDisponiveis;

    public void loadgenda15Dias() throws ErroAtingiuFinalLinhaDoTempoPermita {

        if (dataultimaAtualizacaoAgenda == null) {
            dataultimaAtualizacaoAgenda = new Date();
        }
        if (temAgenda) {
            if (UtilCRCDataHora.intervaloTempoMinutos(dataultimaAtualizacaoAgenda, new Date()) > 5l) {
                return;
            }
        }

        escopo.setDataInicial(new Date());
        escopo.setDatahoraMaximoPesquisa(UtilCRCDataHora.incrementaDias(new Date(), 40));
        mapaHorariosDisponiveis.clear();
        final DateTimeFormatter formatoMundialExcetoIngleses = DateTimeFormatter.ofPattern("dd/MM/yy");
        try {
            TokenLinhaDoTempoDeLorean delorean = new TokenLinhaDoTempoDeLorean(escopo, false, 60);
            try {
                while (delorean.saltarProximoHorarioDisponivel() != null) {

                    HorarioDisponivelAtendimentoPublico horarioDisponivel = delorean.getHorarioMomentoAtual();

                    if (!MapaHorariosDisponiveis.isHorarioBloqueadoPelasReservas(horarioDisponivel)) {
                        if (!mapaHorariosDisponiveis.containsKey(delorean.getMomentoAtualDia())) {
                            mapaHorariosDisponiveis.put(delorean.getMomentoAtualDia(), new ArrayList<>());
                        }
                        delorean.getMomentoAtualDia().format(formatoMundialExcetoIngleses);
                        mapaHorariosDisponiveis.get(delorean.getMomentoAtualDia()).add(horarioDisponivel);

                    }

                }
            } catch (ErroAtingiuFinalLinhaDoTempoPermita t) {
                if (mapaHorariosDisponiveis.isEmpty()) {
                    throw t;
                }
            }
            if (diaSelecionado == null) {
                Optional<LocalDate> pesquisaDatalocal = mapaHorariosDisponiveis.keySet().stream().findFirst();
                if (pesquisaDatalocal.isPresent()) {
                    diaSelecionado = pesquisaDatalocal.get();

                    diaSelecionado.format(formatoMundialExcetoIngleses);
                }

            }
            Date primeirodiaDoMes = UtilCRCDataHora.getPrimeiroDiaDoMes(new Date());

            Date diaDoMes = UtilCRCDataHora.getPrimeiroDiaDoMes(new Date());
            boolean fimPesquisaDiasDesativados = false;
            diasDesativados.clear();
            Date datainicialDesativacao = UtilCRCDataHora.decrementarDias(new Date(), 60);
            Date dataFinalDesativacao = UtilCRCDataHora.incrementaDias(escopo.getDatahoraMaximoPesquisa(), 60);
            while (!fimPesquisaDiasDesativados) {

                diaDoMes = UtilCRCDataHora.incrementaDias(diaDoMes, 1);
                if (!mapaHorariosDisponiveis.containsKey(UtilCRCDataHoraCalendarioLocal.gerarLocalDateByDate(diaDoMes))) {

                    diasDesativados.add(diaDoMes);
                }

                fimPesquisaDiasDesativados = !UtilCRCDataHora.isMesFazParteDoIntevalo(diaDoMes, datainicialDesativacao, dataFinalDesativacao);
            }
            temAgenda = true;
            if (!mapaHorariosDisponiveis.isEmpty()) {
                setDiaSelecionado(mapaHorariosDisponiveis.keySet().stream().sorted().findFirst().get());

            }

        } catch (ErroAtingiuFinalLinhaDoTempoPermita ex) {
            if (mapaHorariosDisponiveis.isEmpty()) {
                throw ex;
            }
        }

    }

    public Date getDiaSelecionadoComoData() {

        return UtilCRCDataHoraCalendarioLocal.gerarDateByLocalDate(getDiaSelecionado());
    }

    public EscopoPesquisaMelhorHorario getEscopo() {
        return escopo;
    }

    public void setEscopo(EscopoPesquisaMelhorHorario escopo) {
        this.escopo = escopo;
    }

    public LocalDate getDiaSelecionado() {
        return diaSelecionado;
    }

    public void setDiaSelecionado(LocalDate diaSelecionado) {
        this.diaSelecionado = diaSelecionado;
    }

    public List<Date> getDiasDesativados() {
        return diasDesativados;
    }

    public boolean isTemAgenda() {
        if (getEscopo() == null) {
            temAgenda = false;

        } else if (getEscopo().getTipoAgendamento() == null) {
            temAgenda = false;
        } else {
            temAgenda = !mapaHorariosDisponiveis.isEmpty();
        }

        return temAgenda;
    }

    public List<HorarioDisponivelAtendimentoPublico> getHorariosDisponiveis() {

        if (!isTemAgenda()) {

            return new ArrayList<>();
        }
        //if (horariosDisponiveis == null || horariosDisponiveis.isEmpty()) {
        //  horariosDisponiveis = (List) escopoPesquisa.getCampoInstanciadoByNomeOuAnotacao(CPEscopoPesquisaMelhorHorario.listahorariosdisponiveis).getValor();
        // }
        horariosDisponiveis = mapaHorariosDisponiveis.get(diaSelecionado);

        return horariosDisponiveis;
    }

}
