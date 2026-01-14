package br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraPresencial;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValidadoresReservaHoraPresencial;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = ReservaHoraPresencial.class)
public @interface ValidadorReservaHoraPresencial {

	ValidadoresReservaHoraPresencial validador();
}