/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.servico;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServico;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class TipoServicoTest extends TesteCRMCarameloCode {

    public TipoServicoTest() {
    }

    /**
     * Test of getValorMensalMin method, of class TipoServico.
     */
    @Test
    public void testeExclusao() {

        List<TipoServico> servicos = UtilSBPersistencia.getListaTodos(TipoServico.class, getEM());

        for (TipoServico tipo : servicos) {
            if (tipo.getNome().toLowerCase().contains("excluir")) {
                ModuloCRMAdmin.tipoServicoRemover(tipo);
            }
        }

    }

}
