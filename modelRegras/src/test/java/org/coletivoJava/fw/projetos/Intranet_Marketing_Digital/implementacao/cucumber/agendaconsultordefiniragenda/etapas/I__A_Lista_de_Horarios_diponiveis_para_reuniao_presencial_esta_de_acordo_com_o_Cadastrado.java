package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import cucumber.api.java.pt.E;
import java.util.List;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.agendaconsultordefiniragenda.EtapasAgendaConsultorDefinirAgenda;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.FluxoAgendaDoConsultor;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda.MapaHorariosDisponiveis;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda.UtilSBAgendaHorariosDisponiveis;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.junit.Assert;

public class I__A_Lista_de_Horarios_diponiveis_para_reuniao_presencial_esta_de_acordo_com_o_Cadastrado extends Assert {

    @E(EtapasAgendaConsultorDefinirAgenda.E_A_LISTA_DE_HORARIOS_DIPONIVEIS_PARA_REUNIAO_PRESENCIAL_ESTA_DE_ACORDO_COM_O_CADASTRADO)
    public void implementacaoEtapa() throws ErroRegraDeNegocio {
        FluxoAgendaDoConsultor.escopoAtendimentoPresencial
                .adicionarAtendente((UsuarioSB) SBCore.getUsuarioLogado());

        List<HorarioDisponivelAtendimentoPublico> horariosDisponiveis = (List) FluxoAgendaDoConsultor.escopoAtendimentoPresencial.getCPinst(CPEscopoPesquisaMelhorHorario.listahorariosdisponiveis).getValor();
        assertNotNull("Recebeu Lista de Horários nula", horariosDisponiveis);
        Assert.assertTrue("Nenhum horário foi listado", !horariosDisponiveis.isEmpty());

        for (HorarioDisponivelAtendimentoPublico horarioDisponivel : horariosDisponiveis) {
            System.out.println("Hora hora, sherlock Holmes, tem horário aqui!");

            if (!validarHorario(horarioDisponivel)) {
                throw new ErroRegraDeNegocio("dasd");
            }
        }

    }

    private boolean validarHorario(HorarioDisponivelAtendimentoPublico pHorario) {
        try {
            if (pHorario.getDataHoraIicialAtendente().getTime() >= pHorario.getDatahoraFinalAtendente().getTime()) {
                return false;
            }
            List<DisponibilidadeAtdmtPublico> disponibilidades = MapaHorariosDisponiveis.getDisponibilidadesDoEscopo(FluxoAgendaDoConsultor.escopoAtendimentoPresencial);
            boolean dentroDaDisponibilidade = UtilSBAgendaHorariosDisponiveis.isHorarioDentroDasDisponibilidades(disponibilidades, pHorario);
            if (!dentroDaDisponibilidade) {
                return false;
            }
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

}
