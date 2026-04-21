/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCodeSemLimparBanco;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class TesteSincronizarTypebotResposta extends TesteCRMCarameloCodeSemLimparBanco {

    /**
     * Test of formularioSincronizar method, of class ModuloCRMAplicacao.
     */
    @Test
    public void testFormularioSincronizar() {
        System.out.println("formularioSincronizar");
        ItfRespostaAcaoDoSistema expResult = null;
        ItfRespostaAcaoDoSistema result = ModuloCRMAplicacao.respostasFormularioSincronizar();
        assertTrue("Falha sincronizando", result.isSucesso());
        // TODO review the generated test code and remove the default call to fail.

    }
}
