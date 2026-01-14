/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Localidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.ErroAtingiuFinalLinhaDoTempoPermita;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
public class MapaHorariosDisponiveis {

    private static final Map<UsuarioSB, List<DisponibilidadeAtdmtPublico>> mapadisponibilidadesPorAtendente = new HashMap();
    private static final Map<TipoAgendamentoAtdmPublico, List<DisponibilidadeAtdmtPublico>> tipoAgendamento = new HashMap();

    private static final Map<UsuarioSB, List<ReservaHorario>> mapaReservasPorAtendente = new HashMap();
    private static final Map<Localidade, List<ReservaHorario>> reservaDeHorarioPorLocal = new HashMap();
    private static final List<IntervaloHorariosDoDia> intervalosDeHoraDoDia = new ArrayList();

    public static synchronized void loadReservasEDisponibilidadesPersistidos() {

        //todo faz sentido REservas do passado?
        List<ReservaHorario> listaCompletaReservas = UtilSBPersistencia.getListaTodos(ReservaHorario.class, UtilSBPersistencia.getEntyManagerPadraoNovo());
        EntityManager emDisponibilidades = UtilSBPersistencia.getEntyManagerPadraoNovo();

        String jpqlDisponibilidades = " from " + DisponibilidadeAtdmtPublico.class.getSimpleName() + " FETCH ALL PROPERTIES";
        Query consulta = emDisponibilidades.createQuery(jpqlDisponibilidades);

        List<DisponibilidadeAtdmtPublico> listaCompletaDisponibilidades
                = consulta.getResultList();
        listaCompletaDisponibilidades.stream().forEach(disp -> disp.getTiposAgendamentosPublicos().size());
        listaCompletaDisponibilidades.stream().forEach(disp -> disp.getAtendentesDisponiveis().size());
        listaCompletaReservas.stream().forEach(MapaHorariosDisponiveis::adicionarReservaAtendente);
        listaCompletaDisponibilidades.stream().forEach(MapaHorariosDisponiveis::adicionarDisponibilidade);

        UtilSBPersistencia.fecharEM(emDisponibilidades);
    }

    public static List<DisponibilidadeAtdmtPublico> getDisponibilidadesDoEscopo(EscopoPesquisaMelhorHorario pEscopo) {
        final List<DisponibilidadeAtdmtPublico> disponibilidadesEscopo = new ArrayList<>();

        if (pEscopo.getAtendentes() == null || pEscopo.getAtendentes().isEmpty()) {
            throw new UnsupportedOperationException("Nenhum atendente foi definido no escopo");
        }

        UsuarioSB usuarioAtendente = pEscopo.getAtendentes().get(0);
        List<DisponibilidadeAtdmtPublico> disponibilidadeDoAtendente = mapadisponibilidadesPorAtendente.get(usuarioAtendente);
        if (disponibilidadeDoAtendente == null || disponibilidadeDoAtendente.isEmpty()) {

            loadReservasEDisponibilidadesPersistidos();

        }
        disponibilidadeDoAtendente = mapadisponibilidadesPorAtendente.get(usuarioAtendente);
        if (disponibilidadeDoAtendente == null) {
            return new ArrayList<>();
        }
        disponibilidadeDoAtendente.stream()
                .filter(dispo -> UtilSBAgendaHorariosDisponiveis.isDisponibilidadeDentroDoEscopo(pEscopo, dispo))
                .forEach(disponibilidadesEscopo::add);
        return disponibilidadesEscopo;

    }

