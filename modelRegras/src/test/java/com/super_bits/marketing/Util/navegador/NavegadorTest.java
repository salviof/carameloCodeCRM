/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.marketing.Util.navegador;

import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class NavegadorTest {

    /**
     * Test of navegar method, of class Navegador.
     */
    @Test
    public void testNavegar_int() {
        Navegador nav = new Navegador("https://casanovadigital.com.br");

        System.out.println(nav.getImagens());
    }

}
