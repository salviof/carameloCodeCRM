/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.FabContextoDeReserva;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.TipoReservaCRMLocal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.TipoReservaCRMRemoto;

/**
 *
 * @author sfurbino
 */
public enum FabTipoAgendamentoPublicoCrm implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = TipoReservaCRMLocal.class, id = 1, nomeObjeto = "Primeira consultoria presencial")
    PRESENCIAL,
    @InfoObjetoDaFabrica(classeObjeto = TipoReservaCRMLocal.class, id = 2, nomeObjeto = "Consultoria avançada presencial")
    PRESENCIAL_COM_PALESTRA,
    @InfoObjetoDaFabrica(classeObjeto = TipoReservaCRMRemoto.class, id = 3, nomeObjeto = "Consultoria por video conferência")
    CONFERENCIA,
    @InfoObjetoDaFabrica(classeObjeto = TipoReservaCRMRemoto.class, id = 4, nomeObjeto = "Consultoria avançada por video Conferência")
    CONFERENCIA_COM_PALESTRA;

    @Override
    public TipoAgendamentoAtdmPublico getRegistro() {
        TipoAgendamentoAtdmPublico tipo = (TipoAgendamentoAtdmPublico) ComoFabricaComPersistencia.super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
        switch (this) {
            case PRESENCIAL:
                tipo.setDuracaoAtendenteMinutos(180);
                tipo.setMinutosAnteriorAReserva(15);
                tipo.setContextoReserva(FabContextoDeReserva.RESERVAR_AGENDA_ATENDENTE.getRegistro());
                break;
            case PRESENCIAL_COM_PALESTRA:
                tipo.setDuracaoAtendenteMinutos(280);
                tipo.setMinutosAnteriorAReserva(15);
                tipo.setContextoReserva(FabContextoDeReserva.RESERVAR_AGENDA_ATENDENTE.getRegistro());
                break;
            case CONFERENCIA:
                tipo.setDuracaoAtendenteMinutos(80);
                tipo.setMinutosAnteriorAReserva(15);
                tipo.setContextoReserva(FabContextoDeReserva.RESERVAR_AGENDA_ATENDENTE.getRegistro());
                break;
            case CONFERENCIA_COM_PALESTRA:

                tipo.setDuracaoAtendenteMinutos(180);
                tipo.setMinutosAnteriorAReserva(15);
                tipo.setContextoReserva(FabContextoDeReserva.RESERVAR_AGENDA_ATENDENTE.getRegistro());
                break;
            default:
                throw new AssertionError(this.name());

        }
        return tipo;
    }

}
