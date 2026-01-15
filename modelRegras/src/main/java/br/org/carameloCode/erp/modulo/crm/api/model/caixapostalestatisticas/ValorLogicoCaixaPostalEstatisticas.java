package br.org.carameloCode.erp.modulo.crm.api.model.caixapostalestatisticas;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.api.model.caixapostalestatisticas.ValoresLogicosCaixaPostalEstatisticas;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal.CaixaPostalEstatisticas;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = CaixaPostalEstatisticas.class)
public @interface ValorLogicoCaixaPostalEstatisticas {

	ValoresLogicosCaixaPostalEstatisticas calculo();
}