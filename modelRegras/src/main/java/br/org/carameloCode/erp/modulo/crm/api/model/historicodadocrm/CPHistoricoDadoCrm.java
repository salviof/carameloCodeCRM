package br.org.carameloCode.erp.modulo.crm.api.model.historicodadocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.HistoricoDadoCrm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = HistoricoDadoCrm.class)
public enum CPHistoricoDadoCrm {
	_ID, _DADOCRM, _DATAVALOR, _DATAALTERACAO, _VALOR, _NOVOVALORSUBSTITUICAO;

	public static final String id = "id";
	public static final String dadocrm = "dadoCRm";
	public static final String datavalor = "dataValor";
	public static final String dataalteracao = "dataAlteracao";
	public static final String valor = "valor";
	public static final String novovalorsubstituicao = "novoValorSubstituicao";
}