/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotformulario;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRM_CRC;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
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
@CucumberOptions(features = "classpath:caracteristicas", tags = "@FluxoTypebotFormulario",
        glue = "org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotformulario.etapas",
        monochrome = false, dryRun = false)
public class FluxoTypebotFormulario extends TesteIntegracaoFuncionalidadeCucumber {

    public static final String USUARIO_VENDEDOR = "atendimento@casanovadigital.com.br";
    public static final String SENHA_VENDEDOR = "123";
    public static ChamadoCliente chamado;

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreCRM_CRC(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistCRMDemostracao());

    }

}
