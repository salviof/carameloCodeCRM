/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRM_CRC;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import cucumber.api.CucumberOptions;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.ConfigPersistCRMDemostracao;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import org.junit.runner.RunWith;
import testesFW.cucumber.CucumberSBTestes;
import testesFW.cucumber.TesteIntegracaoFuncionalidadeCucumber;

/**
 *
 * @author sfurbino
 */
@RunWith(CucumberSBTestes.class)
@CucumberOptions(features = "classpath:caracteristicas", tags = "@AgendaConsultorDefinirAgenda",
        glue = "org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas",
        monochrome = false, dryRun = false
)
public class FluxoAgendaDoConsultor extends TesteIntegracaoFuncionalidadeCucumber {

    public final static EscopoPesquisaMelhorHorario escopoAtendimentoRemoto = new EscopoPesquisaMelhorHorario();
    public final static EscopoPesquisaMelhorHorario escopoAtendimentoPresencial = new EscopoPesquisaMelhorHorario();

    public static final String NOMEUSUARIO_ATENDIMENTO = "atendimento@casanovadigital.com.br";
    public static final String SENHA_ATENDIMENTO = "123";

    @Override
    public void configContextoExecucao() {
        SBCore.configurar(new ConfiguradorCoreCRM_CRC(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistCRMDemostracao());

    }

    @Override
    protected void configAmbienteDesevolvimento() {
        configContextoExecucao();
    }

}
