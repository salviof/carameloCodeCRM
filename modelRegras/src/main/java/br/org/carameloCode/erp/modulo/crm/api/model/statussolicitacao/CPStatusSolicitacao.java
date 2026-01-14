package br.org.carameloCode.erp.modulo.crm.api.model.statussolicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.StatusSolicitacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = StatusSolicitacao.class)
public enum CPStatusSolicitacao {
	_ID, _NOME, _ICONE;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String icone = "icone";
}