/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCodeSemLimparBanco;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class EscopoPesqHorarioPublicadoTest extends TesteCRMCarameloCodeSemLimparBanco {

    public EscopoPesqHorarioPublicadoTest() {
    }

    /**
     * Test of isPublicado method, of class EscopoPesqHorarioPublicado.
     */
    @Test
    public void testIsPublicado() {

        List<EscopoPesqHorarioPublicado> escoposPublicos = UtilSBPersistencia.getListaTodos(EscopoPesqHorarioPublicado.class, getEM());

        for (EscopoPesqHorarioPublicado escopo : escoposPublicos) {
            List<HorarioDisponivelAtendimentoPublico> horariosDisponiveis = (List) escopo.getCPinst(CPEscopoPesquisaMelhorHorario.listahorariosdisponiveis).getValor();
            for (HorarioDisponivelAtendimentoPublico horarioDisponivel : horariosDisponiveis) {
                System.out.println(horarioDisponivel.getUsuarioResponsavel());
            }
        }

    }

}
