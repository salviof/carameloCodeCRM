package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabStatusChamado;
import cucumber.api.java.pt.Entao;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import org.junit.Assert;

public class L_Entao__o_statuso_do_chamado_e_alterado_para_finalizado {

    @Entao(EtapasAberturaDeChamadoSimples.E_O_STATUSO_DO_CHAMADO_E_ALTERADO_PARA_FINALIZADO)
    public void implementacaoEtapa() {
        FluxoChamadoSimples.atualizarEntidadesDeclaradas();

        Assert.assertEquals("Esperado um chamado finalizado", FluxoChamadoSimples.chamado.getStatus().getId(), FabStatusChamado.FINALIZADO.getRegistro().getId());

    }
}
