/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHoraCalendarioLocal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.diaDaSemana.DiasDaSemana;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.ErroAtingiuFinalLinhaDoTempoPermita;

/**
 *
 * Token para navegar entre datas, utilizado nos métodos de pesquisa, O nome
 * deLorean, é uma homenagem a triologia back to the future
 *
 *
 * @author sfurbino
 */
public class TokenLinhaDoTempoDeLorean {

    private Date ultimaDataAnalizada;
    private Calendar momentoAtual;
    private Date proximaDataAnalizada;

    private DayOfWeek momentoAtualDiaDaSemana;
    private LocalTime momentoAtualHora;
    private LocalDate momentoAtualDia;
    private final EscopoPesquisaMelhorHorario escopo;
    private final List<IntervaloHorariosDoDia> intervalosHorariosDisponiveisNoDia = new ArrayList<>();

    private final List<HorarioDisponivelAtendimentoPublico> horariosDoDia = new ArrayList();
    private HorarioDisponivelAtendimentoPublico horarioMomentoAtual;
    private int indiceHorarioMomentoAtual = -1;
    private final int qtdMaximaSaltos;
    private int quantidadeSaltosParaOFuturo = 0;

    final Date dataHoraMaximoPesquisa;
    final List<DisponibilidadeAtdmtPublico> disponibilidades;

    /**
     *
     * @param pEscopo
     * @param pCarregarPrimeiroHorarioDisponivel Melhor não carregar o primeiro
     * Horário disponivel se for usar um while(token.getProximoHorario)
     */
    public TokenLinhaDoTempoDeLorean(EscopoPesquisaMelhorHorario pEscopo, boolean pCarregarPrimeiroHorarioDisponivel, int pQtdMaximaSaltos) throws ErroAtingiuFinalLinhaDoTempoPermita {

        escopo = pEscopo;
        qtdMaximaSaltos = pQtdMaximaSaltos;

        if (pEscopo.getTipoAgendamento() == null) {
            throw new UnsupportedOperationException("Tipo de agendamento não definido no escopo");
        }
        if (pEscopo.getTipoAgendamento().getDuracaoAtendenteMinutos() < 1) {
            throw new UnsupportedOperationException("A duração do " + pEscopo.getTipoAgendamento().getNome() + " não foi definida");
        }
        if (pEscopo.getAtendentes() == null || pEscopo.getAtendentes().isEmpty() || pEscopo.getAtendentes().size() > 1) {
            throw new UnsupportedOperationException("Atendente não definido no escopo, nesta versão o sistema não suporta multiplos atendentes, ou reserva em contexto de local");
        }
        disponibilidades = (List) escopo.getCPinst(CPEscopoPesquisaMelhorHorario.disponibilidadesdoescopo).getValor();
        dataHoraMaximoPesquisa = (Date) escopo.getCPinst(CPEscopoPesquisaMelhorHorario.datahoramaximopesquisa).getValor();

        pEscopo.getCPinst(CPEscopoPesquisaMelhorHorario.datainicial).getValor();
        try {
            UtilSBAgendaHorariosDisponiveis.atualizarReservas();

            loadPrimeiroDiaDisponivel(pEscopo.getDataInicial());

            if (pCarregarPrimeiroHorarioDisponivel) {
                loadHorariosDoDia();
            } else {
                return;
            }

        } catch (ErroAtingiuFinalLinhaDoTempoPermita t) {
            momentoAtual = null;
            momentoAtualDia = null;
            momentoAtualHora = null;
            momentoAtualDiaDaSemana = null;
            throw new ErroAtingiuFinalLinhaDoTempoPermita("Atingiu o final permitido");
        }
    }

    public TokenLinhaDoTempoDeLorean(EscopoPesquisaMelhorHorario pEscopo) throws ErroAtingiuFinalLinhaDoTempoPermita {
        this(pEscopo, false, 10);
    }

    private boolean isDiaAtualDisponivel() {
        List<DisponibilidadeAtdmtPublico> disponibilidadesDoDia = new ArrayList();

        disponibilidades.stream().filter(dispomibilidade -> isDisponibilidadeCompativelComDiaAtual(dispomibilidade))
                .forEach(disponibilidadesDoDia::add);
        return !disponibilidadesDoDia.isEmpty();
    }

    public void saltarProximoDiaDisponivel() throws ErroAtingiuFinalLinhaDoTempoPermita {
        setMomentoAtual(UtilCRCDataHora.incrementaDias(momentoAtual.getTime(), 1));
        horariosDoDia.clear();
        indiceHorarioMomentoAtual = -1;

        if (momentoAtual.getTime().getTime() >= dataHoraMaximoPesquisa.getTime()) {

            throw new ErroAtingiuFinalLinhaDoTempoPermita(UtilCRCDataHora.gerarStringDiaMesAnoAtual() + " ultrapassa o limite de pesquisa");
        }
        if (!isDiaAtualDisponivel()) {
            saltarProximoDiaDisponivel();
        }

    }

