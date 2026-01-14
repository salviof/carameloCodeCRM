/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.servico;

import br.org.carameloCode.erp.crm.config.TesteWPCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import org.junit.Test;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;

/**
 *
 * @author salvio
 */
public class ServicoChatViewTest extends TesteWPCrmCarameloCode {

    public ServicoChatViewTest() {
    }

    /**
     * Test of inicio method, of class ServicoChatView.
     */
    @Test
    public void testInicio() {
        ServicoChatView servico = new ServicoChatView();
        servico.inicio();
        PessoaJuridica casanovadigital = UtilSBPersistencia.getRegistroByID(PessoaJuridica.class, 2031l, getEMTeste());

        ComoChatSalaBean salaDebate = servico.getSalaDebate(casanovadigital);
        System.out.println(salaDebate.getCodigoChat());
    }

}
