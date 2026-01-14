/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.FabConfigModuloIntranet;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.acessoAnonimo.FabAcaoAcessoAnonimoIntranet;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.ConfiguradorWPPadraoArquivoConfiguracao;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Salvio
 */
public class ConfigWP_CRM_CarameloCode extends ConfiguradorWPPadraoArquivoConfiguracao {

    public ConfigWP_CRM_CarameloCode() throws IOException {
        super();
    }

    private static List<String> hostAutorizados;

    @Override
    public List<String> getSitesHostsAutorizados() {
        if (hostAutorizados == null) {
            hostAutorizados = new ArrayList<>();
            hostAutorizados.add(SBCore.getConfigModulo(FabConfigModuloIntranet.class).getPropriedade(FabConfigModuloIntranet.UTRL_PAGINA));
            hostAutorizados.add(SBCore.getConfigModulo(FabConfigModuloIntranet.class).getPropriedade(FabConfigModuloIntranet.URL_ATENDIMENTO));
        }
        return hostAutorizados;
    }

    @Override
    public Class mapaSite() {
        return SiteMap.class;
    }

    @Override
    public List<ParametroURL> parametrosDeAplicacao() {
        return new ArrayList<>();
    }

    @Override
    public ItfAcaoFormulario getAcaoPaginaInicial() {

        return FabAcaoAcessoAnonimoIntranet.LOGIN_MB_GERENCIAR.getRegistro().getComoFormularioEntidade();

    }

    @Override
    public String getPacotePaginas() {
        return "br.org.carameloCode.erp.crm.paginas";
    }

}
