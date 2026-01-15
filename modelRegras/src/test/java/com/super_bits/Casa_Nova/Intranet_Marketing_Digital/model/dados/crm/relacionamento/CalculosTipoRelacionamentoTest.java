/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.relacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class CalculosTipoRelacionamentoTest extends TesteCRMCarameloCode {

    public CalculosTipoRelacionamentoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of values method, of class CalculosTipoRelacionamento.
     */
    @Test
    public void testValues() {
        try {
            List<TipoRelacionamento> tipos = UtilSBPersistencia.getListaTodos(TipoRelacionamento.class, getEMTeste());
            List<UsuarioCRM> usuarios = UtilSBPersistencia.getListaTodos(UsuarioCRM.class, getEMTeste());
            for (TipoRelacionamento tipo : tipos) {
                for (UsuarioCRM usuario : usuarios) {
                    Long quantidadePorUsuario = tipo.getTotalAtividadesPorUsuario(usuario);
                    if (quantidadePorUsuario > 0) {

                        System.out.println(usuario.getNome() + "--------------->" + quantidadePorUsuario);
                        tipo.getTotalAtividadesPorUsuario(usuario);
                    }
                    System.out.println(tipo.getTotalAtividades());
                }
            }
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

    }

}
