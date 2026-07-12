package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import cucumber.api.java.pt.Entao;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import org.junit.Assert;

public class Q_Entao__o_responsavel_e_adicionado_na_sala_junto_ao_atendente_principal {

    @Entao(EtapasAberturaDeChamadoSimples.E_O_RESPONSAVEL_E_ADICIONADO_NA_SALA_JUNTO_AO_ATENDENTE_PRINCIPAL)
    public void implementacaoEtapa() {
        FluxoChamadoSimples.atualizarEntidadesDeclaradas();
        ChamadoCliente chamado = FluxoChamadoSimples.chamado;

        UsuarioCRM usuario = (UsuarioCRM) CarameloCode.getServicoPermissao().getUsuarioByEmail("salvio@casanovadigital.com.br");
        Assert.assertNotNull("Atendente não foi definido", chamado.getAtendenteResponsavel());
        Assert.assertEquals("Responsável não alterou", usuario, chamado.getAtendenteResponsavel());

    }
}
