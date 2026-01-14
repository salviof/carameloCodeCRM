package br.org.carameloCode.erp.modulo.crm.api.model.statusenvioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.StatusEnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = StatusEnvioEmail.class)
public enum CPStatusEnvioEmail {
	_ID, _DESCRICAO, _ICONESTATUS, _STATUSFABRICA;

	public static final String id = "id";
	public static final String descricao = "descricao";
	public static final String iconestatus = "iconeStatus";
	public static final String statusfabrica = "statusFabrica";
}