package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import cucumber.api.java.pt.E;
import java.util.List;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.agendaconsultordefiniragenda.EtapasAgendaConsultorDefinirAgenda;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.FluxoAgendaDoConsultor;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda.UtilSBAgendaHorariosDisponiveis;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;

public class L__o_usuario_tenta_encontrar_este_horario_novamente {

    @E(EtapasAgendaConsultorDefinirAgenda.E_O_USUARIO_TENTA_ENCONTRAR_ESTE_HORARIO_NOVAMENTE)
    public void implementacaoEtapa() {
        UtilSBAgendaHorariosDisponiveis.atualizarReservas();
        FluxoAgendaDoConsultor.escopoAtendimentoRemoto.setDisponibilidadesDoEscopo(null);
        FluxoAgendaDoConsultor.escopoAtendimentoRemoto.setDisponibilidadesDoEscopo(null);
        List<HorarioDisponivelAtendimentoPublico> horariosDisponiveis = (List) FluxoAgendaDoConsultor.escopoAtendimentoRemoto.getCPinst(CPEscopoPesquisaMelhorHorario.listahorariosdisponiveis).getValor();
        System.out.println(UtilCRCDataHora.getDataHoraString(horariosDisponiveis.get(0).getDataHoraIicialAtendente(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO));
        System.out.println("UP");
    }
}
