package br.org.carameloCode.erp.modulo.crm.api.model.metadadousuariocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.MetadadoUsuarioCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = MetadadoUsuarioCliente.class)
public enum ValoresLogicosMetadadoUsuarioCliente {
	CHAMADOSABERTOS, CHAMDOSRESOLVIDOS, TEXTOPROXIMARESERVA, RESERVASATIVAS
}