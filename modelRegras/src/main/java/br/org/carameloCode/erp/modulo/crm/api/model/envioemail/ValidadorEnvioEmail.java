package br.org.carameloCode.erp.modulo.crm.api.model.envioemail;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValidadoresEnvioEmail;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = EnvioEmail.class)
public @interface ValidadorEnvioEmail {

	ValidadoresEnvioEmail validador();
}