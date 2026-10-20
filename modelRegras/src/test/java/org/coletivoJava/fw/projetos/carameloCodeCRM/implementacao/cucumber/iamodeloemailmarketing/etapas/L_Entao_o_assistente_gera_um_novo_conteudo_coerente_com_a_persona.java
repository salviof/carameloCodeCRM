package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmIA.ModuloCRM_IA;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Entao;
import java.lang.UnsupportedOperationException;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.FluxoModeloEmailIA;
import org.junit.Assert;

public class L_Entao_o_assistente_gera_um_novo_conteudo_coerente_com_a_persona {

    @Entao(EtapasIAModeloEmailMarketing.ENTAO_O_ASSISTENTE_GERA_UM_NOVO_CONTEUDO_COERENTE_COM_A_PERSONA)
    public void implementacaoEtapa() {
        //ESte metodo atualiza as entidades estaticas declarasdas em FluxoModeloEmailIA
        ItfRespostaAcaoDoSistema resp = ModuloCRM_IA.modeloDocumentoAtualizar(FluxoModeloEmailIA.EXECUCAO_IA);
        Assert.assertTrue("Falha atualizando documento", resp.isSucesso());
        FluxoModeloEmailIA.atualizarEntidadesDeclaradas();
        Assert.assertNotNull("conteúdo não encontrado", FluxoModeloEmailIA.EMAIL_MARKETING.getTextoModelo());
        Assert.assertTrue("conteúdo não encontrado", !FluxoModeloEmailIA.EMAIL_MARKETING.getTextoModelo().isEmpty());
    }
}
