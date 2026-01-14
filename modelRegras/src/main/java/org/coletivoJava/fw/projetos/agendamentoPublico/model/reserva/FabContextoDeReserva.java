/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author sfurbino
 */
public enum FabContextoDeReserva implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = ContextoReserva.class, id = 1, nomeObjeto = "Reserva Local")
    RESERVAR_LOCAL,
    @InfoObjetoDaFabrica(classeObjeto = ContextoReserva.class, id = 2, nomeObjeto = "Reserva Atendente")
    RESERVAR_AGENDA_ATENDENTE;

    @Override
    public ContextoReserva getRegistro() {
        return (ContextoReserva) ComoFabricaComPersistencia.super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
    }

}