    public static void adicionarDisponibilidade(DisponibilidadeAtdmtPublico pDisponibilidade) {

        if (pDisponibilidade.getDiaInicial() == null) {
            throw new UnsupportedOperationException("O dia  inicial da disponibilidade não foi definica");
        }
        if (pDisponibilidade.getDiaFinal() == null) {
            throw new UnsupportedOperationException("O dia  final da disponibilidade não foi definica");
        }
        if (pDisponibilidade.getHorarioInicio() == null) {
            throw new UnsupportedOperationException("O Horário inicial da disponiblidade não foi definica");
        }
        if (pDisponibilidade.getHorarioFim() == null) {
            throw new UnsupportedOperationException("O Horário final da disponiblidade não foi definica");
        }

        pDisponibilidade.getAtendentesDisponiveis().stream().forEach(atendente -> {
            if (!mapadisponibilidadesPorAtendente.containsKey(atendente)) {
                mapadisponibilidadesPorAtendente.put(atendente, new ArrayList());
                if (!mapaReservasPorAtendente.containsKey(atendente)) {
                    mapaReservasPorAtendente.put(atendente, new ArrayList());
                }
            }
            if (!mapadisponibilidadesPorAtendente.get(atendente).contains(pDisponibilidade)) {
                mapadisponibilidadesPorAtendente.get(atendente)
                        .add(pDisponibilidade);
            }

        });

    }

    public static List<HorarioDisponivelAtendimentoPublico> getHorarioDisponivelPrimeiras9pcoes(EscopoPesquisaMelhorHorario pEscopo) {
        List<HorarioDisponivelAtendimentoPublico> listageDexMelhoresHorarios = new ArrayList();
        try {
            TokenLinhaDoTempoDeLorean delorean = new TokenLinhaDoTempoDeLorean(pEscopo, false, 9);

            while (delorean.saltarProximoHorarioDisponivel() != null) {

                HorarioDisponivelAtendimentoPublico horarioDisponivel = delorean.getHorarioMomentoAtual();

                if (!isHorarioBloqueadoPelasReservas(horarioDisponivel)) {
                    listageDexMelhoresHorarios.add(horarioDisponivel);
                }

            }
        } catch (ErroAtingiuFinalLinhaDoTempoPermita ex) {
            return listageDexMelhoresHorarios;
        }

        return listageDexMelhoresHorarios;
    }

    public static List<ReservaHorario> getReservasPrimeiras10pcoes(EscopoPesquisaMelhorHorario pEscopo) {

        List<HorarioDisponivelAtendimentoPublico> dezmelhoresHorarios = getHorarioDisponivelPrimeiras9pcoes(pEscopo);
        List<ReservaHorario> reservas = new ArrayList();
        dezmelhoresHorarios.stream().map(horario -> {
            ReservaHorario reserva = new ReservaHorario();
            try {
                reserva.prepararNovoObjeto(horario);
            } catch (ErroPreparandoObjeto ex) {
                Logger.getLogger(MapaHorariosDisponiveis.class.getName()).log(Level.SEVERE, null, ex);
            }
            return reserva;
        }).forEachOrdered(reserva -> {
            reservas.add(reserva);
        });

        return reservas;
    }

    public static boolean isHorarioDisponivelBloqueadoPelaReserva(HorarioDisponivelAtendimentoPublico pHorario,
            ReservaHorario pReserva
    ) {
        if (!pHorario.getUsuarioResponsavel().equals(pReserva.getAtendenteResponsavel())) {
            return false;
        }
        // 7:00 10:00 Reserva
        // 6:00 :7:30 não   dataFinal maior que a data inicial da reserva e datafinal < data final da reserva
        // 7:30 9:00 não
        // 7:00 10:30 não data inicial maior igual a data inicial da reserva e a data  menor que data final
        if (pHorario.getDatahoraFinalAtendente().getTime() >= pReserva.getInicioReservaAtendente().getTime()
                && pHorario.getDatahoraFinalAtendente().getTime() <= pReserva.getFinalReservaAtendente().getTime()) {
            return true;
        }
        if (pHorario.getDataHoraIicialAtendente().getTime() >= pReserva.getInicioReservaAtendente().getTime()
                && pHorario.getDatahoraFinalAtendente().getTime() <= pReserva.getFinalReservaAtendente().getTime()) {
            return true;
        }
        if (pHorario.getDataHoraIicialAtendente().getTime() >= pReserva.getInicioReservaAtendente().getTime()
                && pHorario.getDataHoraIicialAtendente().getTime() <= pReserva.getFinalReservaAtendente().getTime()) {

        }

        return false;

    }

