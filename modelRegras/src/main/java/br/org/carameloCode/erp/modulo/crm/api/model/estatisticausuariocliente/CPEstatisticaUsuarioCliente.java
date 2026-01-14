package br.org.carameloCode.erp.modulo.crm.api.model.estatisticausuariocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.MetadadoUsuarioCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = MetadadoUsuarioCliente.class)
public enum CPEstatisticaUsuarioCliente {
	_ID, _USUARIO, _CHAMADOSABERTOS, _CHAMDOSRESOLVIDOS, _ENCONTROSAGENDADOS;

	public static final String id = "id";
	public static final String usuario = "usuario";
	public static final String chamadosabertos = "chamadosAbertos";
	public static final String chamdosresolvidos = "chamdosResolvidos";
	public static final String encontrosagendados = "encontrosAgendados";
}