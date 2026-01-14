package br.org.carameloCode.erp.modulo.crm.api.model.metadadousuariocliente;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadousuariocliente.ValoresLogicosMetadadoUsuarioCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.MetadadoUsuarioCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@InfoReferenciaEntidade(tipoObjeto = MetadadoUsuarioCliente.class)
public @interface ValorLogicoMetadadoUsuarioCliente {

	ValoresLogicosMetadadoUsuarioCliente calculo();
}