package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.etapas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.FabTipoNotificacao;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.reservalinkpublico.EtapasReservaLinkPublico;
import cucumber.api.java.pt.Entao;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.FluxoAgendaPublicaReserva;
import org.junit.Assert;

public class F_Entao_a_pessoa_e_notificada_sobre_a_reserva_pelo_email {

    @Entao(EtapasReservaLinkPublico.E_A_PESSOA_E_NOTIFICADA_SOBRE_A_RESERVA_PELO_EMAIL)
    public void implementacaoEtapa() {

        UsuarioSB usuario = FluxoAgendaPublicaReserva.RESERVA.getAtendidoResponsavel();

        boolean ntfRegistrado = CarameloCode.getServicoComunicacao()
                .getArmazenamento()
                .isNotificacaoExiste(FabTipoNotificacao.NOTIFICAR_CLIENTE_AGENDA_REUNIAO.getRegistro(),
                        usuario, FluxoAgendaPublicaReserva.RESERVA);
        Assert.assertTrue("Notificação não foi encontrada", ntfRegistrado);
    }
}
