/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ComoReservaVideoConferencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;

import javax.persistence.Entity;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Conferência programada", plural = "Conferências Programadas")
public class ReservaHoraRemotoVideo extends ReservaHorarioCRM implements ComoReservaVideoConferencia {

    @InfoCampoValorLogico(nomeCalculo = "linkConferência")
    private String linkConferencia;

    @InfoCampoValorLogico(nomeCalculo = "linkConferenciaDefinido")
    private boolean linkConferenciaFoiDefinido;

    @Override
    public String getLinkConferencia() {

        return linkConferencia;
    }

    public void setLinkConferencia(String linkConferencia) {
        this.linkConferencia = linkConferencia;
    }

    @Override
    public boolean isLinkConferenciaFoiDefinido() {
        return linkConferenciaFoiDefinido;
    }

    public void setLinkConferenciaFoiDefinido(boolean linkConferenciaFoiDefinido) {
        this.linkConferenciaFoiDefinido = linkConferenciaFoiDefinido;
    }

}
