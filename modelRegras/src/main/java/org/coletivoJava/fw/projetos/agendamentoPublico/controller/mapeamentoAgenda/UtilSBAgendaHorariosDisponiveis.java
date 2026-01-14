/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import java.sql.Time;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.CPReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
public abstract class UtilSBAgendaHorariosDisponiveis {

    private static List<HorarioDisponivelAtendimentoPublico> reservaTemporaria;
    private static List<ReservaHorario> reservasAtualizadas;
    private static final int segundosReservaTemporaria = 900;//15 minutos

    public static synchronized void atualizarReservas() {
        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ReservaHorario.class, em);
        consulta.addCondicaoDataHoraMaiorOuIgualA(CPReservaHorario.inicioreservaatendente, new Date());
        consulta.setOrdemCampoPersonalizado(CPReservaHorario.inicioreservaatendente);
        List<ReservaHorario> resercvas = consulta.resultadoRegistros();
        resercvas.parallelStream().forEach(rs -> rs.getAtendenteResponsavel());
        reservasAtualizadas = resercvas;
        resercvas.forEach(MapaHorariosDisponiveis::adicionarReservaAtendente);
        //ordernar por data inicial
        UtilSBPersistencia.fecharEM(em);
    }

    public synchronized void registrarReservaTemporaria(HorarioDisponivelAtendimentoPublico pHorario) {

    }

    public static boolean isHorarioConflitaComReserva(HorarioDisponivelAtendimentoPublico pHorario, ReservaHorario pReserva) {

        return MapaHorariosDisponiveis.isHorarioDisponivelBloqueadoPelaReserva(pHorario, pReserva);

    }

    public static boolean isHorarioOcupadoPorReserva(HorarioDisponivelAtendimentoPublico pHorario) {
        if (reservasAtualizadas == null || reservasAtualizadas.isEmpty()) {
            atualizarReservas();
        }
        for (ReservaHorario reserva : reservasAtualizadas) {

            if (pHorario.getDataHoraIicialAtendente().getTime() >= reserva.getFinalReservaAtendente().getTime()) {

                return false;
            }
            if (isHorarioConflitaComReserva(pHorario, reserva)) {
                if (!SBCore.isEmModoProducao()) {
                    System.out.println("Negado por conter reserva no mesmo horário " + UtilCRCDataHora.getDataSTRFormatoUsuario(pHorario.getDataHoraIicialAtendente()));
                }
                return true;
            }
        }
        return false;

    }

    public static boolean isDisponibilidadeDentroDoEscopo(EscopoPesquisaMelhorHorario pEscopo, DisponibilidadeAtdmtPublico pDisponibilidade) {
        Date dataInicialEscopo = pEscopo.getDataInicial();
        Date dataFinalEscopo = (Date) pEscopo.getCPinst(CPEscopoPesquisaMelhorHorario.datahoramaximopesquisa).getValor();
        if (pDisponibilidade.getDiaInicial().getTime() >= dataFinalEscopo.getTime()) {
            return false;
        }
        if (pDisponibilidade.getDiaFinal().getTime() <= dataInicialEscopo.getTime()) {
            return false;
        }
        if (pDisponibilidade.getDiasDaSemana().equals("0000000")) {
            return false;
        }
        if (!pDisponibilidade.getTiposAgendamentosPublicos().contains(pEscopo.getTipoAgendamento())) {
            return false;
        }
        return pDisponibilidade.getAtendentesDisponiveis().get(0).equals(pEscopo.getAtendentes().get(0));

    }

    public static boolean isHorarioDentroDasDisponibilidades(List<DisponibilidadeAtdmtPublico> disponibilidades, HorarioDisponivelAtendimentoPublico pHorario) {
        boolean disponibilidadeEncontrada = disponibilidades.stream().
                filter(disp -> isHorarioDisponibilidade(pHorario, disp)).findFirst().isPresent();
        return disponibilidadeEncontrada;
    }

    public static boolean isHorarioDisponibilidade(HorarioDisponivelAtendimentoPublico pHorario, DisponibilidadeAtdmtPublico pDisponibilidade) {
        try {
            Date inicio = pHorario.getDataHoraIicialAtendente();
            Date fim = pHorario.getDatahoraFinalAtendente();
            if (SBCore.isEmModoDesenvolvimento()) {
                System.out.println("Analizando disponibilidade para agen em " + UtilCRCDataHora.getDataHoraString(fim, UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO));
            }
            if (inicio.getTime() < pDisponibilidade.getDiaInicial().getTime() || inicio.getTime() > pDisponibilidade.getDiaFinal().getTime()) {
                if (SBCore.isEmModoDesenvolvimento()) {
                    System.out.println("não existem escopos de disponibilidades válidos neste perído");
                }
                return false;
            }
            LocalTime inicioHorarioReserva = inicio
                    .toInstant()
                    .atZone(ZoneId.of("America/Sao_Paulo")
                    ).toLocalTime();
            LocalTime fimHoraReserva = fim
                    .toInstant()
                    .atZone(ZoneId.of("America/Sao_Paulo")
                    ).toLocalTime();
            LocalTime inicioDisponibilidade = null;
            Class teste = pDisponibilidade.getHorarioInicio().getClass();
            System.out.println(teste.getCanonicalName());

            Time horainidispo = pDisponibilidade.getHorarioInicioComoTime();
            inicioDisponibilidade = horainidispo.toLocalTime();

            LocalTime fimHoraDisponibilidade = null;

            Time horafimdispo = (Time) pDisponibilidade.getHorarioFinalComoTime();
            fimHoraDisponibilidade = horafimdispo.toLocalTime();

            int reservaAntecedenciaMinima = pHorario.getTipoAgendamento().getAntecedenciaNovaReservaMinutos();
            Date dataHorainicioPermitido = UtilCRCDataHora.incrementaMinutos(new Date(), reservaAntecedenciaMinima);
            if (dataHorainicioPermitido.getTime() >= pHorario.getDatahoraFinalAtendente().getTime()
                    || dataHorainicioPermitido.getTime() >= pHorario.getDataHoraIicialAtendente().getTime()) {
                if (!SBCore.isEmModoProducao()) {
                    System.out.println("Negado devido a horas de antecedência nescessário");
                }
                return false;
            }
            if (inicioHorarioReserva.compareTo(inicioDisponibilidade) >= 0
                    && fimHoraReserva.compareTo(fimHoraDisponibilidade) <= 0) {
                if (!SBCore.isEmModoProducao()) {
                    System.out.println("OK dentro do escopo código " + pDisponibilidade.getId());
                }
                return true;
            }
            return false;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "falha verificando conformidade da disponibilidade com horario", t);
            throw new UnsupportedOperationException("FAlha verificando conformidade da disponibilidade com horario");
        }
    }

}
