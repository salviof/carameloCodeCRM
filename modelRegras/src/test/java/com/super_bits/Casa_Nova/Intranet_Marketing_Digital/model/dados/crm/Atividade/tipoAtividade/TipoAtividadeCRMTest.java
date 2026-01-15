/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.Atividade.tipoAtividade;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class TipoAtividadeCRMTest extends TesteCRMCarameloCode {

    public TipoAtividadeCRMTest() {
    }

    /**
     * Test of getAcoesDePluginsDisponiveis method, of class TipoAtividadeCRM.
     */
    @Test
    public void testGetAcoesDePluginsDisponiveis() {

        List<TipoAtividadeCRM> tiposDeAtividade = UtilSBPersistencia.getListaTodos(TipoAtividadeCRM.class, getEMTeste());
        for (TipoAtividadeCRM tipo : tiposDeAtividade) {
            ItfResposta resp = ModuloCRMAdmin.tipoAtividadeExcluir(tipo);
            assertTrue("Falhou ao excluir", resp.isSucesso());
        }

    }

}
