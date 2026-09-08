package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import cucumber.api.java.pt.E;
import java.util.List;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.agendaconsultordefiniragenda.EtapasAgendaConsultorDefinirAgenda;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.FluxoAgendaDoConsultor;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda.MapaHorariosDisponiveis;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda.UtilSBAgendaHorariosDisponiveis;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.HorarioDisponivelAtendimentoPublico;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;

public class J__A_Lista_de_Horarios_diponiveis_para_reuniao_online_esta_de_acordo_com_o_Cadastrado {

    @E(EtapasAgendaConsultorDefinirAgenda.E_A_LISTA_DE_HORARIOS_DIPONIVEIS_PARA_REUNIAO_ONLINE_ESTA_DE_ACORDO_COM_O_CADASTRADO)
    public void implementacaoEtapa() throws ErroRegraDeNegocio {

        MapaHorariosDisponiveis.loadReservasEDisponibilidadesPersistidos();
        FluxoAgendaDoConsultor.escopoAtendimentoRemoto.setUsuarioAtendente((UsuarioSB) SBCore.getUsuarioLogado());

        List<HorarioDisponivelAtendimentoPublico> horariosDisponiveis = (List) FluxoAgendaDoConsultor.escopoAtendimentoRemoto.getHorariosDisponiveis();

        assertNotNull("Recebeu Lista de Horários nula", horariosDisponiveis);
        Assert.assertTrue("Nenhum horário foi listado", !horariosDisponiveis.isEmpty());

        for (HorarioDisponivelAtendimentoPublico horarioDisponivel : horariosDisponiveis) {
            System.out.println("De");
            System.out.println(UtilCRCDataHora.getDataHoraString(horarioDisponivel.getDataHoraIicialAtendente(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO));
            System.out.println("Até");
            System.out.println(UtilCRCDataHora.getDataHoraString(horarioDisponivel.getDatahoraFinalAtendente(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO));
            System.out.println("__________");
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
            List<DisponibilidadeAtdmtPublico> disponibilidades = MapaHorariosDisponiveis.getDisponibilidadesDoEscopo(FluxoAgendaDoConsultor.escopoAtendimentoRemoto.getFiltro());
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
