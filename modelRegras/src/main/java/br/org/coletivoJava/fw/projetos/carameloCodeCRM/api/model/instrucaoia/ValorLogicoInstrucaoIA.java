package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.instrucaoia;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.instrucaoia.ValoresLogicosInstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = InstrucaoIA.class)
public @interface ValorLogicoInstrucaoIA {

	ValoresLogicosInstrucaoIA calculo();
}