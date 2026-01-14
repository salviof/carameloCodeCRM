/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.crm.config.TesteWPCrmCarameloCode;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class PgExecutarAtividadeTest extends TesteWPCrmCarameloCode {

    /**
     * Test of renovarEMPagina method, of class PgExecutarAtividade.
     */
    @Test
    public void testeEditarNovaAtividade() {
        try {
            PgExecutarAtividade paginaNovaAtividade = new PgExecutarAtividade();
            paginaNovaAtividade.abrePagina();
            paginaNovaAtividade.inicio();
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
        // TODO review the generated test code and remove the default call to fail.

    }

}
