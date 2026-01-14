package br.org.carameloCode.erp.modulo.crm.api.model.caixapostalestatisticas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.caixaPostal.CaixaPostalEstatisticas;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = CaixaPostalEstatisticas.class)
public enum CPCaixaPostalEstatisticas {
	_ID, _DESCRICAO, _CAIXAPOSTAL, _QUANTIDADENAOLIDOCONHECIDO, _QUANTIDADENAOLIDODESCONHECIDO, _QUANTIDADENAOLIDOPESSOAL;

	public static final String id = "id";
	public static final String descricao = "descricao";
	public static final String caixapostal = "caixaPostal";
	public static final String quantidadenaolidoconhecido = "quantidadeNaoLidoConhecido";
	public static final String quantidadenaolidodesconhecido = "quantidadeNaoLidoDesconhecido";
	public static final String quantidadenaolidopessoal = "quantidadeNaoLidoPessoal";
}