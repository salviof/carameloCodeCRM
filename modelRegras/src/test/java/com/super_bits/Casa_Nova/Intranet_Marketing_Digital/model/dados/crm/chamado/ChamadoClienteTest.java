/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.chamado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.CPChamadoCliente;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class ChamadoClienteTest extends TesteCRMCarameloCode {

    public ChamadoClienteTest() {
    }

    /**
     * Test of prepararNovoObjeto method, of class ChamadoCliente.
     */
    @Test
    public void testPrepararNovoObjeto() throws Exception {
        ChamadoCliente chamado = new ChamadoCliente();
        ItfCampoInstanciado cp = chamado.getCampoInstanciadoByNomeOuAnotacao(CPChamadoCliente.tipochamado);
        cp.getComoCampoSeltorItem().getOrigem();
    }

}
