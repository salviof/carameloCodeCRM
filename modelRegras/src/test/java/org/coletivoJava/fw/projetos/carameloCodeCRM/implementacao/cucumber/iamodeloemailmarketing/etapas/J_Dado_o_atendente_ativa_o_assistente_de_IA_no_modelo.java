package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmIA.ModuloCRM_IA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.FabTipoExecucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Dado;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.FluxoModeloEmailIA;
import org.junit.Assert;

public class J_Dado_o_atendente_ativa_o_assistente_de_IA_no_modelo {

    @Dado(EtapasIAModeloEmailMarketing.E_O_ATENDENTE_ATIVA_O_ASSISTENTE_DE_IA_NO_MODELO)
    public void implementacaoEtapa() {

        FluxoModeloEmailIA.EXECUCAO_IA = new ExecucaoIAModeloEmailMkt();
        FluxoModeloEmailIA.EXECUCAO_IA.setTipoExecucao(FabTipoExecucaoIA.EMAIL_MARKETING.getRegistro());
        FluxoModeloEmailIA.EXECUCAO_IA.setModeloEmailMarketing(FluxoModeloEmailIA.EMAIL_MARKETING);
        ItfRespostaAcaoDoSistema resp = ModuloCRM_IA.criarDialogoModeloEmailMarketing(FluxoModeloEmailIA.EXECUCAO_IA);
        Assert.assertTrue("Falha iniciando dialogo", resp.isSucesso());
        FluxoModeloEmailIA.EXECUCAO_IA = (ExecucaoIAModeloEmailMkt) resp.getRetorno();
        Assert.assertNotNull("Persona não vinculada ao dialogo", FluxoModeloEmailIA.EXECUCAO_IA.getPersona());
    }
}
