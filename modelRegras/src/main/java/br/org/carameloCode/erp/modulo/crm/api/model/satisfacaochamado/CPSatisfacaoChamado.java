package br.org.carameloCode.erp.modulo.crm.api.model.satisfacaochamado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.SatisfacaoChamado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SatisfacaoChamado.class)
public enum CPSatisfacaoChamado {
	_ID, _NOME, _ICONE, _FABTIPORESOLUCAO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String icone = "icone";
	public static final String fabtiporesolucao = "fabTipoResolucao";
}