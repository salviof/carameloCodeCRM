package br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarecebida;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRecebida;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarecebida.ValidadoresTipoAtvChamadaRecebida;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = TipoAtvChamadaRecebida.class)
public @interface ValidadorTipoAtvChamadaRecebida {

	ValidadoresTipoAtvChamadaRecebida validador();
}