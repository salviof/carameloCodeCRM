package br.org.carameloCode.erp.modulo.crm.api.model.fluxodeatividades;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.fluxoAtividade.FluxoDeAtividades;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = FluxoDeAtividades.class)
public enum CPFluxoDeAtividades {
	_ID, _NOME, _RELACIONAMENTOS, _RELACIONAMENTOINICIAL, _RESULTADOS;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String relacionamentos = "relacionamentos";
	public static final String relacionamentoinicial = "relacionamentoInicial";
	public static final String resultados = "resultados";
}