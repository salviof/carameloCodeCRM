package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Dado;
import java.lang.UnsupportedOperationException;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.FluxoModeloEmailIA;

public class E_Dado_o_atendente_seleciona_a_persona_em_um_novo_modelo_de_email {

    @Dado(EtapasIAModeloEmailMarketing.E_O_ATENDENTE_SELECIONA_A_PERSONA_EM_UM_NOVO_MODELO_DE_EMAIL)
    public void implementacaoEtapa() {
        FluxoModeloEmailIA.EMAIL_MARKETING.setNome("Modelo teste IA");
        FluxoModeloEmailIA.EMAIL_MARKETING.setAssunto("Assunto do modelo teste IA");
        FluxoModeloEmailIA.EMAIL_MARKETING.setPersonaCoautoria(FluxoModeloEmailIA.PERSONA);
    }
}
