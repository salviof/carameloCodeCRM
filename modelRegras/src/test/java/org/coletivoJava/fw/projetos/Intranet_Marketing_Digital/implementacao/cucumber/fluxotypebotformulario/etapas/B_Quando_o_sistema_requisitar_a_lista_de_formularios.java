package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotformulario.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.ModuloCRMAplicacao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.fluxotypebotformulario.EtapasFluxoTypebotFormulario;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;

public class B_Quando_o_sistema_requisitar_a_lista_de_formularios {

    @Quando(EtapasFluxoTypebotFormulario.QUANDO_O_SISTEMA_REQUISITAR_A_LISTA_DE_FORMULARIOS)
    public void implementacaoEtapa() {

        ItfRespostaAcaoDoSistema resposta = ModuloCRMAplicacao.formularioSincronizar();
        Assert.assertTrue("Falha sincronizando formulario", resposta.isSucesso());
    }
}
