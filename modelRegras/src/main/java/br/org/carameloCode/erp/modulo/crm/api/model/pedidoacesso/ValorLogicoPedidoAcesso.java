package br.org.carameloCode.erp.modulo.crm.api.model.pedidoacesso;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.api.model.pedidoacesso.ValoresLogicosPedidoAcesso;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.autorizacao.PedidoAcesso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = PedidoAcesso.class)
public @interface ValorLogicoPedidoAcesso {

	ValoresLogicosPedidoAcesso calculo();
}