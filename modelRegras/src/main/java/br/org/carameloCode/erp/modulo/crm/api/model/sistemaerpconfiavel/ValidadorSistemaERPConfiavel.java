package br.org.carameloCode.erp.modulo.crm.api.model.sistemaerpconfiavel;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.sistemaerpconfiavel.ValidadoresSistemaERPConfiavel;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = SistemaERPConfiavel.class)
public @interface ValidadorSistemaERPConfiavel {

	ValidadoresSistemaERPConfiavel validador();
}