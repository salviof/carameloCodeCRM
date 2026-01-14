/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHoraCalendarioLocal;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author sfurbino
 */
public class IntervaloHorariosDoDia {

    private LocalTime horarioInicial;
    private LocalTime horarioFinal;

    public LocalTime getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(LocalTime pHorarioInicial) {
        this.horarioInicial = pHorarioInicial;
    }

    public void setHorarioIniicial(Date pHorarioInicial) {
        setHorarioInicial(UtilCRCDataHoraCalendarioLocal.gerarLocalTimeByDate(pHorarioInicial));
    }

    public LocalTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public void setHorarioFinal(Date pHorarioFinal) {
        setHorarioFinal(UtilCRCDataHoraCalendarioLocal.gerarLocalTimeByDate(pHorarioFinal));
    }

}
