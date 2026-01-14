/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.FabConfigModuloIntranet;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabTipoUtilizacao;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabTipoUtilizacao.CRM_B2B;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabTipoUtilizacao.CRM_PESSOA_FISICA;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabTipoUtilizacao.WEBMAIL_B2B;
import static br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabTipoUtilizacao.WEBMAIL_PESSOA_FISICA;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author sfurbino
 */
@ApplicationScoped
@Named
public class InfoWebAppCrmColetivoJava {

    private FabTipoUtilizacao tipoUtilizacao
            = FabTipoUtilizacao.getTipoAtualizacaoByString(
                    SBCore.getConfigModulo(FabConfigModuloIntranet.class).getPropriedade(FabConfigModuloIntranet.TIPO_APRESENTACAO));

    private boolean apenasWebMail;

    @PostConstruct
    public void inicio() {
        switch (tipoUtilizacao) {
            case WEBMAIL_B2B:

            case WEBMAIL_PESSOA_FISICA:
                apenasWebMail = true;
                break;
            case CRM_B2B:

            case CRM_PESSOA_FISICA:
                apenasWebMail = false;
                break;
            default:
                throw new AssertionError(tipoUtilizacao.name());

        }
        apenasWebMail = false;
    }

    public boolean isApenasWebMail() {
        return apenasWebMail;
    }

}
