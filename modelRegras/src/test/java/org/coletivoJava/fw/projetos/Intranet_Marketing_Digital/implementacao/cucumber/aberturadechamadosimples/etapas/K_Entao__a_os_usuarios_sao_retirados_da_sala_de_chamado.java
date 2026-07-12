package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.chat.UtilCRMChat;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import cucumber.api.java.pt.Entao;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.junit.Assert;

public class K_Entao__a_os_usuarios_sao_retirados_da_sala_de_chamado {

    @Entao(EtapasAberturaDeChamadoSimples.E_A_OS_USUARIOS_SAO_RETIRADOS_DA_SALA_DE_CHAMADO)
    public void implementacaoEtapa() {
        FluxoChamadoSimples.renovarConexaoEntityManagerEscopoTeste();
        ChamadoCliente chamado = FluxoChamadoSimples.chamado;

        try {
            String salaMatrix = chamado.getSalaMatrix();
            ComoChatSalaBean sala = UtilCRMChat.chatService.getSalaByAlias(salaMatrix);
            sala = UtilCRMChat.chatService.getSalaAtualizada(sala);
            if (sala.getUsuarios().stream().filter(usr -> usr.getEmail() != null && UtilCRMChat.chatService.isUmUsuarioAtendimento(usr))
                    .findFirst().isPresent()) {
                Assert.fail("O usuário de atendimento não foi removido");
            }
        } catch (Throwable ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "FAlha buscando sala", ex);

        }
    }
}
