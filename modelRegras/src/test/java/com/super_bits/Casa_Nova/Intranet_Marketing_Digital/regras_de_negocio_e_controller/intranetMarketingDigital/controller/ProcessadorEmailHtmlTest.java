/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.regras_de_negocio_e_controller.intranetMarketingDigital.controller;

import org.junit.Test;

/**
 *
 * @author salvio
 */
public class ProcessadorEmailHtmlTest {

    public ProcessadorEmailHtmlTest() {
    }

    @Test
    public void testGetHtmlProcessado() {

        ProcessadorEmailHtml instance = new ProcessadorEmailHtml(
                "<html><head></head><h1 >Sálvio</h1></html>",
                "p {margin: 0 0 10px;}");
        System.out.println(instance.getHtmlProcessado());
    }

}
