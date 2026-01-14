package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import cucumber.api.java.pt.Quando;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.agendaconsultordefiniragenda.EtapasAgendaConsultorDefinirAgenda;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.ModuloAgendamentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.FabTipoAgendamentoPublicoCrm;
import static org.junit.Assert.assertTrue;

public class D_Quando__O_usuario_altera_seus_horarios_disponiveis_para_reuniao_presencial {

    @Quando(EtapasAgendaConsultorDefinirAgenda.QUANDO_O_USUARIO_ALTERA_SEUS_HORARIOS_DISPONIVEIS_PARA_REUNIAO_PRESENCIAL)
    public void implementacaoEtapa() {

        TipoAgendamentoAtdmPublico tipoAgendamento = FabTipoAgendamentoPublicoCrm.PRESENCIAL.getRegistro();
        DisponibilidadeAtdmtPublico novaDisponibilidade = new DisponibilidadeAtdmtPublico();
        novaDisponibilidade.getTiposAgendamentosPublicos().add(tipoAgendamento);
        novaDisponibilidade.setDiaInicial(new Date());
        novaDisponibilidade.setDiaFinal(UtilCRCDataHora.incrementaDias(new Date(), 30));
        Calendar horarioInicial = Calendar.getInstance();
        horarioInicial.set(Calendar.HOUR, 12);
        horarioInicial.set(Calendar.MINUTE, 30);

        Calendar horarioFinal = Calendar.getInstance();
        horarioFinal.set(Calendar.HOUR, 12);
        horarioFinal.set(Calendar.MINUTE, 30);
        try {
            novaDisponibilidade.setHorarioInicio(new Time(new SimpleDateFormat("hh:mm").parse("8:00").getTime()));
            novaDisponibilidade.setHorarioFim(new Time(new SimpleDateFormat("hh:mm").parse("20:00").getTime()));

        } catch (ParseException ex) {
            throw new UnsupportedOperationException("Erro definindo horário");
        }
        UsuarioSB usuarioAtendente = (UsuarioSB) SBCore.getUsuarioLogado();
        novaDisponibilidade.adicionarAtendenteDisponivel(usuarioAtendente);
        novaDisponibilidade.setUsuarioResponsavel((UsuarioSB) SBCore.getUsuarioLogado());
        ItfResposta resposta = ModuloAgendamentoPublico.disponibilidadeAtendimentoMerge(novaDisponibilidade);
        assertTrue("O horário disponível não foi encontrado", resposta.isSucesso());

    }
}
