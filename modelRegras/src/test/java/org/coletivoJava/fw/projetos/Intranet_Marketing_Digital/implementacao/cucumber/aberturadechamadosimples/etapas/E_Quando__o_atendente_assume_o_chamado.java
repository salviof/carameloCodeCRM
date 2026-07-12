package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimentoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import cucumber.api.java.pt.Quando;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import org.junit.Assert;

public class E_Quando__o_atendente_assume_o_chamado {

    @Quando(EtapasAberturaDeChamadoSimples.QUANDO_O_ATENDENTE_ASSUME_O_CHAMADO)
    public void implementacaoEtapa() {
        SBCore.getServicoSessao().logarEmailESenha(FluxoChamadoSimples.EMAIL_USUARIO_VENDEDOR, FluxoChamadoSimples.SENHA_VENDEDOR);
        ChamadoCliente chamadp = UtilSBPersistencia.loadEntidade(FluxoChamadoSimples.chamado, FluxoChamadoSimples.getEM());

        Assert.assertEquals("O usuario atendimento não efetuou login", FluxoChamadoSimples.EMAIL_USUARIO_VENDEDOR, SBCore.getServicoSessao().getSessaoAtual().getUsuario().getEmail());

        ItfResposta resp = ModuloCRMAtendimentoChamado.chamadoAssumirResponsavel(chamadp);
        Assert.assertTrue("Falha assumindo responsável", resp.isSucesso());
    }
}
