/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.modulo.crm.config.ConfigPercistenciaCrmCarameloCode;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import testesFW.TesteJunitSBPersistencia;

/**
 *
 * @author desenvolvedor
 */
public class TesteWPCrmCarameloCode extends TesteJunitSBPersistencia {

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfigCoreWebAppCrmCarameloCode(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPercistenciaCrmCarameloCode(), true, false);
        try {
            SBWebPaginas.configurar(new ConfigWP_CRM_CarameloCode());
        } catch (IOException ex) {
            Logger.getLogger(TesteWPCrmCarameloCode.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
