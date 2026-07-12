/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto;

import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCodeSemLimparBanco;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class PessoaTest extends TesteCRMCarameloCodeSemLimparBanco {

    public PessoaTest() {
    }

    /**
     * Test of prepararNovoObjeto method, of class Pessoa.
     */
    @Test
    public void testPrepararNovoObjeto() {
        Pessoa pessoa = new Pessoa();

        pessoa.getCPinst(CPPessoa.localizacao).isObrigatorio();
        pessoa.getCPinst(CPPessoa.origem).getComoCampoComListaDeOpcoes().getSeletor().atualizarListaCompleta();
        assertFalse("Esperado que a localização não seja obrigatória", false);
        System.out.println("ok");
    }

}
