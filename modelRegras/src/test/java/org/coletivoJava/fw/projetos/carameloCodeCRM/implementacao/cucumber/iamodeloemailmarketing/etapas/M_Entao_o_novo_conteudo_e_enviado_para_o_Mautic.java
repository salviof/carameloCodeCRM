package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import org.junit.Assert;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt.ModuloCRMMauticSync;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Entao;
import java.lang.UnsupportedOperationException;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.FluxoModeloEmailIA;

public class M_Entao_o_novo_conteudo_e_enviado_para_o_Mautic {

    @Entao(EtapasIAModeloEmailMarketing.E_O_NOVO_CONTEUDO_E_ENVIADO_PARA_O_MAUTIC)
    public void implementacaoEtapa() {
        ItfRespostaAcaoDoSistema resposta = ModuloCRMMauticSync.enviarModeloEmailMktParaMautic(FluxoModeloEmailIA.EMAIL_MARKETING);
        Assert.assertTrue("Falha enviando o novo conteudo para o Mautic", resposta.isSucesso());
        FluxoModeloEmailIA.atualizarEntidadesDeclaradas();
        System.out.println("ValorGasto= " + FluxoModeloEmailIA.EXECUCAO_IA.getValorGasto());
    }
}
