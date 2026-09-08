package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.HorarioDisponivelAtendimentoPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda.UtilSBAgendaHorariosDisponiveis;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import cucumber.api.java.pt.Entao;
import java.util.Date;
import java.util.List;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.agendaconsultordefiniragenda.EtapasAgendaConsultorDefinirAgenda;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.FluxoAgendaDoConsultor;
import org.junit.Assert;

public class M_Entao__o_usuario_nao_encontra_mais_este_horario {

    @Entao(EtapasAgendaConsultorDefinirAgenda.ENTAO_O_USUARIO_NAO_ENCONTRA_MAIS_ESTE_HORARIO)
    public void implementacaoEtapa() {
        UtilSBAgendaHorariosDisponiveis.atualizarReservas();
        FluxoAgendaDoConsultor.escopoAtendimentoRemoto.limparPesquisa();
        FluxoAgendaDoConsultor.escopoAtendimentoRemoto.setUsuarioAtendente((UsuarioSB) SBCore.getUsuarioLogado());
        FluxoAgendaDoConsultor.atualizarEntidadesDeclaradas();
        ReservaHorario reserva = FluxoAgendaDoConsultor.reservaHorario;
        List<HorarioDisponivelAtendimentoPublico> horariosDisponiveis = (List) FluxoAgendaDoConsultor.escopoAtendimentoRemoto.getHorariosDisponiveis();

        Assert.assertNotNull("A reserva criada no passo anterior não foi registrada", reserva);
        Assert.assertFalse("Nenhum horário livre foi listado depois da reserva", horariosDisponiveis.isEmpty());

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
