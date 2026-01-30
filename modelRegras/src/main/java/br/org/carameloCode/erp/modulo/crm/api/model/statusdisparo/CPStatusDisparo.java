package br.org.carameloCode.erp.modulo.crm.api.model.statusdisparo;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa.StatusDisparo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = StatusDisparo.class)
public enum CPStatusDisparo {
	_ID, _NOME, _COR, _ICONESTATUS, _STATUSFABRICA;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String cor = "cor";
	public static final String iconestatus = "iconeStatus";
	public static final String statusfabrica = "statusFabrica";
}