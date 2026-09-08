package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.etapas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.FabTipoNotificacao;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.reservalinkpublico.EtapasReservaLinkPublico;
import cucumber.api.java.pt.Entao;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.FluxoAgendaPublicaReserva;
import org.junit.Assert;

public class G_Entao_o_atendente_Salvio_Furbino_e_notificado_pelo_chat_interno {

    @Entao(EtapasReservaLinkPublico.E_O_ATENDENTE_SALVIO_FURBINO_E_NOTIFICADO_PELO_CHAT_INTERNO)
    public void implementacaoEtapa() {
        UsuarioSB usuario = (UsuarioSB) CarameloCode.getServicoPermissao().getUsuarioByEmail(FluxoAgendaPublicaReserva.EMAIL_ATENDENTE);

        boolean ntfRegistrado = CarameloCode.getServicoComunicacao()
                .getArmazenamento()
                .isNotificacaoExiste(FabTipoNotificacao.NOTIFICAR_ATENDENTE_CLIENTE_MARCOU_NA_AGENDA.getRegistro(),
                        usuario, FluxoAgendaPublicaReserva.RESERVA);
        Assert.assertTrue("Notificação não foi encontrada", ntfRegistrado);
    }
}
