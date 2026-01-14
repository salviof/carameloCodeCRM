/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.crm.config.TesteWPCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstSeletorItens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class PgTipoRelacionamentoTest extends TesteWPCrmCarameloCode {

    public PgTipoRelacionamentoTest() {
    }

    public void depurarCampoInstanciado(TipoRelacionamento tipoRel) {

        ItfCampoInstanciado campoInstanciado = tipoRel.getCampoByNomeOuAnotacao("tiposAtividadeDisponiveis");
        ItfCampoInstSeletorItens seltorItens = campoInstanciado.getComoSeltorItens();
        System.out.println("Opcoes=" + seltorItens.getCampoSeletorItens().getOrigem());

    }

    /**
     * Test of inicio method, of class PgTipoRelacionamento.
     */
    @Test
    public void testInicio() {
        try {
            System.out.println("inicio");
            PgTipoRelacionamento pagina = new PgTipoRelacionamento();
            pagina.inicio();

            pagina.setEntidadeSelecionada(pagina.getEntidadesListadas().get(0));

            for (TipoRelacionamento tipo : pagina.getEntidadesListadas()) {
                depurarCampoInstanciado(tipo);
            }

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

}
