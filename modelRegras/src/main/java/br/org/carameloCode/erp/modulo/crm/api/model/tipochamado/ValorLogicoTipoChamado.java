package br.org.carameloCode.erp.modulo.crm.api.model.tipochamado;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.api.model.tipochamado.ValoresLogicosTipoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.TipoChamado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = TipoChamado.class)
public @interface ValorLogicoTipoChamado {

	ValoresLogicosTipoChamado calculo();
}