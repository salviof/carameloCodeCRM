/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotrespostas;

import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotformulario.FluxoTypebotFormulario;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRM_CRC;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import cucumber.api.CucumberOptions;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.ConfigPersistCRMDemostracao;
import org.junit.runner.RunWith;
import testesFW.cucumber.CucumberSBTestes;
import testesFW.cucumber.TesteIntegracaoFuncionalidadeCucumber;
import testesFW.devOps.DevOpsCucumberPersistenciaMysql;

/**
 *
 * @author sfurbino
 */
@RunWith(CucumberSBTestes.class)
@CucumberOptions(features = "classpath:caracteristicas", tags = "@FluxoTypebotRespostas",
        glue = "org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotrespostas.etapas",
        monochrome = false, dryRun = false)
public class FluxoTypebotRespostas extends TesteIntegracaoFuncionalidadeCucumber {

    public static final String USUARIO_VENDEDOR = "atendimento@casanovadigital.com.br";
    public static final String SENHA_VENDEDOR = "123";
    public static ChamadoCliente chamado;

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreCRM_CRC(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistCRMDemostracao(), true, false);
        DevOpsCucumberPersistenciaMysql.carregarResultadoRequisito(FluxoTypebotFormulario.class);
        TipoDadoCRM observacao = new TipoDadoCRM();
        observacao.setId(1L);

        observacao.setNome("obs");
        observacao.setLabel("Observacao");
        observacao.setFabricaTipoAtributo(FabTipoAtributoObjeto.HTML);
        UtilSBPersistencia.mergeRegistro(observacao, getEMTeste());

    }

}
