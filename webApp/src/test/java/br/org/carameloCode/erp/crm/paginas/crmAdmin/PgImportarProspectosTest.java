/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.crm.config.TesteWPCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilCRCArquivos;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class PgImportarProspectosTest extends TesteWPCrmCarameloCode {

    /**
     * Test of executarAcaoSelecionada method, of class PgImportarProspectos.
     */
    @Test
    public void testExecutarAcaoSelecionada() {

        PgImportarProspectos paginaProsp = new PgImportarProspectos();

        paginaProsp.setAcaoSelecionada(FabAcaoCrmAdmin.IMPORTADOR_PROSPECTO_CTR_IMPORTAR.getRegistro());
        String arquivo = paginaProsp.getCaminhoArquhivoImportacao();
        UtilCRCArquivos.copiarArquivos("/home/superBits/projetos/Casa_Nova/source/Intranet_Marketing_Digital/dados/tabela_prospectos.xls", arquivo);
        paginaProsp.executarAcaoSelecionada();

    }

}