    /**
     * Salta para o próximo horário permitido, caso não haja mais horário no
     * dia, salta dias, até encontrar o proximo horário, se atingir o final da
     * linha do tempo, lança a exceção ErroAtingiuFinalLinhaDoTempoPermita
     *
     * Esse método também altera o momento atual, sendo assim o resultado pode
     * ser obtido a qualquer momento chamando getHorarioMomentoAtual()
     *
     * @return Um objeto: HorarioDisponivelAtendimentoPublico representando o
     * próximo horário if (quantidadeSaltosParaOFuturo >= qtdMaximaSaltos) {
     * return null; } if (horariosDoDia.isEmpty()) { indiceHorarioMomentoAtual =
     * -1;
     * @throws ErroAtingiuFinalLinhaDoTempoPermita
     */
    public HorarioDisponivelAtendimentoPublico saltarProximoHorarioDisponivel() throws ErroAtingiuFinalLinhaDoTempoPermita {
        if (quantidadeSaltosParaOFuturo >= qtdMaximaSaltos) {
            return null;
        }
        if (horariosDoDia.isEmpty()) {
            indiceHorarioMomentoAtual = -1;
            //  setMomentoAtual(horarioMomentoAtual.getDataHoraIicialAtendente());
            loadHorariosDoDia();
        }
        // SE já houver percorrido, todos os horários do dia, vai para o proximo dia
        if (horariosDoDia.isEmpty() || indiceHorarioMomentoAtual > horariosDoDia.size() - 1) {

            saltarProximoDiaDisponivel();
            return saltarProximoHorarioDisponivel();

        } else {

            quantidadeSaltosParaOFuturo++;
            horarioMomentoAtual = horariosDoDia.get(indiceHorarioMomentoAtual);
            indiceHorarioMomentoAtual++;
            setMomentoAtual(horarioMomentoAtual.getDataHoraIicialAtendente());
            return horarioMomentoAtual;
        }

    }

    public HorarioDisponivelAtendimentoPublico getHorarioMomentoAtual() {
        return horarioMomentoAtual;
    }

