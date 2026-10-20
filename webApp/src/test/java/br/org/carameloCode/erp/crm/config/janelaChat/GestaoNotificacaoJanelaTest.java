/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.carameloCode.erp.crm.config.janelaChat;

import br.org.coletivoJava.fw.erp.implementacao.chat.atendente.janelaChat.GestaoNotificacaoJanelaChat;
import br.org.carameloCode.erp.crm.config.ConfigCoreCrmCarameloTestes;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPersistenciaCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class GestaoNotificacaoJanelaTest {

    public GestaoNotificacaoJanelaTest() {
    }

    @Test
    public void testSomeMethod() {
        SBCore.configurar(new ConfigCoreCrmCarameloTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistenciaCrmCarameloCode(), true, false);
        UsuarioCRM usuaio = (UsuarioCRM) CarameloCode.getServicoPermissao().getUsuarioByEmail("salvio@casanovadigital.com.br");
        GestaoNotificacaoJanelaChat gestao = new GestaoNotificacaoJanelaChat(usuaio, "https://chat.casanovadigital.com.br");

        while (true) {
            if (gestao.getJanelas() != null) {
                System.out.println(gestao.getJanelas().size());
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestaoNotificacaoJanelaTest.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
