/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.relacionamento.responsavel;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import org.junit.Test;

/**
 *
 * @author salviofurbino
 */
public class RepositorioResponsavelRelacionamentoTest extends TesteCRMCarameloCode {

    public RepositorioResponsavelRelacionamentoTest() {
    }

    @Test

    public void testSomeMethod() {

        renovarConexao();
        // TODO review the generated test code and remove the default call to fail.

        // BigInteger quantida = (BigInteger) UtilSBPersistencia.getRegistroBySQL("select count(id) from ProspectoEmpresa p \n"
        //       + "inner join prospectos_usuarios_responsaveis pr on p.id=pr.prospecto_id \n"
        //      + "where relacionamento_id=" + FabTipoRelacionamentoMarketingDigital.CADASTRADO.getRegistro().getId()
        //    + " and pr.usuarioCRM_id=" + 1 + "",
        //      SBCore.get().getAcessoDadosDoContexto().getEntitiManager());
        //System.out.println(quantida);
    }

}
