package br.org.carameloCode.erp.modulo.crm.api.model.condicaofiltrorelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.filtroTipoRelacionamento.CondicaoFiltroRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = CondicaoFiltroRelacionamento.class)
public enum CPCondicaoFiltroRelacionamento {
	_ID, _DESCRICAO, _FILTRO, _TIPODADO, _TIPOCONDICAO, _VALOREMPACOTADO, _TOLERANCIAPARECIDO;

	public static final String id = "id";
	public static final String descricao = "descricao";
	public static final String filtro = "filtro";
	public static final String tipodado = "tipoDado";
	public static final String tipocondicao = "tipoCondicao";
	public static final String valorempacotado = "valorEmpacotado";
	public static final String toleranciaparecido = "toleranciaParecido";
}