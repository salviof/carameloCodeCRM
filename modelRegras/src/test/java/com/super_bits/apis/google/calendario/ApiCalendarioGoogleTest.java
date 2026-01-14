/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.apis.google.calendario;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRM_CRC;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPercistenciaCrmCarameloCode;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCEmail;
import com.super_bits.modulosSB.SBCore.modulos.email.ConfigEmailServersProjeto;

/**
 *
 * @author SalvioF
 */
public class ApiCalendarioGoogleTest extends TesteCRMCarameloCode {

    public static void main(String[] args) {

        //Nas classes de ambiente padrão do sistema  modo desenvolvimento significa execuções via JUNIT, HOmologação Jetty na sua maquina, e Produção na Web
        SBCore.configurar(new ConfiguradorCoreCRM_CRC(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

        // System.out.println("CAMINHO===" + getPastaExecucaoScriptsSQL());
        System.out.println("Retorno");

        SBPersistencia.configuraJPA(new ConfigPercistenciaCrmCarameloCode(), true, true);
        UtilCRCEmail.configurar(new ConfigEmailServersProjeto("mail.casanovadigital.com.br", "contato@casanovadigital.com.br", "acasadigital@2019"));

        //    ApiCalendarioGoogle calendario = new ApiCalendarioGoogle();
        //   System.out.println(calendario.getUrlAutenticacao());
        //   Scanner sc = new Scanner(System.in);
        // String codigo = sc.nextLine();
        // calendario.informarTokenAutorizacao(codigo);
        //    calendario.registrarAtividades(new ArrayList<>());
        System.out.println("Fim!");
    }

}
