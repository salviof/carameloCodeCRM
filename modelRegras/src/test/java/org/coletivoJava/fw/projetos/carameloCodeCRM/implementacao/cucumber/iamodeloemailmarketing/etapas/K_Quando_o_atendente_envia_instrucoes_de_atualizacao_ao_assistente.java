package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmIA.ModuloCRM_IA;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Quando;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.FluxoModeloEmailIA;
import org.junit.Assert;

public class K_Quando_o_atendente_envia_instrucoes_de_atualizacao_ao_assistente {

    @Quando(EtapasIAModeloEmailMarketing.QUANDO_O_ATENDENTE_ENVIA_INSTRUCOES_DE_ATUALIZACAO_AO_ASSISTENTE)
    public void implementacaoEtapa() {
        FluxoModeloEmailIA.EXECUCAO_IA.setNovaMensagem("Crie um email teste com pouco texto, gastando poucos tokens");
        ItfRespostaAcaoDoSistema resp = ModuloCRM_IA.modeloDocumentoAtualizar(FluxoModeloEmailIA.EXECUCAO_IA);
        String msg = resp.isSucesso() ? "" : resp.getMensagens().get(0).getMenssagem();
        Assert.assertTrue("Falha executando ia" + msg, resp.isSucesso());
        FluxoModeloEmailIA.EXECUCAO_IA = (ExecucaoIAModeloEmailMkt) resp.getRetorno();
    }
}
