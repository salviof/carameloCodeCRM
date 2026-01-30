package br.org.carameloCode.erp.modulo.crm.api.model.disparoemmassa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa.DisparoEmMassa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = DisparoEmMassa.class)
public enum CPDisparoEmMassa {
	_ID, _DATADISPARO, _STATUSDISPAROFABRICA, _METARELACIONAMENTO, _RELACIONAMENTOS;

	public static final String id = "id";
	public static final String datadisparo = "dataDisparo";
	public static final String statusdisparofabrica = "statusDisparoFabrica";
	public static final String metarelacionamento = "metaRelacionamento";
	public static final String relacionamentos = "relacionamentos";
}