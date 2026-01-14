package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.model.ItfNotificacaoUsuarioChat;
import cucumber.api.java.pt.Entao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;

public class C_Entao__o_atendente_principal_do_cliente_e_notificado_pelo_chat_interno {

    @Entao(EtapasAberturaDeChamadoSimples.ENTAO_O_ATENDENTE_PRINCIPAL_DO_CLIENTE_E_NOTIFICADO_PELO_CHAT_INTERNO)
    public void implementacaoEtapa() {
        List<ItfNotificacaoUsuarioChat> notificacoes;
        try {
            notificacoes = ERPChat.MATRIX_ORG.getImplementacaoDoContexto().getUltimasNotificacoesUsuarioAdmin();
            for (ItfNotificacaoUsuarioChat notificacao : notificacoes) {
                System.out.println(notificacao.getConteudo());
            }
        } catch (ErroConexaoServicoChat ex) {
            Logger.getLogger(C_Entao__o_atendente_principal_do_cliente_e_notificado_pelo_chat_interno.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
