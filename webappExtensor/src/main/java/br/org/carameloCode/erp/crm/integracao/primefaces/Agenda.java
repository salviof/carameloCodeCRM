/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.integracao.primefaces;

import java.util.List;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author desenvolvedor
 */
public class Agenda implements ScheduleModel {

    private List<ScheduleEvent<?>> eventos;

    @Override
    public void addEvent(ScheduleEvent event) {
        System.out.println("");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteEvent(ScheduleEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ScheduleEvent<?>> getEvents() {
        if (eventos != null) {
            return eventos;
        }

        eventos = ListasAgenda.EVENTOS.getLista(this);

        return eventos;
    }

    @Override
    public ScheduleEvent getEvent(String id) {
        if (eventos == null) {
            return null;
        }
        return eventos.stream().filter(evento -> evento.getId().equals(id)).findFirst().get();
    }

    @Override
    public void updateEvent(ScheduleEvent event) {
        System.out.println("Nao Implementado");
    }

    @Override
    public int getEventCount() {
        if (eventos == null) {
            return 0;
        }
        return eventos.size();
    }

    @Override
    public void clear() {
        if (eventos != null) {
            eventos.clear();
        }

    }

    @Override
    public boolean isEventLimit() {
        return true;
    }

}
