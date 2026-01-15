/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.dadosDinamicos;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.FabDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.FabTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.opcaoPersonalizada.OpcaoPersonalizada;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class TipoDadoCRMTest extends TesteCRMCarameloCode {

    /**
     * Test of getTemplateCamposDinamicos method, of class TipoDadoCRM.
     */
    @Test
    public void testGetTemplateCamposDinamicos() {

        TipoDadoCRM tipoDAdo = FabTipoDadoCRM.PORTE.getRegistro();

        tipoDAdo = UtilSBPersistencia.mergeRegistro(tipoDAdo, getEMTeste());
        tipoDAdo.setNomeClasseAtributoDeclarado(OpcaoPersonalizada.class.getSimpleName());
        tipoDAdo.getListaDeOpcoes();

        AtividadeCRM atividade = UtilSBPersistencia.getListaTodos(AtividadeCRM.class, getEMTeste(), 1).get(0);

        DadoCRM dado = FabDadoCRM.getNovoDadoDeAtividade("", atividade, tipoDAdo);
        dado.getCampoInstanciado().getComoCampoSeltorItem().getSeletor().getOrigem();
        dado.getCampoInstanciado().setValor(dado.getCampoInstanciado().getComoCampoSeltorItem().getSeletor().getOrigem().get(0));
        System.out.println(dado.getCampoInstanciado().getValor());

        OpcaoPersonalizada opcao = (OpcaoPersonalizada) dado.getCampoInstanciado().getValor();
        System.out.println(opcao.getNome());

    }

}
