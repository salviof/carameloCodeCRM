package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.AgendaDisponibilidade;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contato.ContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRMTestes;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import cucumber.api.CucumberOptions;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.ConfigPersistCRMDemostracao;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.FluxoAgendaDoConsultor;
import org.junit.runner.RunWith;
import testesFW.cucumber.CucumberSBTestes;
import testesFW.cucumber.TesteIntegracaoFuncionalidadeCucumber;
import testesFW.devOps.DevOpsCucumberPersistenciaMysql;

/**
 *
 * @author sfurbino
 */
@RunWith(CucumberSBTestes.class)
@CucumberOptions(features = "classpath:caracteristicas/agendaPublicaReserva", tags = "@ReservaLinkPublico",
        glue = "org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.etapas",
        monochrome = false, dryRun = false)
public class FluxoAgendaPublicaReserva extends TesteIntegracaoFuncionalidadeCucumber {

    public static final String EMAIL_RESERVA = "teste@casanovadigital.com.br";
    public static final String NOME_RESERVA = "Joao";
    public static final String TELEFONE_RESERVA = "31984178550";
    public static final String NOME_EMPRESA = "123";

    public static ReservaHorarioCRM RESERVA;

    public static EscopoPesqHorarioPublicado escopoPulico;

    public static AgendaDisponibilidade agendaDisponibilidade;
    public static ContatoAnonimoDadoTansitorio dadosContato;

    public static final String EMAIL_ATENDENTE = "atendimento@casanovadigital.com.br";
    public static final String SENHA_ATENDIMENTO = "123";

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreCRMTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistCRMDemostracao(), true, false);
        MapaObjetosProjetoAtual.adcionarObjeto(ContatoAnonimoDadoTansitorio.class);
        DevOpsCucumberPersistenciaMysql.carregarResultadoRequisito(FluxoAgendaDoConsultor.class);

    }

}
