package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas;

import cucumber.api.java.pt.E;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.agendaconsultordefiniragenda.EtapasAgendaConsultorDefinirAgenda;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.FluxoAgendaDoConsultor;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.FabTipoAgendamentoPublicoCrm;

public class B__escopo_remoto__para_segunda_e_quinta_das_8_as_15 {

    @E(EtapasAgendaConsultorDefinirAgenda.E_ESCOPO_DE_PESQUISA_REMOTO_PARA_SEGUNDA_DAS_8_AS_15)
    public void implementacaoEtapa() {
        FluxoAgendaDoConsultor.escopoAtendimentoRemoto.setTipoAgendamento(FabTipoAgendamentoPublicoCrm.CONFERENCIA.getRegistro());
        FluxoAgendaDoConsultor.escopoAtendimentoRemoto.setDataInicial(new Date());
        FluxoAgendaDoConsultor.escopoAtendimentoRemoto.setDiasDaSemana("0100100");
        try {
            FluxoAgendaDoConsultor.escopoAtendimentoRemoto.setHorarioinicio(new SimpleDateFormat("hh:mm").parse("8:00"));
            FluxoAgendaDoConsultor.escopoAtendimentoRemoto.setHorarioFinal(new SimpleDateFormat("hh:mm").parse("20:00"));
        } catch (ParseException ex) {
            throw new UnsupportedOperationException("Erro definindo horarrio inicial");
        }

    }
}
