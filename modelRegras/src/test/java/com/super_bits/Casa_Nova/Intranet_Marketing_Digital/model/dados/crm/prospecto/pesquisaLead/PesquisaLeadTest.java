/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.prospecto.pesquisaLead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.PesquisaLead;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.CPPesquisaLead;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class PesquisaLeadTest extends TesteCRMCarameloCode {

    public PesquisaLeadTest() {
    }

    /**
     * Test of getId method, of class PesquisaLead.
     */
    @Test
    public void testGetId() {
        SBCore.getServicoSessao().logarEmailESenha("atendimento@casanovadigital.com.br", "123");
        PesquisaLead novaPesquisa = new PesquisaLead();

        novaPesquisa.getCampoInstanciadoByNomeOuAnotacao(CPPesquisaLead.leadsencontrados).getValor();
        List<PessoaJuridica> pessoas = (List<PessoaJuridica>) novaPesquisa.getCPinst(CPPesquisaLead.leadsencontrados).getValor();
        System.out.println(novaPesquisa.getLeadsEncontrados());
        pessoas.stream().forEach(p
                -> {
            System.out.println(p.getNome());
        }
        );

    }

}
