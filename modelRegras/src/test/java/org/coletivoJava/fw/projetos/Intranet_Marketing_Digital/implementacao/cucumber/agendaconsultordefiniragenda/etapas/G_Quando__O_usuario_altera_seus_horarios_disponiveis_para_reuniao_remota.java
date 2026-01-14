package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import cucumber.api.java.pt.Quando;
import java.sql.Time;
import java.util.Date;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.agendaconsultordefiniragenda.EtapasAgendaConsultorDefinirAgenda;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.ModuloAgendamentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.FabTipoAgendamentoPublicoCrm;
import static org.junit.Assert.assertTrue;

public class G_Quando__O_usuario_altera_seus_horarios_disponiveis_para_reuniao_remota {

    @Quando(EtapasAgendaConsultorDefinirAgenda.QUANDO_O_USUARIO_ALTERA_SEUS_HORARIOS_DISPONIVEIS_PARA_REUNIAO_REMOTA)
    public void implementacaoEtapa() {
        TipoAgendamentoAtdmPublico tipoAgendamento = FabTipoAgendamentoPublicoCrm.CONFERENCIA.getRegistro();
        DisponibilidadeAtdmtPublico novaDisponibilidade = new DisponibilidadeAtdmtPublico();
        novaDisponibilidade.setDiaInicial(new Date());
        novaDisponibilidade.setDiaFinal(UtilCRCDataHora.incrementaMes(new Date(), 1));
        novaDisponibilidade.setHorarioInicio(new Time(UtilCRCDataHora.converteString_HH_doisPontos_mm_EmData("08:30").getTime()));
        novaDisponibilidade.setHorarioFim(new Time(UtilCRCDataHora.converteString_HH_doisPontos_mm_EmData("12:30").getTime()));

        novaDisponibilidade.getTiposAgendamentosPublicos().add(tipoAgendamento);
        UsuarioSB usuarioAtendente = (UsuarioSB) SBCore.getUsuarioLogado();
        novaDisponibilidade.adicionarAtendenteDisponivel(usuarioAtendente);
        novaDisponibilidade.setUsuarioResponsavel((UsuarioSB) SBCore.getUsuarioLogado());
        ItfResposta resposta = ModuloAgendamentoPublico.disponibilidadeAtendimentoMerge(novaDisponibilidade);
        assertTrue("O horário disponível não foi encontrado", resposta.isSucesso());
    }
}
