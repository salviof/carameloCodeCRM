/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAgenda;

import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.FabAcaoAgendaMentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.InfoAcaoAgendamentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesqHorarioPublicado;

/**
 *
 * @author sfurbino
 */
@InfoAcaoAgendamentoPublico(acao = FabAcaoAgendaMentoPublico.ESCOPO_AGENDAMENTO_PUBLICO_MB)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "Escopos publicos", tags = {"CadecopoPublico"})
public class PgEscopoAgendaPublica extends MB_paginaCadastroEntidades<EscopoPesqHorarioPublicado> {

}
