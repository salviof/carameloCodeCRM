/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.prospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.CPPessoaJuridica;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class PessoaJuridicaTest extends TesteCRMCarameloCode {

    public PessoaJuridicaTest() {
    }

    /**
     * Test of loadEmpresabyContato method, of class PessoaJuridica.
     */
    @Test
    public void testLoadEmpresabyContato() {

        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        final String site = "https://www.saudemais.co.ao/";
        try {

            pessoaJuridica.getCPinst(CPPessoaJuridica.site).setValorSeValido(site);
        } catch (ErroValidacao ex) {
            Logger.getLogger(ProspectoEmpresaTest.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsupportedOperationException("Falha acessando " + site);
        }
    }

}
