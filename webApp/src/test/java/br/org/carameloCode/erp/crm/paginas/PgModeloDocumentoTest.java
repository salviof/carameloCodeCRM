/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas;

import br.org.carameloCode.erp.crm.config.TesteWPCrmCarameloCode;
import br.org.carameloCode.erp.crm.paginas.crmAtendimento.PgModeloDocumentoDeAtividade;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDeEntidadeController;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import org.junit.Test;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
public class PgModeloDocumentoTest extends TesteWPCrmCarameloCode {

    @Test
    public void testSomeMethod() {

        AcaoDeEntidadeController acaoController = (AcaoDeEntidadeController) FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_MERGE.getRegistro();

        AcaoGestaoEntidade gestao = (AcaoGestaoEntidade) FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_MB_GERENCIAR.getRegistro();
        for (ComoAcaoDoSistema acao : gestao.getAcoesVinculadas()) {
            System.out.println(acao.getNomeAcao());
            System.out.println(acao.getTipoAcaoSistema());
            System.out.println(acao.getTipoAcaoGenerica());
        }

        gestao.getAcaoSalvarMerge();

        PgModeloDocumentoDeAtividade teste = new PgModeloDocumentoDeAtividade();

        System.out.println(teste.getAcaoSalvarAlteracoes().getNomeAcao());

    }

}
