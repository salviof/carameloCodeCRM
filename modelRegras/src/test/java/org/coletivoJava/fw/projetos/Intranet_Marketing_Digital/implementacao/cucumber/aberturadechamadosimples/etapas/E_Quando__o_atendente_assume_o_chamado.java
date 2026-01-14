package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimentoChamado;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import cucumber.api.java.pt.Quando;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import org.junit.Assert;

public class E_Quando__o_atendente_assume_o_chamado {

    @Quando(EtapasAberturaDeChamadoSimples.QUANDO_O_ATENDENTE_ASSUME_O_CHAMADO)
    public void implementacaoEtapa() {
        SBCore.getServicoSessao().logarEmailESenha(FluxoChamadoSimples.USUARIO_VENDEDOR, FluxoChamadoSimples.SENHA_VENDEDOR);
        Assert.assertEquals("O usuario atendimento não efetuou login", FluxoChamadoSimples.USUARIO_VENDEDOR, SBCore.getServicoSessao().getSessaoAtual().getUsuario().getEmail());
        ItfResposta resp = ModuloCRMAtendimentoChamado.chamadoAssumirResponsavel(FluxoChamadoSimples.chamado);
        Assert.assertTrue("Falha assumindo responsável", resp.isSucesso());
    }
}
