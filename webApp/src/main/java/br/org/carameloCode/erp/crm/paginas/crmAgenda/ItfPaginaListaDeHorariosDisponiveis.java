/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAgenda;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.HorarioDisponivelAtendimentoPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;

/**
 *
 * @author sfurbino
 */
public interface ItfPaginaListaDeHorariosDisponiveis {

    public HorarioDisponivelAtendimentoPublico getHorarioDisponivelSelecionado();

    public EscopoPesquisaMelhorHorario getEscopoPesquisa();

    public void setHorarioDisponivelSelecionado(HorarioDisponivelAtendimentoPublico pHorarioDisponivel);

    public ItfAcaoFormulario getAcaoFormularioNovaReservaConferenciaRemota();

    public ItfAcaoFormulario getAcaoFormularioNovaReservaVisitaLocal();
}
