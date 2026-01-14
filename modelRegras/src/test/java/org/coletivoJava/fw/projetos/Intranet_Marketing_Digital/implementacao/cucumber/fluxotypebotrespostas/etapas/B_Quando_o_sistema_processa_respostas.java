package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotrespostas.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.ModuloCRMAplicacao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.fluxotypebotrespostas.EtapasFluxoTypebotRespostas;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;

public class B_Quando_o_sistema_processa_respostas {

    @Quando(EtapasFluxoTypebotRespostas.QUANDO_O_SISTEMA_PROCESSA_RESPOSTAS)
    public void implementacaoEtapa() {
        ItfResposta resp = ModuloCRMAplicacao.respostasSincronizar();
        System.out.println(resp.isSucesso());
        Assert.assertTrue("Falha processando respostas", resp.isSucesso());
    }
}
