package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.etapas;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.HorarioDisponivelAtendimentoPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.AgendaDisponibilidade;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda.ErroAcidenteDeLorean;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgendaPublica.ModuloCRMAgendaPublica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.reservalinkpublico.EtapasReservaLinkPublico;
import cucumber.api.java.pt.Quando;
import java.lang.UnsupportedOperationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.FluxoAgendaPublicaReserva;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.junit.Assert;

public class C_Quando_seleciona_um_horario_disponivel_do_atendente_Salvio_Furbino {

    @Quando(EtapasReservaLinkPublico.E_SELECIONA_UM_HORARIO_DISPONIVEL_DO_ATENDENTE_SALVIO_FURBINO)
    public void implementacaoEtapa() {
        FluxoAgendaPublicaReserva.agendaDisponibilidade = new AgendaDisponibilidade(FluxoAgendaPublicaReserva.escopoPulico);
        try {
            FluxoAgendaPublicaReserva.agendaDisponibilidade.loadgendaXDias();
        } catch (ErroAcidenteDeLorean ex) {
            Logger.getLogger(C_Quando_seleciona_um_horario_disponivel_do_atendente_Salvio_Furbino.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertFalse(FluxoAgendaPublicaReserva.agendaDisponibilidade.getHorariosDisponiveis().isEmpty());
        HorarioDisponivelAtendimentoPublico horarioDisponivel = FluxoAgendaPublicaReserva.agendaDisponibilidade.getHorariosDisponiveis().get(0);
        FluxoAgendaPublicaReserva.RESERVA = new ReservaHoraRemotoVideo();
        try {
            FluxoAgendaPublicaReserva.RESERVA.prepararNovoObjeto(horarioDisponivel);
            FluxoAgendaPublicaReserva.RESERVA.setDadosContatoAcessoAnonimo(FluxoAgendaPublicaReserva.dadosContato);
        } catch (ErroPreparandoObjeto ex) {
            Logger.getLogger(C_Quando_seleciona_um_horario_disponivel_do_atendente_Salvio_Furbino.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail("Falha criando nova reserva");
        }
        ItfRespostaAcaoDoSistema reservar = ModuloCRMAgendaPublica.reservaPublicaReservar(FluxoAgendaPublicaReserva.RESERVA);
        FluxoAgendaPublicaReserva.RESERVA = (ReservaHorarioCRM) reservar.getRetorno();
        Assert.assertTrue("Falha reservando", reservar.isSucesso());
    }
}
