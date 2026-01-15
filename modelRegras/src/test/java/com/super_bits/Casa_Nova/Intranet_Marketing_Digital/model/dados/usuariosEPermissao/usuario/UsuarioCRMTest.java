/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.usuariosEPermissao.usuario;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class UsuarioCRMTest extends TesteCRMCarameloCode {

    public UsuarioCRMTest() {
    }

    /**
     * Test of prepararNovoObjeto method, of class UsuarioCRM.
     */
    public void testeLogin() {
        getEM();
        try {
            SBCore.getServicoSessao().logarEmailESenha("salviof@gmail.com", "123");
            ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(PessoaJuridica.class, UtilSBPersistencia.getEMDoContexto());
            novaConsulta.addCondicaoManyToManyContendoObjeto("usuariosResponsaveis", SBCore.getUsuarioLogado());

            novaConsulta.resultadoRegistros();
            System.out.println(novaConsulta.resultadoRegistros().size());
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    @Test
    public void testeListUsuariosDesativados() {

        List<UsuarioCRM> usuarios = UtilSBPersistencia.getListaRegistrosByHQL("from UsuarioCRM where ativo = 1", -1);
        System.out.println(usuarios);
    }

}
