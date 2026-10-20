/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.ia.acoes.FabAcaoIAExecucao;
import br.org.carameloCode.erp.modulo.ia.acoes.InfoAcaoIAExecucao;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "gdpia", tags = {"gestao de personas ia"})
@InfoAcaoIAExecucao(acao = FabAcaoIAExecucao.PERSONA_IA_MB_GESTAO)
public class PgPersonaIA extends MB_paginaCadastroEntidades<Persona> {

}
