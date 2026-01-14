
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
@InfoPagina(nomeCurto = "PROSPAdmin", tags = {"AdminProsp"}, acessoLivre = false)
@ViewScoped
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.PROSPECTO_ADMIN_MB_GERENCIAR)
@Named
public class PgAdminProspecto extends MB_paginaCadastroEntidades<PessoaJuridica> {

    public void inicio() {
        if (!SBCore.getUsuarioLogado().getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
            xhtmlAcaoAtual = FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM.getRegistro().getComoFormulario().getXhtml();
        }
    }

    @Override
    public ComoAcaoDoSistema getAcaoSalvarAlteracoes() {
        return FabAcaoCrmAdmin.PROSPECTO_ADMIN_CTR_SALVAR_MERGE.getRegistro();
    }

}
