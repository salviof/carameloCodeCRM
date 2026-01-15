package br.org.carameloCode.erp.modulo.crm.api.model.emailcrm;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.EmailCrm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValidadoresEmailCrm;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = EmailCrm.class)
public @interface ValidadorEmailCrm {

	ValidadoresEmailCrm validador();
}