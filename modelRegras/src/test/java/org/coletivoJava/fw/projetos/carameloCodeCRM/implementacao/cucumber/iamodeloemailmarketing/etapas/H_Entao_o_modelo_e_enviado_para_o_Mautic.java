package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt.ModuloCRMMauticSync;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Entao;
import java.lang.UnsupportedOperationException;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.FluxoModeloEmailIA;
import org.junit.Assert;

public class H_Entao_o_modelo_e_enviado_para_o_Mautic {

    @Entao(EtapasIAModeloEmailMarketing.E_O_MODELO_E_ENVIADO_PARA_O_MAUTIC)
    public void implementacaoEtapa() {

        ItfRespostaAcaoDoSistema resp = ModuloCRMMauticSync.enviarModeloEmailMktParaMautic(FluxoModeloEmailIA.EMAIL_MARKETING);
        Assert.assertTrue("Falha enviando email marketing para o Mautic", resp.isSucesso());
    }
}
