/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.controller;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.FabUsuarioPadraoMarketingParaWeb;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import java.sql.Time;
import java.util.List;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda.MapaHorariosDisponiveis;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda.TokenLinhaDoTempoDeLorean;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.FabTipoAgendamentoPublicoCrm;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class MapaHorariosDisponiveisTest extends TesteCRMCarameloCode {

    public MapaHorariosDisponiveisTest() {
    }

    /**
     * Test of adicionarDisponibilidade method, of class
     * MapaHorariosDisponiveis.
     */
    /**
     * Test of getPrimeiroHorarioDisponivel method, of class
     * MapaHorariosDisponiveis.
     */
    @Test
    public void testGetPrimeiroHorarioDisponivel() throws Exception {
        adicionarDisponibilidades();
        adicionarDisponibilidades();
        EscopoPesquisaMelhorHorario escopoPesquisa = new EscopoPesquisaMelhorHorario();
        escopoPesquisa.adicionarAtendente(FabUsuarioPadraoMarketingParaWeb.ATENDIMENTO.getRegistro());
        escopoPesquisa.setTipoAgendamento(FabTipoAgendamentoPublicoCrm.CONFERENCIA.getRegistro());
        TokenLinhaDoTempoDeLorean meudelorean = new TokenLinhaDoTempoDeLorean(escopoPesquisa);
        List<HorarioDisponivelAtendimentoPublico> listaMelhoresHorarios = MapaHorariosDisponiveis.getHorarioDisponivelPrimeiras9pcoes(escopoPesquisa);
        System.out.println("Encontrados " + listaMelhoresHorarios.size() + "Horários");
        for (HorarioDisponivelAtendimentoPublico horario : listaMelhoresHorarios) {
            System.out.println("____________________________________________");
            System.out.println(UtilCRCDataHora.getDiaDaSemana(horario.getDataHoraIicialAtendente()));
            System.out.println(horario.toString());
        }
        escopoPesquisa.setTipoAgendamento(FabTipoAgendamentoPublicoCrm.PRESENCIAL.getRegistro());
        listaMelhoresHorarios = MapaHorariosDisponiveis.getHorarioDisponivelPrimeiras9pcoes(escopoPesquisa);
        escopoPesquisa.getListaHorariosDisponiveis();
        for (HorarioDisponivelAtendimentoPublico horario : listaMelhoresHorarios) {
            System.out.println("____________________________________________");
            System.out.println(UtilCRCDataHora.getDiaDaSemana(horario.getDataHoraIicialAtendente()));
            System.out.println(horario.toString());
        }
        listaMelhoresHorarios = MapaHorariosDisponiveis.getHorarioDisponivelPrimeiras9pcoes(escopoPesquisa);
        for (HorarioDisponivelAtendimentoPublico horario : listaMelhoresHorarios) {
            System.out.println("____________________________________________");
            System.out.println(UtilCRCDataHora.getDiaDaSemana(horario.getDataHoraIicialAtendente()));
            System.out.println(horario.toString());
        }

    }

    public void adicionarDisponibilidades() {
        DisponibilidadeAtdmtPublico disponibilidadeAtendimentoHomeOffice = new DisponibilidadeAtdmtPublico();
        disponibilidadeAtendimentoHomeOffice.adicionarTipoAgendamentoDisponivel(FabTipoAgendamentoPublicoCrm.CONFERENCIA.getRegistro());
        disponibilidadeAtendimentoHomeOffice.adicionarAtendenteDisponivel(FabUsuarioPadraoMarketingParaWeb.ATENDIMENTO.getRegistro());
        disponibilidadeAtendimentoHomeOffice.setDiaInicial(UtilCRCDataHora.converteString_dd_MM_yyEmData("14/01/2025"));
        disponibilidadeAtendimentoHomeOffice.setDiaFinal(UtilCRCDataHora.converteString_dd_MM_yyEmData("18/01/2025"));
        disponibilidadeAtendimentoHomeOffice.setHorarioInicio(new Time(UtilCRCDataHora.converteString_HH_doisPontos_mm_EmData("08:30").getTime()));
        disponibilidadeAtendimentoHomeOffice.setHorarioFim(new Time(UtilCRCDataHora.converteString_HH_doisPontos_mm_EmData("12:30").getTime()));

        DisponibilidadeAtdmtPublico disponibilidadeAtendimentoVisitas = new DisponibilidadeAtdmtPublico();
        disponibilidadeAtendimentoVisitas.adicionarAtendenteDisponivel(FabUsuarioPadraoMarketingParaWeb.ATENDIMENTO.getRegistro());
        disponibilidadeAtendimentoVisitas.adicionarTipoAgendamentoDisponivel(FabTipoAgendamentoPublicoCrm.PRESENCIAL.getRegistro());
        disponibilidadeAtendimentoVisitas.setDiaInicial(UtilCRCDataHora.converteString_dd_MM_yyEmData("15/01/2025"));
        disponibilidadeAtendimentoVisitas.setDiaFinal(UtilCRCDataHora.converteString_dd_MM_yyEmData("18/01/2025"));
        disponibilidadeAtendimentoVisitas.setHorarioInicio(new Time(UtilCRCDataHora.converteString_HH_doisPontos_mm_EmData("08:30").getTime()));
        disponibilidadeAtendimentoVisitas.setHorarioFim(new Time(UtilCRCDataHora.converteString_HH_doisPontos_mm_EmData("12:30").getTime()));
        System.out.println(disponibilidadeAtendimentoHomeOffice.toString());
        System.out.println(disponibilidadeAtendimentoVisitas.toString());
        MapaHorariosDisponiveis.adicionarDisponibilidade(disponibilidadeAtendimentoHomeOffice);
        MapaHorariosDisponiveis.adicionarDisponibilidade(disponibilidadeAtendimentoVisitas);
    }

}