    public static boolean isReservaBloqueadoPorOutraReserva(ReservaHorario pReservaValidacao,
            ReservaHorario pReserva
    ) {
        if (!pReservaValidacao.getAtendenteResponsavel().equals(pReserva.getAtendenteResponsavel())) {
            return false;
        }
        // 7:00 10:00 Reserva
        // 6:00 :7:30 não   dataFinal maior que a data inicial da reserva e datafinal < data final da reserva
        // 7:30 9:00 não
        // 7:00 10:30 não data inicial maior igual a data inicial da reserva e a data  menor que data final
        if (pReservaValidacao.getFinalReservaAtendente().getTime() >= pReserva.getInicioReservaAtendente().getTime()
                && pReservaValidacao.getFinalReservaAtendente().getTime() <= pReserva.getFinalReservaAtendente().getTime()) {
            return true;
        }
        if (pReservaValidacao.getInicioReservaAtendente().getTime() >= pReserva.getInicioReservaAtendente().getTime()
                && pReservaValidacao.getFinalReservaAtendente().getTime() <= pReserva.getFinalReservaAtendente().getTime()) {
            return true;
        }
        if (pReservaValidacao.getInicioReservaAtendente().getTime() >= pReserva.getInicioReservaAtendente().getTime()
                && pReservaValidacao.getInicioReservaAtendente().getTime() <= pReserva.getFinalReservaAtendente().getTime()) {

        }

        return false;

    }

    public static boolean isHorarioBloqueadoPelasReservas(HorarioDisponivelAtendimentoPublico pHorario) {
        if (pHorario == null) {
            return false;
        }
        return mapaReservasPorAtendente.get(pHorario.getEscopo().getAtendentes().get(0))
                .parallelStream()
                .filter(reserva -> UtilCRCDataHora.isDiaIgual(reserva.getInicioReservaAtendente(), pHorario.getDatahoraFinalAtendente()))
                .parallel().filter(reservaDoDia -> isHorarioDisponivelBloqueadoPelaReserva(pHorario, reservaDoDia)).findFirst().isPresent();

    }

    public static void adicionarReservaLocal(ReservaHorario pReserva) {

    }

    public static void removerReservaLocal(ReservaHorario pReserva) {

    }

    public static void adicionarReservaAtendente(ReservaHorario pReserva) {
        try {
            if (pReserva.getInicioReservaAtendente().getTime() < new Date().getTime()) {
                return;
            }
            if (!SBCore.isEmModoProducao()) {
                System.out.println("Reserva de ");
                System.out.println(UtilCRCDataHora.getDataHoraString(pReserva.getInicioReservaAtendente(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO));
                System.out.println("até:");
                System.out.println(UtilCRCDataHora.getDataHoraString(pReserva.getFinalReservaAtendente(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO));
                System.out.println(pReserva.getCPinst("pessoaRelacionada").getValor());
            }
            UsuarioCRM atendente = pReserva.getAtendenteResponsavel();
            if (!mapaReservasPorAtendente.containsKey(atendente)) {
                mapaReservasPorAtendente.put(atendente, new ArrayList<>());
            }
            mapaReservasPorAtendente.get(pReserva.getAtendenteResponsavel()).add(pReserva);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha adicionando reserva em memória", t);
        }

    }

    public static void removerReservaAtendente(ReservaHorario pReserva) {
        try {
            mapaReservasPorAtendente.get(pReserva.getAtendenteResponsavel()).remove(pReserva);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha removendo reserva em memória", t);
        }

    }
}
