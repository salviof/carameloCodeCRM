package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.etapas;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.FabStatusReservaHorario;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.reservalinkpublico.EtapasReservaLinkPublico;
import cucumber.api.java.pt.Entao;
import java.lang.UnsupportedOperationException;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.FluxoAgendaPublicaReserva;
import org.junit.Assert;

public class D_Entao_uma_nova_reserva_e_criada_com_status_ativo {

    @Entao(EtapasReservaLinkPublico.ENTAO_UMA_NOVA_RESERVA_E_CRIADA_COM_STATUS_ATIVO)
    public void implementacaoEtapa() {
        FluxoAgendaPublicaReserva.atualizarEntidadesDeclaradas();
        Assert.assertEquals("Falha reservando", FluxoAgendaPublicaReserva.RESERVA.getStatus(), FabStatusReservaHorario.AGENDADO.getRegistro());
    }
}
