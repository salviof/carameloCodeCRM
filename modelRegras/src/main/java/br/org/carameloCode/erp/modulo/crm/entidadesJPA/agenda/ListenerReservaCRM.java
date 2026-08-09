package br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda;

import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda.UtilSBAgendaHorariosDisponiveis;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.entidadeEscuta.ComoListenerPersistenciaEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

/**
 *
 * @author salvio
 */
public class ListenerReservaCRM implements ComoListenerPersistenciaEntidade {

    @PostUpdate
    @Override
    public void acaoAposAtualizar(ComoEntidadeSimples emp) {

        UtilSBAgendaHorariosDisponiveis.atualizarReservas();
    }

    @PostPersist
    @Override
    public void acaoAposPersistir(ComoEntidadeSimples emp) {

        UtilSBAgendaHorariosDisponiveis.atualizarReservas();
    }

    @Override
    public void acaoAntesDeAtualizar(ComoEntidadeSimples pEntidade) {

    }

    @Override
    public void acaoAntesDePersistir(ComoEntidadeSimples pEntidade) {

    }

    @Override
    public void acaoAposRemover(ComoEntidadeSimples pEntidade) {

    }

    @Override
    public void acaoAntesRemover(ComoEntidadeSimples pEntidade) {

    }

}
