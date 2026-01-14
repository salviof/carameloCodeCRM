/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implemetation.model.pesquisaatividade;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.pesquisaAtividade.PesquisaAtividade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.CPPesquisaAtividade;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class ValorLogicoPesquisaAtividadeAtividadesEncontradosTest extends TesteCRMCarameloCode {

    public ValorLogicoPesquisaAtividadeAtividadesEncontradosTest() {
    }

    /**
     * Test of getValor method, of class
     * ValorLogicoPesquisaAtividadeAtividadesEncontrados.
     */
    @Test
    public void testGetValor() {
        getEM();
        getEMTeste();
        PesquisaAtividade novapesquisa = new PesquisaAtividade();
        novapesquisa.setDatainicial(UtilCRCDataHora.decrementarDias(new Date(), 60));
        novapesquisa.setDatafinal(new Date());
        novapesquisa.getCPinst(CPPesquisaAtividade.atividadesencontradas).getValor();
        novapesquisa.getAtividadesEncontradas().stream().forEachOrdered(atv -> System.out.println(atv.getId()));
    }

}
