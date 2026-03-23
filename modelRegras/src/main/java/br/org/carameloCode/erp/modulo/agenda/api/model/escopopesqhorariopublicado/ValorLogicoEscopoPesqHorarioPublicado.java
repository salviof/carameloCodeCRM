package br.org.carameloCode.erp.modulo.agenda.api.model.escopopesqhorariopublicado;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.agenda.api.model.escopopesqhorariopublicado.ValoresLogicosEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = EscopoPesqHorarioPublicado.class)
public @interface ValorLogicoEscopoPesqHorarioPublicado {

	ValoresLogicosEscopoPesqHorarioPublicado calculo();
}