    private void loadHorariosDoDia() {

        List<DisponibilidadeAtdmtPublico> disponibilidadesDoEscopo = (List) escopo.getCPinst(CPEscopoPesquisaMelhorHorario.disponibilidadesdoescopo).getValor();
        List<DisponibilidadeAtdmtPublico> disponibilidadesDoDia = new ArrayList();
        if (!SBCore.isEmModoProducao()) {
            System.out.println("Pesquisando melhores horários no dia " + UtilCRCDataHora.converteDateEmSTringDD_MM_YY(momentoAtual.getTime()));
        }
        intervalosHorariosDisponiveisNoDia.clear();
        if (momentoAtual == null) {
            return;
        }
        //  disponibilidades.stream().filter(predicate)
        disponibilidadesDoEscopo.stream().filter(disp -> isDisponibilidadeCompativelComDiaAtual(disp)).forEach(disponibilidadesDoDia::add);
        if (!disponibilidadesDoDia.isEmpty()) {
            System.out.println("OK");
        }

        Collections.sort(disponibilidadesDoDia, new ComparadorDisponibilidadeOrdemHorario());
        int indice = 0;
        for (DisponibilidadeAtdmtPublico disponibilidadeDoDia : disponibilidadesDoDia) {
            if (indice == 0) {
                IntervaloHorariosDoDia novoIntevalo = new IntervaloHorariosDoDia();
                novoIntevalo.setHorarioIniicial(disponibilidadeDoDia.getHorarioInicio());
                novoIntevalo.setHorarioFinal(disponibilidadeDoDia.getHorarioFim());
                intervalosHorariosDisponiveisNoDia.add(novoIntevalo);
            } else {
                IntervaloHorariosDoDia ultimointervalo = intervalosHorariosDisponiveisNoDia.get(intervalosHorariosDisponiveisNoDia.size() - 1);
                LocalTime horarioFinallDoUltimoIntervalor = ultimointervalo.getHorarioFinal();
                LocalTime horarioInicialDoNovoIntervalo = UtilCRCDataHoraCalendarioLocal.gerarLocalTimeByDate(disponibilidadeDoDia.getHorarioInicio());
                if (horarioInicialDoNovoIntervalo.compareTo(horarioFinallDoUltimoIntervalor) <= 0) {
                    ultimointervalo.setHorarioFinal(disponibilidadeDoDia.getHorarioFim());
                } else {
                    IntervaloHorariosDoDia novoIntevalo = new IntervaloHorariosDoDia();
                    novoIntevalo.setHorarioIniicial(disponibilidadeDoDia.getHorarioInicio());
                    novoIntevalo.setHorarioFinal(disponibilidadeDoDia.getHorarioFim());
                    intervalosHorariosDisponiveisNoDia.add(novoIntevalo);
                }
            }

            indice++;
        }
        indiceHorarioMomentoAtual = 0;
        int duracaoAtendimento = escopo.getTipoAgendamento().getDuracaoAtendenteMinutos();
        for (IntervaloHorariosDoDia intervalo : intervalosHorariosDisponiveisNoDia) {
            intervalo.getHorarioInicial();
            intervalo.getHorarioFinal();

            LocalTime horarioAnalizado = intervalo.getHorarioInicial();

            while ((horarioAnalizado.compareTo(intervalo.getHorarioFinal()) < 0)) {
                if (!SBCore.isEmModoProducao()) {
                    System.out.println("Analizando horário" + horarioAnalizado.toString());
                }
                Calendar calendarioDataHoraBuilder = Calendar.getInstance();
                calendarioDataHoraBuilder.clear();

                //Olha só a api Calendar vacilando: retorna o mès começando a contar do 1 (janeiro é 1) em momentoAtualDia.getMonthValue() mas na hora de um set,
                //pede o valor contando  a partir do 0 (janeiro é 0)
                calendarioDataHoraBuilder.set(momentoAtualDia.getYear(), momentoAtualDia.getMonthValue() - 1, momentoAtualDia.getDayOfMonth(),
                        horarioAnalizado.getHour(), horarioAnalizado.getMinute(), 0);
                HorarioDisponivelAtendimentoPublico novoHorarioDisponivel = new HorarioDisponivelAtendimentoPublico();

                novoHorarioDisponivel.setEscopo(escopo);
                //todo condigurar hora do encontro
                //Analizar viabilidade de 3 tipos de horário de atendimento, reserva no inicio, reserva ao final, e distribuido
                Date datahorainicial = calendarioDataHoraBuilder.getTime();
                novoHorarioDisponivel.setDataHoraIicialAtendente(datahorainicial);

                LocalTime horarioFInalAtendimento = null;
                LocalTime novoHorarioFinalAtendimento = horarioAnalizado.plusMinutes(duracaoAtendimento);
                if (novoHorarioFinalAtendimento.isAfter(horarioAnalizado)) {
                    horarioFInalAtendimento = novoHorarioFinalAtendimento;
                } else {

                    horarioAnalizado = intervalo.getHorarioFinal();
                    continue;
                }

                Date datahoraFinal = UtilCRCDataHora.incrementaMinutos(datahorainicial, duracaoAtendimento);
                novoHorarioDisponivel.setDatahoraFinalAtendente(datahoraFinal);
                novoHorarioDisponivel.setTipoAgendamento(escopo.getTipoAgendamento());
                novoHorarioDisponivel.setUsuarioResponsavel((UsuarioCRM) escopo.getAtendentes().get(0));

                if (horarioFInalAtendimento.compareTo(intervalo.getHorarioFinal()) <= 1) {
                    if (UtilSBAgendaHorariosDisponiveis.isHorarioDentroDasDisponibilidades(disponibilidadesDoDia,
                            novoHorarioDisponivel)) {
                        if (!UtilSBAgendaHorariosDisponiveis.isHorarioOcupadoPorReserva(novoHorarioDisponivel)) {
                            horariosDoDia.add(novoHorarioDisponivel);
                        }
                    } else {
                        if (!SBCore.isEmModoProducao()) {
                            System.out.println("não existe disponibilidade cadastrada que contemple o horário" + UtilCRCDataHora.getDataHoraString(novoHorarioDisponivel.getDataHoraIicialAtendente(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO));
                        }
                    }
                    if (horarioMomentoAtual == null) {
                        if (novoHorarioDisponivel.getDataHoraIicialAtendente().getTime() >= momentoAtual.getTimeInMillis()) {
                            if (!horariosDoDia.isEmpty()) {

                                horarioMomentoAtual = horariosDoDia.get(horariosDoDia.size() - 1);
                            }
                        }

                    }

                } else {
                    System.out.println("Horário final do atendimento às " + horarioFInalAtendimento + " supera a hora final do itervalor:" + intervalo.getHorarioFinal());
                }

                if (!horariosDoDia.isEmpty()) {
                    int intevaloNescessario = escopo.getTipoAgendamento().getMinutosAnteriorAReserva();
                    HorarioDisponivelAtendimentoPublico horarioAnterior = horariosDoDia.get(horariosDoDia.size() - 1);
                    long diferenca = UtilCRCDataHora.intervaloTempoMinutos(novoHorarioDisponivel.getDataHoraIicialAtendente(), horarioAnterior.getDatahoraFinalAtendente());
                    if (diferenca < intevaloNescessario) {
                        LocalTime novoHorario = horarioFInalAtendimento.plusMinutes(intevaloNescessario);
                        if (novoHorario.isAfter(horarioAnalizado)) {
                            horarioAnalizado = horarioFInalAtendimento.plusMinutes(intevaloNescessario);
                        } else {
                            horarioAnalizado = intervalo.getHorarioFinal();
                            continue;
                        }
                    } else {
                        horarioAnalizado = horarioFInalAtendimento;
                    }

                } else {
                    horarioAnalizado = horarioFInalAtendimento;
                }
            }

        }
    }

    private void loadPrimeiroDiaDisponivel(Date pDiaInicial) throws ErroAtingiuFinalLinhaDoTempoPermita {
        boolean respeitouLimiteReservaAntecedencia;
        respeitouLimiteReservaAntecedencia = false;
        Date diainicial = pDiaInicial;
        Date limite = UtilCRCDataHora.incrementaMinutos(new Date(), escopo.getTipoAgendamento().getAntecedenciaNovaReservaMinutos());
        while (!respeitouLimiteReservaAntecedencia) {
            if (diainicial.getTime() >= limite.getTime()) {
                respeitouLimiteReservaAntecedencia = true;
            } else {
                diainicial = UtilCRCDataHora.incrementaDias(diainicial, 1);

            }
        }

        setMomentoAtual(pDiaInicial);
        if (!isDiaAtualDisponivel()) {
            saltarProximoDiaDisponivel();
        }
    }

    private void setMomentoAtual(Date pData) {
        Calendar c = Calendar.getInstance();
        c.setTime(pData);
        setMomentoAtual(c);
    }

    private void setMomentoAtual(Calendar pMomentoAtual) {
        ultimaDataAnalizada = pMomentoAtual.getTime();

        momentoAtual = pMomentoAtual;
        int idx = pMomentoAtual.get(Calendar.DAY_OF_WEEK) - 1;
        if (idx == 0) {
            idx = 7;
        }
        momentoAtualDiaDaSemana = DayOfWeek.of(idx);
        momentoAtualDia = LocalDateTime.ofInstant(pMomentoAtual.toInstant(), pMomentoAtual.getTimeZone().toZoneId()).toLocalDate();
        momentoAtualHora = LocalDateTime.ofInstant(pMomentoAtual.toInstant(), pMomentoAtual.getTimeZone().toZoneId()).toLocalTime();
        if (!UtilCRCDataHora.isDiaIgual(ultimaDataAnalizada, momentoAtual.getTime())) {
            loadHorariosDoDia();
        }
    }

    private boolean isDisponibilidadeCompativelComDiaAtual(DisponibilidadeAtdmtPublico pDisponibilidade) {
        if (pDisponibilidade.getDiasDaSemana() == null) {
            System.out.println("Dias de semana inválido");
        }
        DiasDaSemana dias = new DiasDaSemana(pDisponibilidade.getDiasDaSemana());
        if (!dias.isDiaDaSemanaSelecionado(momentoAtualDiaDaSemana)) {
            return false;
        }
        LocalDate inicioDisponibilidade = UtilCRCDataHoraCalendarioLocal.gerarLocalDateByDate(pDisponibilidade.getDiaInicial());
        LocalDate fimDisponibilidade = UtilCRCDataHoraCalendarioLocal.gerarLocalDateByDate(pDisponibilidade.getDiaFinal());
        //momentoAtualDia maior que o dia incial
        if (inicioDisponibilidade.compareTo(momentoAtualDia) > 0) {
            return false;
        }
        // momentoAtualDia menor que o dia atual
        if (fimDisponibilidade.compareTo(momentoAtualDia) < 0) {
            return false;
        }

        return true;
    }

    public Date getUltimaDataAnalizada() {
        return ultimaDataAnalizada;
    }

    public Date getProximaDataAnalizada() {
        return proximaDataAnalizada;
    }

    public DayOfWeek getMomentoAtualDiaDaSemana() {
        return momentoAtualDiaDaSemana;
    }

    public LocalTime getMomentoAtualHora() {
        return momentoAtualHora;
    }

    public LocalDate getMomentoAtualDia() {
        return momentoAtualDia;
    }

    public EscopoPesquisaMelhorHorario getEscopo() {
        return escopo;
    }

}
