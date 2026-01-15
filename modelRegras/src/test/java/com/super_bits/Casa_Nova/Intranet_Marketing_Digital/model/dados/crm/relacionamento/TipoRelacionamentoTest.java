/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.relacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoExibicaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class TipoRelacionamentoTest extends TesteCRMCarameloCode {

    public TipoRelacionamentoTest() {
    }

    /**
     * Test of getNome method, of class TipoRelacionamento.
     */
    @Test
    public void testGetNome() {
        try {
            TipoRelacionamento tipoRelacionamento = new TipoRelacionamento();

            ItfCampoInstanciado campoInstanciado = tipoRelacionamento.getCampoInstanciadoByNomeOuAnotacao("dadosNescessarios");
            //   ItfCampoInstSeletorItens seletorDeitens = campoInstanciado.getComoSeltorItens();
            //System.out.println(campoInstanciado.getComoSeltorItens().getGrupoCampoExibicao().getCampos());

            for (ItfCampoExibicaoFormulario campo : campoInstanciado.getComoCampoSeltorItem().getGrupoCampoExibicao().getCamposArray()) {
                System.out.println(campo.getLabel());
            }
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

}
