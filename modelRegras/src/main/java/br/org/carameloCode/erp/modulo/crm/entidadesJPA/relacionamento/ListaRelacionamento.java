/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author desenvolvedor
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ListaRelacionamento {

    ListasRelacionamento lista();

}
