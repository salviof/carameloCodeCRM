package br.org.carameloCode.erp.modulo.crm.api.model.statuspedidoacesso;

import br.org.carameloCode.erp.modulo.crm.implemetation.model.autorizacao.StatusPedidoAcesso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = StatusPedidoAcesso.class)
public enum CPStatusPedidoAcesso {
	_ID, _NOME, _FABRICAVINCULADA;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String fabricavinculada = "fabricaVinculada";
}