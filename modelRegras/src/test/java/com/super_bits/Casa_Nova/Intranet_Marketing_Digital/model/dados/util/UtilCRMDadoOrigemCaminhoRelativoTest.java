/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.util;

import br.org.carameloCode.erp.modulo.crm.util.UtilCRMDadoOrigemCaminhoRelativo;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class UtilCRMDadoOrigemCaminhoRelativoTest extends TesteCRMCarameloCode {

    public UtilCRMDadoOrigemCaminhoRelativoTest() {
    }

    /**
     * Test of validarCaminhoRelativoContato method, of class
     * UtilCRMDadoOrigemCaminhoRelativo.
     */
    @Test
    public void testValidarCaminhoRelativoContato() throws Exception {
        UtilCRMDadoOrigemCaminhoRelativo.validarCaminhoRelativoContato("nome");
        System.out.println("ok");
    }

}
