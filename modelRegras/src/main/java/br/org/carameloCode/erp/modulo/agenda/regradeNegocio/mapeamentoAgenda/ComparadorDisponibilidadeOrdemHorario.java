/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda;

import java.util.Comparator;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.DisponibilidadeAtdmtPublico;

/**
 *
 * @author sfurbino
 */
public class ComparadorDisponibilidadeOrdemHorario implements Comparator<DisponibilidadeAtdmtPublico> {

    @Override
    public int compare(DisponibilidadeAtdmtPublico o1, DisponibilidadeAtdmtPublico o2) {
        if (o1.getHorarioInicio().getTime() > o2.getHorarioInicio().getTime()) {
            return 1;
        }
        if (o1.getHorarioInicio().getTime() < o2.getHorarioInicio().getTime()) {
            return -1;
        }
        if (o1.getHorarioInicio().getTime() == o2.getHorarioInicio().getTime()) {

            if (o1.getHorarioFim().getTime() > o2.getHorarioFim().getTime()) {
                return 1;
            } else {
                if (o1.getHorarioFim().getTime() == o2.getHorarioFim().getTime()) {
                    return 0;
                }
                return -1;
            }

        }
        throw new UnsupportedOperationException("Impossível comparar as disponibilidades");
    }

}
/**
 *
 * @author sfurbino
 */
