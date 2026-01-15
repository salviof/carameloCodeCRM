/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.util;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;

/**
 *
 * @author SalvioF
 */
public abstract class UtilSBCrmEmail {

    public static String getUrlConfirmacaoEmail(EnvioEmail pEmail) {
        return SBWebPaginas.getSiteURL() + "/imagens/" + pEmail.getId() + "/.logomarca.png";
    }

}
