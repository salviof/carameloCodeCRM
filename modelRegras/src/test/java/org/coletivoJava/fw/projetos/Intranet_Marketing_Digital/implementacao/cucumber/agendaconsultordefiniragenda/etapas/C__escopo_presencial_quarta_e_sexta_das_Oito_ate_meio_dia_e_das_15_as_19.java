package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas;

import cucumber.api.java.pt.E;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.agendaconsultordefiniragenda.EtapasAgendaConsultorDefinirAgenda;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.FluxoAgendaDoConsultor;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.FabTipoAgendamentoPublicoCrm;

public class C__escopo_presencial_quarta_e_sexta_das_Oito_ate_meio_dia_e_das_15_as_19 {

    @E(EtapasAgendaConsultorDefinirAgenda.E_ESCOPO_DE_PESQUISA_PRESENCIAL_QUARTA_E_SEXTA_DAS_15_AS_19)
    public void implementacaoEtapa() {
        FluxoAgendaDoConsultor.escopoAtendimentoPresencial.setTipoAgendamento(FabTipoAgendamentoPublicoCrm.PRESENCIAL.getRegistro());
        FluxoAgendaDoConsultor.escopoAtendimentoPresencial.setDataInicial(new Date());

        FluxoAgendaDoConsultor.escopoAtendimentoPresencial.setDiasDaSemana("0001010");

        try {
            FluxoAgendaDoConsultor.escopoAtendimentoPresencial.setHorarioinicio(new SimpleDateFormat("hh:mm").parse("8:00"));
            FluxoAgendaDoConsultor.escopoAtendimentoPresencial.setHorarioFinal(new SimpleDateFormat("hh:mm").parse("20:00"));
        } catch (ParseException ex) {
            throw new UnsupportedOperationException("Erro definindo horarrio inicial");
        }
    }
}
