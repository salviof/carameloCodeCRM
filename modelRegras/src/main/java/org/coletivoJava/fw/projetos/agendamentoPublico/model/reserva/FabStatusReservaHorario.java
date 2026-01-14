/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaStatusComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public enum FabStatusReservaHorario implements ComoFabricaStatusComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = StatusReserva.class, nomeObjeto = "Agendado", id = 1)
    AGENDADO,
    @InfoObjetoDaFabrica(classeObjeto = StatusReserva.class, nomeObjeto = "Confirmado", id = 2)
    CONFIRMADO,
    @InfoObjetoDaFabrica(classeObjeto = StatusReserva.class, nomeObjeto = "Realizado", id = 3)
    REALIZADO;

    @Override
    public StatusReserva getRegistro() {
        return (StatusReserva) ComoFabricaStatusComPersistencia.super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComoAcaoDoSistema> opcoesPorStatus() {
        return new ArrayList<>();
    }

    public boolean isAtiva() {
        switch (this) {
            case AGENDADO:
                return true;

            case CONFIRMADO:
                return true;

            case REALIZADO:
                return false;

            default:
                throw new AssertionError(this.name());

        }
    }
}
