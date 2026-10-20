package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.etapas;

import br.org.carameloCode.erp.modulo.ia.controller.ModuloExecucaoAcao;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.iamodeloemailmarketing.EtapasIAModeloEmailMarketing;
import cucumber.api.java.pt.Quando;
import java.lang.UnsupportedOperationException;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.iamodeloemailmarketing.FluxoModeloEmailIA;
import org.junit.Assert;

public class B_Quando_o_atendente_cria_uma_nova_persona {

    @Quando(EtapasIAModeloEmailMarketing.QUANDO_O_ATENDENTE_CRIA_UMA_NOVA_PERSONA)
    public void implementacaoEtapa() {

        Persona persona = new Persona();
        persona.setNome("João ");
        persona.setLimitePalavras(5);
        persona.setPublicoAlvo("Desenvolvedor Testando");
        persona.setTipoPersona("Persona teste");
        persona.setAtivo(true);
        persona.setInstrucoesAdicionais("Responda com poucas palavras, gastando poucos tokens, estamos apenas testando, mas respeite a instrução principal");
        ItfRespostaAcaoDoSistema resp = ModuloExecucaoAcao.personaIaAtualizar(persona);
        Assert.assertTrue("Falha sanvando Persona", resp.isSucesso());
        FluxoModeloEmailIA.PERSONA = (Persona) resp.getRetorno();

    }
}
