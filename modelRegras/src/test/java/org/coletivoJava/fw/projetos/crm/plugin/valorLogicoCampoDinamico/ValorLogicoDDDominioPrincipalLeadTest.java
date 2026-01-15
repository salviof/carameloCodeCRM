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
public class ValorLogicoDDDominioPrincipalLeadTest extends ValorLogicoTesteDadoDinamico {

    public ValorLogicoDDDominioPrincipalLeadTest() {
    }

    @Test
    public void testGetValor() {

        DadoCRM dado = getDadoCRM(ValorLogicoDDDominioPrincipalLead.class);
        assertNotNull("Tipo de dado não localizado, cadastr um tipo de dado com ValorLogicoDDHostServidorEMail", dado);
        assertEquals("Domínio encontrado não é o domínio esperado", "casanovadigital.com.br", dado.getCampoInstanciado().getValor());

    }
}
