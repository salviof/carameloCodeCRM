package br.org.carameloCode.erp.modulo.crm.api.model.persona;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.api.model.persona.ValoresLogicosPersona;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = Persona.class)
public @interface ValorLogicoPersona {

	ValoresLogicosPersona calculo();
}