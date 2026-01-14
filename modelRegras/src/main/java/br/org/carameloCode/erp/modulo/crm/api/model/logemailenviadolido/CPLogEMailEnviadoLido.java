package br.org.carameloCode.erp.modulo.crm.api.model.logemailenviadolido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.LogEMailEnviadoLido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = LogEMailEnviadoLido.class)
public enum CPLogEMailEnviadoLido {
	_ID, _DESCRICAO, _EMAIL, _DATAHORA, _CIDADE, _JSONCOMPLETO;

	public static final String id = "id";
	public static final String descricao = "descricao";
	public static final String email = "email";
	public static final String datahora = "dataHora";
	public static final String cidade = "cidade";
	public static final String jsoncompleto = "jsonCompleto";
}