/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author SalvioF
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CalculoProspectoEmpresa {

    public FabCalculoProspectoEmpresa calculo();
}
