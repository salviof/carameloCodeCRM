package br.org.carameloCode.erp.modulo.crm.api.model.pedidoacesso;

import br.org.carameloCode.erp.modulo.crm.implemetation.model.autorizacao.PedidoAcesso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = PedidoAcesso.class)
public enum CPPedidoAcesso {
	_ID, _USUARIOSOLICITANTE, _STATUS, _ACAO, _USUARIOCONCEDENTE, _DATAHORACRIACAO, _DATAHORAALTERACAO, _TIPOPEDIDO, _ATIVO;

	public static final String id = "id";
	public static final String usuariosolicitante = "usuarioSolicitante";
	public static final String status = "status";
	public static final String acao = "acao";
	public static final String usuarioconcedente = "usuarioConcedente";
	public static final String datahoracriacao = "dataHoraCriacao";
	public static final String datahoraalteracao = "dataHoraAlteracao";
	public static final String tipopedido = "tipoPedido";
	public static final String ativo = "ativo";
}