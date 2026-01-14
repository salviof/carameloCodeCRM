package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.ModuloCRMCliente;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import cucumber.api.java.pt.Quando;
import java.util.List;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.agendaconsultordefiniragenda.EtapasAgendaConsultorDefinirAgenda;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.FluxoAgendaDoConsultor;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraRemotoVideo;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.junit.Assert;

public class K_Quando__uma_reserva_e_realizada_para_segunda_as_8_horas {

    @Quando(EtapasAgendaConsultorDefinirAgenda.QUANDO_UMA_RESERVA_E_REALIZADA_PARA_SEGUNDA_AS_8_HORAS)
    public void implementacaoEtapa() {
        FluxoAgendaDoConsultor.escopoAtendimentoRemoto.setDisponibilidadesDoEscopo(null);

        List<HorarioDisponivelAtendimentoPublico> horariosDisponiveis = (List) FluxoAgendaDoConsultor.escopoAtendimentoRemoto.getCPinst(CPEscopoPesquisaMelhorHorario.listahorariosdisponiveis).getValor();
        System.out.println(UtilCRCDataHora.getDataHoraString(horariosDisponiveis.get(0).getDataHoraIicialAtendente(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO));
        ReservaHoraRemotoVideo novaReserva = new ReservaHoraRemotoVideo();
        try {
            novaReserva.prepararNovoObjeto(horariosDisponiveis.get(0));
        } catch (ErroPreparandoObjeto ex) {
            throw new UnsupportedOperationException("Falha criando reserva de horário");
        }
        ItfResposta resp = ModuloCRMCliente.reservaHorario(novaReserva);
        Assert.assertTrue("Falha criando reserva", resp.isSucesso());
    }
}
