/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRMTestes;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.ConfigPersistCRMDemostracao;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import testesFW.cucumber.TesteIntegracaoFuncionalidadeCucumber;
import testesFW.devOps.DevOpsCucumberPersistenciaMysql;

/**
 *
 * @author salvio
 */
public class FluxoNotificacao extends TesteIntegracaoFuncionalidadeCucumber {

    public FluxoNotificacao() {
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreCRMTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistCRMDemostracao(), true, false);
        DevOpsCucumberPersistenciaMysql.carregarResultadoRequisito(FluxoChamadoSimples.class);
    }

}
