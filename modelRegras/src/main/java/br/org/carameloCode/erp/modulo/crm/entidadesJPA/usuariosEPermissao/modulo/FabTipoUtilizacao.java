/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo;

/**
 *
 * @author sfurbino
 */
public enum FabTipoUtilizacao {

    WEBMAIL_B2B,
    WEBMAIL_PESSOA_FISICA,
    CRM_B2B,
    CRM_PESSOA_FISICA;

    public static FabTipoUtilizacao getTipoAtualizacaoByString(String pParametro) {
        if (true) {
            return CRM_B2B;
        }
        if (pParametro == null || pParametro.isEmpty()) {
            return WEBMAIL_B2B;
        }
        String parametro = pParametro.toUpperCase();
        if (parametro.contains("WEBMAIL")) {
            if (parametro.contains("FISICA")) {
                return WEBMAIL_PESSOA_FISICA;
            }
            return WEBMAIL_B2B;
        }
        if (parametro.contains("CRM")) {
            if (parametro.contains("FISICA")) {
                return WEBMAIL_PESSOA_FISICA;
            }
            return CRM_B2B;
        }
        return WEBMAIL_B2B;

    }

}
