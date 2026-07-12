package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimentoChamado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import cucumber.api.java.pt.Quando;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import org.junit.Assert;

public class I_Quando__o_atendente_fecha_o_chamado {

    @Quando(EtapasAberturaDeChamadoSimples.QUANDO_O_ATENDENTE_FECHA_O_CHAMADO)
    public void implementacaoEtapa() {
        FluxoChamadoSimples.renovarConexaoEntityManagerEscopoTeste();
        ItfResposta resp = ModuloCRMAtendimentoChamado.chamadoFinalizar(FluxoChamadoSimples.chamado);
        Assert.assertTrue("Falha finalizando chamado", resp.isSucesso());
    }
}
