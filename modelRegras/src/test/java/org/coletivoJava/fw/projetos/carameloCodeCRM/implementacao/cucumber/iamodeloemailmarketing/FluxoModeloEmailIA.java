package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contato.ContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;
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
@CucumberOptions(features = "classpath:caracteristicas/iaModeloEmail", tags = "@IAModeloEmailMarketing",
        glue = "org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas",
        monochrome = false, dryRun = false)
public class FluxoModeloEmailIA extends TesteIntegracaoFuncionalidadeCucumber {

    public static final String EMAIL_ATENDENTE = "atendimento@casanovadigital.com.br";
    public static final String SENHA_ATENDIMENTO = "123";
    public static ModeloEmailMarketing EMAIL_MARKETING;
    public static Persona PERSONA;
    public static ExecucaoIAModeloEmailMkt EXECUCAO_IA;

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreCRMTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistCRMDemostracao(), true, true);
        MapaObjetosProjetoAtual.adcionarObjeto(ContatoAnonimoDadoTansitorio.class);

    }

}
