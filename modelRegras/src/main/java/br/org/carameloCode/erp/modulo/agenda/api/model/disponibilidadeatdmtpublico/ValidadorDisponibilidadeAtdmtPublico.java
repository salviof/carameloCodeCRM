package br.org.carameloCode.erp.modulo.agenda.api.model.disponibilidadeatdmtpublico;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.agenda.api.model.disponibilidadeatdmtpublico.ValidadoresDisponibilidadeAtdmtPublico;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = DisponibilidadeAtdmtPublico.class)
public @interface ValidadorDisponibilidadeAtdmtPublico {

	ValidadoresDisponibilidadeAtdmtPublico validador();
}