/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author SalvioF
 */
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.META_RELACIONAMENTO_MB)
@InfoPagina(nomeCurto = "METASR", tags = "Metas de Relacionamento")
@Named
@ViewScoped
public class PgMetaRelacionamento extends MB_paginaCadastroEntidades<MetaRelacionamento> {

    public PgMetaRelacionamento() {
        super(FabAcaoCrmAdmin.META_RELACIONAMENTO_MB.getRegistro().getAcaoDeGestaoEntidade());
        removerAcaoDeEntidade(FabAcaoCrmAdmin.META_RELACIONAMENTO_CTR_CONVERTER.getRegistro());
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.META_RELACIONAMENTO_FRM_CONVERTER.getRegistro());
    }

}
