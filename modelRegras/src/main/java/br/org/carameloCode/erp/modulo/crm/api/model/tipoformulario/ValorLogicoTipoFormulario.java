package br.org.carameloCode.erp.modulo.crm.api.model.tipoformulario;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoformulario.ValoresLogicosTipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.formulario.TipoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = TipoFormulario.class)
public @interface ValorLogicoTipoFormulario {

	ValoresLogicosTipoFormulario calculo();
}