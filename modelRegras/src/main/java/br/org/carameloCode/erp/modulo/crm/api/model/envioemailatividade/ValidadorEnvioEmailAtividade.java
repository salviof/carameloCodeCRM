package br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValidadoresEnvioEmailAtividade;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = EnvioEmailAtividade.class)
public @interface ValidadorEnvioEmailAtividade {

	ValidadoresEnvioEmailAtividade validador();
}