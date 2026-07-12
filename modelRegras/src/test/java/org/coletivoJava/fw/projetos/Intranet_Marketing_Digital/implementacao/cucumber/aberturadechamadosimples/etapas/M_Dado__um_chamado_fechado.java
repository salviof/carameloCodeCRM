package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import cucumber.api.java.pt.Dado;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;

public class M_Dado__um_chamado_fechado {

    @Dado(EtapasAberturaDeChamadoSimples.DADO_UM_CHAMADO_FECHADO)
    public void implementacaoEtapa() {
        FluxoChamadoSimples.renovarConexaoEntityManagerEscopoTeste();
    }
}
