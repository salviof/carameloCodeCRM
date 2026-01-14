/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.crm.config.TesteWPCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class PgProspectosTest extends TesteWPCrmCarameloCode {

    /**
     * Test of setTermoPesquisa method, of class PgProspectos.
     */
    @Test
    public void testSetTermoPesquisa() {
        try {
            ItfAcaoGerenciarEntidade acao = FabAcaoCRMAtendimento.PROSPECTO_MB_GERENCIAR.getRegistro().getComoGestaoEntidade();
            acao.isTemEditar();
            acao.getAcaoFormularioListarPadrao();
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

}
