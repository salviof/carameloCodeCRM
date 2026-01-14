/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author desenvolvedor
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CalculoTipoRelacionamento {

    CalculosTipoRelacionamento calculo();

}
