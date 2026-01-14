package br.org.carameloCode.erp.modulo.crm.api.model.integracaomanual;

import br.org.carameloCode.erp.modulo.crm.implemetation.model.integracao.IntegracaoManual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = IntegracaoManual.class)
public enum CPIntegracaoManual {
	_ID, _NOME, _CODIGOPESSOA, _NOMEINTEGRACAO, _VALOR;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String codigopessoa = "codigoPessoa";
	public static final String nomeintegracao = "nomeIntegracao";
	public static final String valor = "valor";
}