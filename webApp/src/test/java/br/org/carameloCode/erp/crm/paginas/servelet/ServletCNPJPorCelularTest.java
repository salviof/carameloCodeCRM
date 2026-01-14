/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.servelet;

import br.org.carameloCode.erp.crm.config.TesteWPCrmCarameloCode;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import jakarta.json.JsonObject;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class ServletCNPJPorCelularTest extends TesteWPCrmCarameloCode {

    public ServletCNPJPorCelularTest() {
    }

    /**
     * Test of doGet method, of class ServletCNPJPorCelular.
     */
    @Test
    public void testDoGet() {
        ServletCNPJPorCelular servlet = new ServletCNPJPorCelular();
        JsonObject cnpj = servlet.getCPJ("+5531984704378");
        System.out.println(UtilCRCJson.getTextoByJsonObjeect(cnpj));

    }

}
