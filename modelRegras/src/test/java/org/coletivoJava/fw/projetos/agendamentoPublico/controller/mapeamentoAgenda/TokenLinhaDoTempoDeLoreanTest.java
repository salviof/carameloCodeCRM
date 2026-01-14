/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda;

import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.ErroAtingiuFinalLinhaDoTempoPermita;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class TokenLinhaDoTempoDeLoreanTest {

    /**
     * Test of saltarProximoDiaDisponivel method, of class
     * TokenLinhaDoTempoDeLorean.
     */
    @Test
    public void testSaltarProximoDiaDisponivel() throws Exception {
        EscopoPesquisaMelhorHorario escopo = new EscopoPesquisaMelhorHorario();

        TokenLinhaDoTempoDeLorean meuDeLorean = new TokenLinhaDoTempoDeLorean(escopo);
        try {
            meuDeLorean.saltarProximoHorarioDisponivel();
        } catch (ErroAtingiuFinalLinhaDoTempoPermita p) {

        }
    }

}
