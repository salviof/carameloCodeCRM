package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.etapas;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.HorarioDisponivelAtendimentoPublico;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda.UtilSBAgendaHorariosDisponiveis;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.reservalinkpublico.EtapasReservaLinkPublico;
import cucumber.api.java.pt.Entao;
import java.util.Date;
import java.util.List;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.FluxoAgendaPublicaReserva;
import org.junit.Assert;

public class E_Entao_o_horario_selecionado_deixa_de_aparecer_como_disponivel_no_link_publico {

    @Entao(EtapasReservaLinkPublico.E_O_HORARIO_SELECIONADO_DEIXA_DE_APARECER_COMO_DISPONIVEL_NO_LINK_PUBLICO)
    public void implementacaoEtapa() {

        UtilSBAgendaHorariosDisponiveis.atualizarReservas();
        FluxoAgendaPublicaReserva.agendaDisponibilidade.limparPesquisa();
        ReservaHorarioCRM reserva = FluxoAgendaPublicaReserva.RESERVA;

        List<HorarioDisponivelAtendimentoPublico> horariosDisponiveis = (List) FluxoAgendaPublicaReserva.agendaDisponibilidade.getHorariosDisponiveis();

        for (HorarioDisponivelAtendimentoPublico horario : horariosDisponiveis) {
            boolean conflitaComReserva = horario.getDataHoraIicialAtendente().getTime()
                    < reserva.getFinalReservaAtendente().getTime()
                    && horario.getDatahoraFinalAtendente().getTime() > reserva.getInicioReservaAtendente().getTime();
            Assert.assertFalse("O horário " + descreveIntervalo(horario.getDataHoraIicialAtendente(), horario.getDatahoraFinalAtendente())
                    + " continua sendo listado como livre, mas já está reservado de "
                    + descreveIntervalo(reserva.getInicioReservaAtendente(), reserva.getFinalReservaAtendente()),
                    conflitaComReserva);
        }
    }

    private String descreveIntervalo(Date pInicio, Date pFim) {
        return UtilCRCDataHora.getDataHoraString(pInicio, UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO)
                + " às " + UtilCRCDataHora.getDataHoraString(pFim, UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO);
    }
}
