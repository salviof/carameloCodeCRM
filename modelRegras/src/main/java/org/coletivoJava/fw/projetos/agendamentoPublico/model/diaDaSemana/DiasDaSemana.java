/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.diaDaSemana;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.io.Serializable;
import java.time.DayOfWeek;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
public class DiasDaSemana implements Serializable {

    private final String diaDaSemana;

    /**
     *
     * Sequencia de 0 e 1, indicando quais dias da semana estão selecionados
     * (1), e quais não estão (0)
     *
     * A ordem dos valores é: (segunda,terça,quarta,quinta,sexta,sábado,domingo)
     *
     * Um exemplo de sequencia selecionando todos os dias da semana exceto
     * domingo (1111110)
     *
     * Porque começamos de segunda e não no domingo? Para melhor compatiblidade
     * com DayOfWeek do Java 8, que segue o padrão ISO-8601
     *
     * @param pDiaDaSemana Exampo: 1100000 -> Seleção de Segunda, e Terça os
     * outros dias de semana não
     */
    public DiasDaSemana(String pDiaDaSemana) {
        this.diaDaSemana = pDiaDaSemana;
    }

    /**
     *
     * @param pDia Código ISO-8601 representando um dia da semana (segunda é 1,
     * domingo é 7) , conforme o enum DayOfWeek do java8
     * @return
     */
    public boolean isDiaDaSemanaSelecionada(int pDia) {
        return diaDaSemana.charAt(pDia - 1) == '1';
    }

    public boolean isDiaDaSemanaSelecionado(DayOfWeek pDia) {
        try {
            char registroDiaDaSemana = diaDaSemana.charAt(pDia.getValue() - 1);
            boolean diaAtivo = registroDiaDaSemana == '1';
            return diaAtivo;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha definindo dia da semana selecionado", t);
            throw new UnsupportedOperationException("Falha definindo dia da semana selecionado" + pDia + "-" + diaDaSemana);
        }
    }

    public boolean isSegundaSelecionada() {
        return diaDaSemana.charAt(0) == '1';
    }

    public boolean isTeracaSelecionada() {
        return diaDaSemana.charAt(1) == '1';
    }

    public boolean isQuartaSelecionada() {
        return diaDaSemana.charAt(2) == '1';
    }

    public boolean isQuintaSelecionada() {
        return diaDaSemana.charAt(3) == '1';
    }

    public boolean isSextaSelecionada() {
        return diaDaSemana.charAt(4) == '1';
    }

    public boolean isSabadoSelecionada() {
        return diaDaSemana.charAt(5) == '1';
    }

    public boolean isDomingoSelecionada() {
        return diaDaSemana.charAt(6) == '1';
    }

}
