/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.AgendaDisponibilidade;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRMTestes;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import cucumber.api.CucumberOptions;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.ConfigPersistCRMDemostracao;
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

    public final static AgendaDisponibilidade escopoAtendimentoRemoto = new AgendaDisponibilidade(new EscopoPesqHorarioPublicado());
    public final static AgendaDisponibilidade escopoAtendimentoPresencial = new AgendaDisponibilidade(new EscopoPesqHorarioPublicado());
    public static ReservaHorario reservaHorario;

    public static Pessoa pessoa;

    public static final String NOMEUSUARIO_ATENDIMENTO = "atendimento@casanovadigital.com.br";
    public static final String SENHA_ATENDIMENTO = "123";

    @Override
    public void configContextoExecucao() {
        SBCore.configurar(new ConfiguradorCoreCRMTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistCRMDemostracao());
        MapaObjetosProjetoAtual.adcionarObjeto(AgendaDisponibilidade.class);
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        configContextoExecucao();
    }

}
