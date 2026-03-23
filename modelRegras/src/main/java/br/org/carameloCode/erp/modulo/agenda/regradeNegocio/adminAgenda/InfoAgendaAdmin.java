/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author salvio
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoAgendaAdmin {

    public FabAcaoAdminAgenda acao();
}
