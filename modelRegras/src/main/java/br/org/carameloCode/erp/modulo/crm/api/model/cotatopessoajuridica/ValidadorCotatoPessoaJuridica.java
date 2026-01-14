package br.org.carameloCode.erp.modulo.crm.api.model.cotatopessoajuridica;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoPessoaJuridica.CotatoPessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.cotatopessoajuridica.ValidadoresCotatoPessoaJuridica;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = CotatoPessoaJuridica.class)
public @interface ValidadorCotatoPessoaJuridica {

	ValidadoresCotatoPessoaJuridica validador();
}