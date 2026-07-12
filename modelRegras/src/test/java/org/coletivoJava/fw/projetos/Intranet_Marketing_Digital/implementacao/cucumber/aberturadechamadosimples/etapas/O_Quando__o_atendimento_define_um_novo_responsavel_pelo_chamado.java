package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimentoChamado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmChamado.ModuloCRMChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import cucumber.api.java.pt.Quando;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import org.junit.Assert;

public class O_Quando__o_atendimento_define_um_novo_responsavel_pelo_chamado {

    @Quando(EtapasAberturaDeChamadoSimples.E_O_ATENDIMENTO_DEFINE_UM_NOVO_RESPONSAVEL_PELO_CHAMADO)
    public void implementacaoEtapa() {

        ChamadoCliente chamado = FluxoChamadoSimples.chamado;
        chamado.setAtendenteResponsavel((UsuarioCRM) CarameloCode.getServicoPermissao().getUsuarioByEmail("salvio@casanovadigital.com.br"));
        ItfRespostaAcaoDoSistema resp = ModuloCRMAtendimentoChamado.chamadoDefinirResponsavel(chamado);
        Assert.assertTrue("Falha definindo novo responsável", resp.isSucesso());

    }
}
