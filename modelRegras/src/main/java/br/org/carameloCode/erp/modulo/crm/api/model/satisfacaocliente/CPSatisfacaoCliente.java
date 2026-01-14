package br.org.carameloCode.erp.modulo.crm.api.model.satisfacaocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.cliente.satisfacao.SatisfacaoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SatisfacaoCliente.class)
public enum CPSatisfacaoCliente {
	_ID, _NOME, _ICONE;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String icone = "icone";
}