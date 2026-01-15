package br.org.carameloCode.erp.modulo.crm.api.model.logemailrecebidolido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.LogEmailRecebidoLido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = LogEmailRecebidoLido.class)
public enum CPLogEmailRecebidoLido {
	_ID, _DESCRICAO, _EMAIL, _DATAHORA, _USUARIO;

	public static final String id = "id";
	public static final String descricao = "descricao";
	public static final String email = "email";
	public static final String datahora = "dataHora";
	public static final String usuario = "usuario";
}