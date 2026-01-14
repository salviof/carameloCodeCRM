/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.calendario;

import java.sql.Time;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author sfurbino
 */
@FacesValidator("org.super_bits.view.validadores.horario")
public class ValidadorHorario implements Validator<Date> {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Date t) throws ValidatorException {
        System.out.println("TEste");
    }

}
