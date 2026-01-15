package br.org.carameloCode.erp.modulo.crm.api.model.statuschamado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.StatusChamado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = StatusChamado.class)
public enum CPStatusChamado {
	_ID, _NOME, _FABSTATUS;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String fabstatus = "fabStatus";
}