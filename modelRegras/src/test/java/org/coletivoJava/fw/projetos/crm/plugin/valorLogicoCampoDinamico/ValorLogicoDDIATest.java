/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoIA;
import br.org.coletivoJava.fw.api.erp.ia.escopo.ERP_IA;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class ValorLogicoDDIATest extends TesteCRMCarameloCode {

    /**
     * Test of getValor method, of class ValorLogicoDDIA.
     */
    @Test
    public void testGetValor() {

        TipoDadoIA tipoDadoIa = new TipoDadoIA();
        tipoDadoIa.setImplementacaoPadrao(ERP_IA.OLHAMA);
        tipoDadoIa.setLabel("Teste");
        tipoDadoIa.setConteudoPerguntaIA("Quanto é 2 + 2");

    }

}
