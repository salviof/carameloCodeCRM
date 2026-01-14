package br.org.carameloCode.erp.modulo.crm.api.model.metadadousuariocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.MetadadoUsuarioCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = MetadadoUsuarioCliente.class)
public enum CPMetadadoUsuarioCliente {
	_ID, _NOME, _USUARIO, _CHAMADOSABERTOS, _CHAMDOSRESOLVIDOS, _TEXTOPROXIMARESERVA, _RESERVASATIVAS;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String usuario = "usuario";
	public static final String chamadosabertos = "chamadosAbertos";
	public static final String chamdosresolvidos = "chamdosResolvidos";
	public static final String textoproximareserva = "textoProximaReserva";
	public static final String reservasativas = "reservasAtivas";
}