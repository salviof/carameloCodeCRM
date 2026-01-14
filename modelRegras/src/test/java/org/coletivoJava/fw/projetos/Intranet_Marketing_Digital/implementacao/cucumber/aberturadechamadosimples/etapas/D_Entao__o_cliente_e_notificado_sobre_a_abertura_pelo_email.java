package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.coletivojava.erp.notificacao.padrao.model.notificacao.NotificacaoSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import cucumber.api.java.pt.Entao;
import java.util.List;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;

public class D_Entao__o_cliente_e_notificado_sobre_a_abertura_pelo_email {

    @Entao(EtapasAberturaDeChamadoSimples.E_O_CLIENTE_E_NOTIFICADO_SOBRE_A_ABERTURA_PELO_EMAIL)
    public void implementacaoEtapa() {
        List<NotificacaoSB> notificacoes = UtilSBPersistencia.getListaTodos(NotificacaoSB.class, FluxoChamadoSimples.getEM());
        for (NotificacaoSB ntf : notificacoes) {
            System.out.println(ntf);
        }
    }
}
