package br.org.carameloCode.erp.modulo.crm.api.model.pedidoacessopessoa;

import br.org.carameloCode.erp.modulo.crm.implemetation.model.autorizacao.PedidoAcessoPessoa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = PedidoAcessoPessoa.class)
public enum CPPedidoAcessoPessoa {
	_PESSOA;

	public static final String pessoa = "pessoa";
}