/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.dadosDinamicos;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class DadoCRMTest extends TesteCRMCarameloCode {

    public DadoCRMTest() {
    }

    @Test
    public void testGetDocumentoPessoa() {

        PessoaJuridica pessoa = UtilSBPersistencia.getRegistroByID(PessoaJuridica.class, 2830l, getEM());
        for (DadoCRM dado : pessoa.getDadosColetados()) {

            switch (dado.getTipoCampo()) {
                case OBJETO_DE_UMA_LISTA:
                    dado.getCampoInstanciado();
                    dado.getCampoInstanciado().getComoCampoSeltorItem().getOrigem();
                    break;

                default:

            }

        }

    }

}
