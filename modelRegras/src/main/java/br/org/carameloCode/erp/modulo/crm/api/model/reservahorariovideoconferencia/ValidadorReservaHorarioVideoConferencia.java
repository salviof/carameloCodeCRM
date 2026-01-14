package br.org.carameloCode.erp.modulo.crm.api.model.reservahorariovideoconferencia;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraRemotoVideo;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = ReservaHoraRemotoVideo.class)
public @interface ValidadorReservaHorarioVideoConferencia {

	ValidadoresReservaHorarioVideoConferencia validador();
}