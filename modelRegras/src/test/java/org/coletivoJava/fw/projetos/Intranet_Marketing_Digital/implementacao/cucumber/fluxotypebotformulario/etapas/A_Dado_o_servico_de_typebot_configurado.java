package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.fluxotypebotformulario.etapas;

import com.super_bits.Super_Bits.intTypebot.config.FabConfigModuloTypebot;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.fluxotypebotformulario.EtapasFluxoTypebotFormulario;
import cucumber.api.java.pt.Dado;
import java.lang.UnsupportedOperationException;

public class A_Dado_o_servico_de_typebot_configurado {

    @Dado(EtapasFluxoTypebotFormulario.DADO_O_SERVICO_DE_TYPEBOT_CONFIGURADO)
    public void implementacaoEtapa() {
        String url = FabConfigModuloTypebot.URL_SERVIDOR.getValorParametroSistema();
        System.out.println(url);
    }
}
