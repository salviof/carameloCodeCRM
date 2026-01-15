/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.prospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoExibicaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoExibicaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.CPPessoaJuridica;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class ProspectoEmpresaTest extends TesteCRMCarameloCode {

    public ProspectoEmpresaTest() {
    }

    /**
     * Test of getListaDadosNaoColetadosCRM method, of class PessoaJuridica.
     */
    @Test
    public void testGetListaDadosNaoColetadosCRM_0args() {
        String[] campos = {"contatosProspecto[].nome", "contatosProspecto[].email"};
        GrupoCampos grupoCampo = new GrupoCampos("Teste");
        try {
            for (String campo : campos) {
                grupoCampo.adicionarCampo(new CaminhoCampoExibicaoFormulario(new CaminhoCampoReflexao(PessoaJuridica.class.getSimpleName() + "." + campo), grupoCampo.getNomeIdentificadorSlug()));
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possível gerar um grup de campos com " + campos, t);
        }

        for (ItfCampoExibicaoFormulario campo : grupoCampo.getCampos()) {
            System.out.println(campo.getUltimoNome());

        }

    }

    @Test
    public void validacaoSite() {
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
