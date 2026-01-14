/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Conferência programada", plural = "Conferências Programadas")
public class ReservaHoraRemotoVideo extends ReservaHorario {

    @InfoCampoValorLogico(nomeCalculo = "linkConferência")
    private String linkConferencia;

    @InfoCampoValorLogico(nomeCalculo = "linkConferenciaDefinido")
    private boolean linkConferenciaFoiDefinido;

    public String getLinkConferencia() {
        return linkConferencia;
    }

    public void setLinkConferencia(String linkConferencia) {
        this.linkConferencia = linkConferencia;
    }

    public boolean isLinkConferenciaFoiDefinido() {
        return linkConferenciaFoiDefinido;
    }

    public void setLinkConferenciaFoiDefinido(boolean linkConferenciaFoiDefinido) {
        this.linkConferenciaFoiDefinido = linkConferenciaFoiDefinido;
    }

}
