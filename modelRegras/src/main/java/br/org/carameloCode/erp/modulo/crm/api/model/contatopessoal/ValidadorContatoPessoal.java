package br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoal.ContatoPessoal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal.ValidadoresContatoPessoal;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = ContatoPessoal.class)
public @interface ValidadorContatoPessoal {

	ValidadoresContatoPessoal validador();
}