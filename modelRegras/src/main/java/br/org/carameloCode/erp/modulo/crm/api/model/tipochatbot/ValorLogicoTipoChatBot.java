package br.org.carameloCode.erp.modulo.crm.api.model.tipochatbot;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chatBot.TipoChatBot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = TipoChatBot.class)
public @interface ValorLogicoTipoChatBot {

	ValoresLogicosTipoChatBot calculo();
}