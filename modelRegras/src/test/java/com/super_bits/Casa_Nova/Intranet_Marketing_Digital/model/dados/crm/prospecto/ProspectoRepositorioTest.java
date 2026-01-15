/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.prospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.ProspectoRepositorio;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.historicoRelacionamento.HistoricoRelacionamento;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author salviofurbino
 */
public class ProspectoRepositorioTest extends TesteCRMCarameloCode {

    public ProspectoRepositorioTest() {
    }

    /**
     * Test of getUltimosProspectosAlterados method, of class
     * ProspectoRepositorio.
     */
    @Test
    public void testGetUltimosProspectosAlterados() {
        List<HistoricoRelacionamento> historico = ProspectoRepositorio.getUltimasAlteracoesProspectos();
        System.out.println(historico.size());
    }

}
