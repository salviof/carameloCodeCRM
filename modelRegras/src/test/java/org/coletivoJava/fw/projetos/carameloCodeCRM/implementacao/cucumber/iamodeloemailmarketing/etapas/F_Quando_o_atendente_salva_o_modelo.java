package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt.ModuloCRMMauticSync;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Quando;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.FluxoModeloEmailIA;
import org.junit.Assert;

public class F_Quando_o_atendente_salva_o_modelo {

    @Quando(EtapasIAModeloEmailMarketing.QUANDO_O_ATENDENTE_SALVA_O_MODELO)
    public void implementacaoEtapa() {
        FluxoModeloEmailIA.EMAIL_MARKETING.setAssunto("Email teste");
        FluxoModeloEmailIA.EMAIL_MARKETING.setTextoModelo("Teste e-mail");
        ItfRespostaAcaoDoSistema resp = ModuloCRMMauticSync.salvarModeloEmailMArketing(FluxoModeloEmailIA.EMAIL_MARKETING);
        Assert.assertTrue("Falha salvando email marketing", resp.isSucesso());
        FluxoModeloEmailIA.EMAIL_MARKETING = (ModeloEmailMarketing) resp.getRetorno();
    }
}
