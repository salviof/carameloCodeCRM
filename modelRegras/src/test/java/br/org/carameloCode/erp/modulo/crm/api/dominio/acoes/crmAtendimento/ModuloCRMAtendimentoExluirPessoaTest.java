/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCodeSemLimparBanco;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class ModuloCRMAtendimentoExluirPessoaTest extends TesteCRMCarameloCodeSemLimparBanco {

    @Test
    public void exlusao() {
        PessoaJuridica pessoa = UtilSBPersistencia.getRegistroByID(PessoaJuridica.class, 2031l, getEM());
        SBCore.getServicoSessao().logarEmailESenha("salvio@casanovadigital.com.br", "semSenha@123");
        ModuloCRMAtendimento.prospectoRemover(pessoa);
    }
}
