/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmReservas;

import br.org.carameloCode.erp.crm.paginas.ComoPaginaAtualCRM;
import br.org.carameloCode.erp.crm.paginas.crmAgenda.ItfPaginaListaDeHorariosDisponiveis;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

/**
 *
 * @author sfurbino
 */
@Named
@ViewScoped
public class ServicoCrmReserva implements Serializable {

    @Inject
    private ItfPaginaAtual paginaAtual;

    public void reservarHorario(HorarioDisponivelAtendimentoPublico pHorarioDisponivel) {

        if (!(paginaAtual.getInfoPagina() instanceof ItfPaginaListaDeHorariosDisponiveis)) {
            throw new UnsupportedOperationException("A página de reserva de horário: " + paginaAtual.getInfoPagina().getClass().getSimpleName() + " precisa implmentar  "
                    + ItfPaginaListaDeHorariosDisponiveis.class.getSimpleName());
        }
        paginaAtual.getInfoPagina().getComoPaginaEntidade().setEntidadeSelecionada(null);
        ((ComoPaginaAtualCRM) paginaAtual).getComoPaginaComHorarioDisponivel().setHorarioDisponivelSelecionado(pHorarioDisponivel);
        if (paginaAtual.getInfoPagina().getComoPaginaEntidade().getEntidadeSelecionada() == null
                || !(paginaAtual.getInfoPagina().getComoPaginaEntidade().getEntidadeSelecionada() instanceof ReservaHorario)) {
            throw new UnsupportedOperationException("A página de reserva de horário: " + paginaAtual.getInfoPagina().getClass().getSimpleName() + " precisa gerenciar  "
                    + ReservaHorario.class.getSimpleName() + " como entidade principal  ");
        } else {
            if (pHorarioDisponivel.getTipoAgendamento().isUmAtendimentoRemoto()) {
                paginaAtual.getInfoPagina().getComoPaginaEntidade().setAcaoSelecionada(((ComoPaginaAtualCRM) paginaAtual).getComoPaginaComHorarioDisponivel().getAcaoFormularioNovaReservaConferenciaRemota());

            } else {
                paginaAtual.getInfoPagina().getComoPaginaEntidade().setAcaoSelecionada(((ComoPaginaAtualCRM) paginaAtual).getComoPaginaComHorarioDisponivel().getAcaoFormularioNovaReservaVisitaLocal());
            }

            paginaAtual.getInfoPagina().getComoPaginaDeGestao().executarAcaoSelecionada();
        }
    }

}
