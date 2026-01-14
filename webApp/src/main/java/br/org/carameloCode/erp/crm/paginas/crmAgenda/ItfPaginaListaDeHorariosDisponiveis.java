/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAgenda;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;

/**
 *
 * @author sfurbino
 */
public interface ItfPaginaListaDeHorariosDisponiveis {

    public HorarioDisponivelAtendimentoPublico getHorarioDisponivelSelecionado();

    public void setHorarioDisponivelSelecionado(HorarioDisponivelAtendimentoPublico pHorarioDisponivel);

    public ItfAcaoFormulario getAcaoFormularioNovaReservaConferenciaRemota();

    public ItfAcaoFormulario getAcaoFormularioNovaReservaVisitaLocal();
}
