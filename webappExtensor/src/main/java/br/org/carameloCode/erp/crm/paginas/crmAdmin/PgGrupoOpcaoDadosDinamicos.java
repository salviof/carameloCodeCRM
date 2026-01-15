/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.GrupoTipoDadoCrm;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;

import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "GPDDCRM", tags = {"Grupos de dados dinâmicos"})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.GRUPO_OPCAO_DADOS_CRM_MB_GERENCIAR)
public class PgGrupoOpcaoDadosDinamicos extends MB_paginaCadastroEntidades<GrupoTipoDadoCrm> {

    public PgGrupoOpcaoDadosDinamicos() {
        super(FabAcaoCrmAdmin.GRUPO_OPCAO_DADOS_CRM_MB_GERENCIAR.getRegistro().getComoGestaoEntidade());
    }

    @PostConstruct
    public void init() {
        xhtmlAcaoAtual = getAcaoSelecionada().getComoFormulario().getXhtml();
    }

}
