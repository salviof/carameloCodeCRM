/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.coletivoJava.fw.api.erp.erpintegracao.contextos.ERPIntegracaoSistemasApi;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfSistemaERP;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author sfurbino
 */
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.INTEGRACAO_ERP_MB_GESTAO)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "intERP", tags = "Integração ERP")
public class PgIntegracaoERP extends MB_paginaCadastroEntidades<SistemaERPConfiavel> {

    private ItfSistemaERP sistemaAtual;

    @PostConstruct
    public void inicio() {
        if (SBCore.getServicoSessao().getSessaoAtual().isIdentificado())
            try {
            sistemaAtual = ERPIntegracaoSistemasApi.RESTFUL.getImplementacaoDoContexto().getSistemaAtual();
        } catch (Throwable t) {

        }
    }

    public ItfSistemaERP getSistema() {
        return sistemaAtual;
    }
}
