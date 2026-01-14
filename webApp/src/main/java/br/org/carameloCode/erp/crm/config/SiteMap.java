/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.modulo.crm.menu.FabMenuIntranetCasaNova;

import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfSiteMapa;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MB_SiteMapa;
import javax.enterprise.context.ApplicationScoped;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoFabricaMenu;
import javax.enterprise.inject.Specializes;

/**
 *
 *
 * Todas as paginas do seu sistema precisam ser Injetadas no SiteMAP
 *
 * OFabMenuIntranett
 * com.super_bitscrmAdmin.<error>.Intranet_Marketing_Digital.paginas.PgServicoDisponivel;
 * juda o bean paginaAtual a identificar qual pagina está sendo referenciada, e
 * serve de parametro para montar o site Map da pagina
 *
 *
 * @author Salvio
 */
@ApplicationScoped
public class SiteMap extends MB_SiteMapa implements ItfSiteMapa {

    public SiteMap() {
        super();
    }

    @Override
    public Class<? extends ComoFabricaMenu> getFabricaMenu() {
        return FabMenuIntranetCasaNova.class;
    }

}
