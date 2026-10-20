package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import br.org.carameloCode.erp.modulo.crm.api.model.modeloemail.CPModeloEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Entao;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.FluxoModeloEmailIA;
import org.junit.Assert;

public class C_Entao_a_persona_aparece_na_lista_de_personas_para_novos_modelos_de_email {

    @Entao(EtapasIAModeloEmailMarketing.ENTAO_A_PERSONA_APARECE_NA_LISTA_DE_PERSONAS_PARA_NOVOS_MODELOS_DE_EMAIL)
    public void implementacaoEtapa() {

        FluxoModeloEmailIA.EMAIL_MARKETING = new ModeloEmailMarketing();
//        FluxoModeloEmailIA.EMAIL_MARKETING.getCPinst(CPModeloEmail.personacoautoria).getComoCampoSeltorItem().getSeletor().atualizarListaCompleta();
        Assert.assertFalse("Lista de persona não aparece", FluxoModeloEmailIA.EMAIL_MARKETING.getCPinst(CPModeloEmail.personacoautoria).getComoCampoSeltorItem().getSeletor().getOrigem().isEmpty());

    }
}
