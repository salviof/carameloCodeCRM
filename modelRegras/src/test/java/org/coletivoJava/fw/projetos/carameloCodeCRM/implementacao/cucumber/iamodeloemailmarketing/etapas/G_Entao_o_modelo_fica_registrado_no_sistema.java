package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.FluxoModeloEmailIA;
import org.junit.Assert;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Entao;
import java.lang.UnsupportedOperationException;

public class G_Entao_o_modelo_fica_registrado_no_sistema {

    @Entao(EtapasIAModeloEmailMarketing.ENTAO_O_MODELO_FICA_REGISTRADO_NO_SISTEMA)
    public void implementacaoEtapa() {
        Assert.assertNotNull("Modelo não foi salvo", FluxoModeloEmailIA.EMAIL_MARKETING.getId());
        //ESte metodo atualiza as entidades estaticas declarasdas em FluxoModeloEmailIA
        FluxoModeloEmailIA.atualizarEntidadesDeclaradas();
        Assert.assertNotNull("Modelo não encontrado no sistema", FluxoModeloEmailIA.EMAIL_MARKETING);
        Assert.assertNotNull("Persona não registrada no modelo", FluxoModeloEmailIA.EMAIL_MARKETING.getPersonaCoautoria());
    }
}
