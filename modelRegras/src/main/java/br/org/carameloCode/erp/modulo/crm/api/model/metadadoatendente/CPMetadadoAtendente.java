package br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.estatisticas.MetadadoAtendente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = MetadadoAtendente.class)
public enum CPMetadadoAtendente {
	_ID, _NOME, _USUARIO, _CHAMADOSEMATENDIMENTO, _CHAMADOSAGUARDANDOATENDIMENTO, _RESERVASATIVAS, _CLIENTESSATISFEITOS, _CLIENTESMUITOSATISFEITOS, _CLIENTESINSATISFEITOS, _SOLICITACOESFEITASPORMIMEQUIPE, _SOLICITACOESFEITASPARAMIM, _SOLICITACOESFEITASPORMIMPARACLIENTES;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String usuario = "usuario";
	public static final String chamadosematendimento = "chamadosEmAtendimento";
	public static final String chamadosaguardandoatendimento = "chamadosAguardandoAtendimento";
	public static final String reservasativas = "reservasAtivas";
	public static final String clientessatisfeitos = "clientesSatisfeitos";
	public static final String clientesmuitosatisfeitos = "clientesMuitoSatisfeitos";
	public static final String clientesinsatisfeitos = "clientesInsatisfeitos";
	public static final String solicitacoesfeitaspormimequipe = "solicitacoesFeitasPorMimEquipe";
	public static final String solicitacoesfeitasparamim = "solicitacoesFeitasParaMim";
	public static final String solicitacoesfeitaspormimparaclientes = "solicitacoesFeitasPorMimParaClientes";
}