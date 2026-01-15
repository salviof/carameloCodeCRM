/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.marketing.Util;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRM_CRC;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPercistenciaCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCEmail;
import com.super_bits.modulosSB.SBCore.modulos.email.ConfigEmailServersProjeto;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilCRCEmailAvancadoTest extends TesteCRMCarameloCode {

    /**
     * Test of enviarEmailComAnexo method, of class UtilCRCEmailAvancado.
     */
    @Test
    public void testEnviarEmailComAnexo() {
        try {
            ContatoProspecto contatoTeste = new ContatoProspecto();
            contatoTeste.setNome("Salvio Furbino");
            contatoTeste.setEmail("salviof@gmail.com");
            List<ContatoProspecto> listaContatos = new ArrayList<>();
            listaContatos.add(contatoTeste);
            File arquivoTeste = new File("/home/SalvioF/Imagens/FundoSBFWTwitter.png");
            String reposta = UtilCRCEmailAvancado.enviarEmailComAnexoV2(UtilCRCEmailAvancado.getJsonFromListaContato(listaContatos), "Teste", "Teste", null);
            System.out.println(reposta);
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    @Override
    public void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreCRM_CRC(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

        // System.out.println("CAMINHO===" + getPastaExecucaoScriptsSQL());
        System.out.println("Retorno");

        SBPersistencia.configuraJPA(new ConfigPercistenciaCrmCarameloCode(), true, false);
        UtilCRCEmail.configurar(new ConfigEmailServersProjeto("mail.casanovadigital.com.br", "contato@casanovadigital.com.br", "acasadigital@2017"));

    }

}
