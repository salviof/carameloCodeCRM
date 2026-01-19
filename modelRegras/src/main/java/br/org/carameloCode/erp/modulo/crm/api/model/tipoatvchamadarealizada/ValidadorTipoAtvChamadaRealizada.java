package br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarealizada;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRealizada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarealizada.ValidadoresTipoAtvChamadaRealizada;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = TipoAtvChamadaRealizada.class)
public @interface ValidadorTipoAtvChamadaRealizada {

	ValidadoresTipoAtvChamadaRealizada validador();
}