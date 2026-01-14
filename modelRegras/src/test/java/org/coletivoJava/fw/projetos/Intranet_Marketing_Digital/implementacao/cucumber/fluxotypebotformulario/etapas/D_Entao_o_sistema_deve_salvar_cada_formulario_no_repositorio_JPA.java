package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotformulario.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.ModuloCRMAplicacao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.fluxotypebotformulario.EtapasFluxoTypebotFormulario;
import cucumber.api.java.pt.Entao;
import org.junit.Assert;

public class D_Entao_o_sistema_deve_salvar_cada_formulario_no_repositorio_JPA {

    @Entao(EtapasFluxoTypebotFormulario.E_O_SISTEMA_DEVE_SALVAR_CADA_FORMULARIO_NO_REPOSITORIO_JPA)
    public void implementacaoEtapa() {
        ItfRespostaAcaoDoSistema resposta = ModuloCRMAplicacao.formularioSincronizar();
        Assert.assertTrue("Falha sincronizando formulario", resposta.isSucesso());
    }
}
