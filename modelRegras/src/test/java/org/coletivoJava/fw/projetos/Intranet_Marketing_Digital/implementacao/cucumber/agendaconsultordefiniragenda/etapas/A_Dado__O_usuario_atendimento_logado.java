package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import cucumber.api.java.pt.Dado;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.agendaconsultordefiniragenda.EtapasAgendaConsultorDefinirAgenda;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.FluxoAgendaDoConsultor;
import org.junit.Assert;

public class A_Dado__O_usuario_atendimento_logado {

    @Dado(EtapasAgendaConsultorDefinirAgenda.DADO_O_USUARIO_ATENDIMENTO_LOGADO)
    public void implementacaoEtapa() {

        SBCore.getServicoSessao().logarEmailESenha(FluxoAgendaDoConsultor.NOMEUSUARIO_ATENDIMENTO, FluxoAgendaDoConsultor.SENHA_ATENDIMENTO);

        Assert.assertTrue("O usário não logrou êxito ao atendicar com " + FluxoAgendaDoConsultor.NOMEUSUARIO_ATENDIMENTO + " senha " + FluxoAgendaDoConsultor.SENHA_ATENDIMENTO, SBCore.getServicoSessao().getSessaoAtual().isIdentificado());
    }
}
