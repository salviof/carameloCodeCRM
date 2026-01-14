/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas;

import br.org.carameloCode.erp.crm.config.TesteWPCrmCarameloCode;
import br.org.carameloCode.erp.crm.paginas.crmAtendimento.PgMeusProspectos;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class PgMeusProspectosTest extends TesteWPCrmCarameloCode {

    @Test
    public void testeListaMeusProspectos() {
        PgMeusProspectos pagina = new PgMeusProspectos();
        SBCore.getServicoSessao().logarEmailESenha("atendimento@casanovadigital.com.br", "123");
        pagina.init();
//        pagina.setOrigemSelecionada(pagina.getOrigens().get(0));
//        pagina.filtrar();

    }

}
