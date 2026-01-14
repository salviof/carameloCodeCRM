package br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValidadoresEscopoPesquisaMelhorHorario;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = EscopoPesquisaMelhorHorario.class)
public @interface ValidadorEscopoPesquisaMelhorHorario {

	ValidadoresEscopoPesquisaMelhorHorario validador();
}