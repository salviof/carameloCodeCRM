package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;
import cucumber.api.java.pt.Entao;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import org.junit.Assert;

public class F_Entao__uma_sala_entre_o_atendente_e_o_cliente_e_criado {

    @Entao(EtapasAberturaDeChamadoSimples.ENTAO_UMA_SALA_ENTRE_O_ATENDENTE_E_O_CLIENTE_E_CRIADO)
    public void implementacaoEtapa() {
        FluxoChamadoSimples.atualizarEntidadesDeclaradas();
        String codigoSala = FluxoChamadoSimples.chamado.getSalaMatrix();
        Assert.assertNotNull("Código sala do chamado não definido", codigoSala);
        ComoChatSalaBean sala;

        try {
            sala = ERPChat.MATRIX_ORG.getImplementacaoDoContexto().getSalaByAlias(codigoSala);
            Assert.assertNotNull("Falha obtendo sala do chamado", sala);
        } catch (ErroConexaoServicoChat ex) {
            Assert.fail("Falha buscando sala matrix");
        }

    }
}
