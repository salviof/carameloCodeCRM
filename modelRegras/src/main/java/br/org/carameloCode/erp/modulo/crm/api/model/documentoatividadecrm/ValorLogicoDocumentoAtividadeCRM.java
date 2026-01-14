package br.org.carameloCode.erp.modulo.crm.api.model.documentoatividadecrm;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.api.model.documentoatividadecrm.ValoresLogicosDocumentoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.DocumentoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = DocumentoAtividadeCRM.class)
public @interface ValorLogicoDocumentoAtividadeCRM {

	ValoresLogicosDocumentoAtividadeCRM calculo();
}