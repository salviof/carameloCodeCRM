/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class ValorLogicoDDHostServidorEMailTest extends ValorLogicoTesteDadoDinamico {

    public ValorLogicoDDHostServidorEMailTest() {
    }

    /**
     * Test of getValor method, of class ValorLogicoDDHostServidorEMail.
     */
    @Test
    public void testGetValor() {
        DadoCRM dado = getDadoCRM(ValorLogicoDDHostServidorEMail.class);
        assertNotNull("Tipo de dado não localizado, cadastr um tipo de dado com ValorLogicoDDHostServidorEMail", dado);
        System.out.println(dado.getCampoInstanciado().getValor());
        assertEquals("Domínio encontrado não é o domínio esperado", "mail04-ssl.m9.network", dado.getCampoInstanciado().getValor());

    }

}
