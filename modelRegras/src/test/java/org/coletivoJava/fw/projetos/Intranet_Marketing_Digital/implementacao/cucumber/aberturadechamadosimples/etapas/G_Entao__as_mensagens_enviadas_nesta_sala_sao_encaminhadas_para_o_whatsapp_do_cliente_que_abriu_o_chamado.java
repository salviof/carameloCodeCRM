package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroRegraDeNEgocioChat;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.chat.UtilCRMChat;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import cucumber.api.java.pt.Entao;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import org.junit.Assert;

public class G_Entao__as_mensagens_enviadas_nesta_sala_sao_encaminhadas_para_o_whatsapp_do_cliente_que_abriu_o_chamado {

    @Entao(EtapasAberturaDeChamadoSimples.E_AS_MENSAGENS_ENVIADAS_NESTA_SALA_SAO_ENCAMINHADAS_PARA_O_WHATSAPP_DO_CLIENTE_QUE_ABRIU_O_CHAMADO)
    public void implementacaoEtapa() {
        SBCore.getServicoSessao().logarEmailESenha(FluxoChamadoSimples.USUARIO_VENDEDOR, FluxoChamadoSimples.SENHA_VENDEDOR);
        ComoChatSalaBean sala;
        try {
            sala = ERPChat.MATRIX_ORG.getImplementacaoDoContexto().getSalaByAlias(FluxoChamadoSimples.chamado.getSalaMatrix());
            ERPChat.MATRIX_ORG.getImplementacaoDoContexto().salaEnviarMesagem(sala, UtilCRMChat.gerarUsuarioAtendimento((UsuarioCRM) SBCore.getUsuarioLogado()),
                    "tst", "apenas teste");
        } catch (ErroConexaoServicoChat | ErroRegraDeNEgocioChat ex) {
            Assert.fail(ex.getMessage());
        }

    }
}
