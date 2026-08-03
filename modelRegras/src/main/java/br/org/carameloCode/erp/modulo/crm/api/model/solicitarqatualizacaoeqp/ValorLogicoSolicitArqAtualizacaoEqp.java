package br.org.carameloCode.erp.modulo.crm.api.model.solicitarqatualizacaoeqp;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitarqatualizacaoeqp.ValoresLogicosSolicitArqAtualizacaoEqp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitArqAtualizacaoEqp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = SolicitArqAtualizacaoEqp.class)
public @interface ValorLogicoSolicitArqAtualizacaoEqp {

	ValoresLogicosSolicitArqAtualizacaoEqp calculo();
}