package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Dado;
import java.lang.UnsupportedOperationException;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.FluxoModeloEmailIA;
import org.junit.Assert;

public class A_Dado_um_atendente_autenticado_no_sistema {

    @Dado(EtapasIAModeloEmailMarketing.DADO_UM_ATENDENTE_AUTENTICADO_NO_SISTEMA)
    public void implementacaoEtapa() {
        CarameloCode.getServicoSessao().logarEmailESenha(FluxoModeloEmailIA.EMAIL_ATENDENTE, FluxoModeloEmailIA.SENHA_ATENDIMENTO);
        Assert.assertTrue("Falha autenticando", CarameloCode.getServicoSessao().getSessaoAtual().isIdentificado());
    }
}
