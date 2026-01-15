package br.org.carameloCode.erp.modulo.crm.api.model.orcamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = Orcamento.class)
public enum CPOrcamento {
	_ID, _USUARIOCRIOU, _DESCRICAO, _PESSOA, _VALORTOTAL, _VALORMENSALTOTAL, _VALORMENSALCOMDESCONTO, _PORCENTAGEMMENSALCOMDESCONTO, _VALORCOMDESCONTO, _PORCENTAGEMDESCONTO, _ITENSSAZONAIS, _ITEMRECORRENTE, _SERVICOOFERECIDO, _DATAHORACRIACAO, _DATAHORAEDICAO, _USUARIOEDITOU, _ORCAMENTOFATURA;

	public static final String id = "id";
	public static final String usuariocriou = "usuariocriou";
	public static final String descricao = "descricao";
	public static final String pessoa = "pessoa";
	public static final String valortotal = "valorTotal";
	public static final String valormensaltotal = "valorMensalTotal";
	public static final String valormensalcomdesconto = "valorMensalComDesconto";
	public static final String porcentagemmensalcomdesconto = "porcentagemMensalComDesconto";
	public static final String valorcomdesconto = "valorComDesconto";
	public static final String porcentagemdesconto = "porcentagemDesconto";
	public static final String itenssazonais = "itensSazonais";
	public static final String itemrecorrente = "itemRecorrente";
	public static final String servicooferecido = "servicoOferecido";
	public static final String datahoracriacao = "dataHoraCriacao";
	public static final String datahoraedicao = "dataHoraEdicao";
	public static final String usuarioeditou = "usuarioEditou";
	public static final String orcamentofatura = "orcamentoFatura";
}