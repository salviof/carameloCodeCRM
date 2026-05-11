package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.reservahorario;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.reservahorario.ValoresLogicosReservaHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = ReservaHorario.class)
public @interface ValorLogicoReservaHorario {

	ValoresLogicosReservaHorario calculo();
}