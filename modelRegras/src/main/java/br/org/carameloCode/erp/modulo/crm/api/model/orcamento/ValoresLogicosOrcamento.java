package br.org.carameloCode.erp.modulo.crm.api.model.orcamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = Orcamento.class)
public enum ValoresLogicosOrcamento {
	DESCRICAO, VALORTOTAL, VALORMENSALTOTAL, ITENSSAZONAIS, ITEMRECORRENTE, ORCAMENTOFATURA
}