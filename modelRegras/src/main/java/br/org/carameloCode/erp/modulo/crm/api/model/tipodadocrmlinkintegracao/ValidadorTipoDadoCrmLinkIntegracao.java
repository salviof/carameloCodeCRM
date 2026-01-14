package br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao.ValidadoresTipoDadoCrmLinkIntegracao;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = TipoDadoCrmLinkIntegracao.class)
public @interface ValidadorTipoDadoCrmLinkIntegracao {

	ValidadoresTipoDadoCrmLinkIntegracao validador();
}