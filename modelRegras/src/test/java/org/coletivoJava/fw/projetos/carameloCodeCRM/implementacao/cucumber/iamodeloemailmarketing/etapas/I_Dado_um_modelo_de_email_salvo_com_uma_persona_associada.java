package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Dado;
import java.lang.UnsupportedOperationException;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.FluxoModeloEmailIA;
import org.junit.Assert;

public class I_Dado_um_modelo_de_email_salvo_com_uma_persona_associada {

    @Dado(EtapasIAModeloEmailMarketing.DADO_UM_MODELO_DE_EMAIL_SALVO_COM_UMA_PERSONA_ASSOCIADA)
    public void implementacaoEtapa() {
        //ESte metodo atualiza as entidades estaticas declarasdas em FluxoModeloEmailIA
        FluxoModeloEmailIA.atualizarEntidadesDeclaradas();
        Assert.assertNotNull("Persona não encontrada", FluxoModeloEmailIA.EMAIL_MARKETING.getPersonaCoautoria());
    }
}
