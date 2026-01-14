package br.org.carameloCode.erp.modulo.crm.api.model.emailtransacionalmkt;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.transacionalMkt.EmailTransacionalMkt;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EmailTransacionalMkt.class)
public enum CPEmailTransacionalMkt {
	_ID, _DESCRICAO, _PESSOA, _DATAHORADISPARO, _USUARIODISPARO, _FOIENVIADO, _FOILIDO, _DATAHORALEITURA, _ICONESTATUS;

	public static final String id = "id";
	public static final String descricao = "descricao";
	public static final String pessoa = "pessoa";
	public static final String datahoradisparo = "datahoraDisparo";
	public static final String usuariodisparo = "usuarioDisparo";
	public static final String foienviado = "foiEnviado";
	public static final String foilido = "foiLido";
	public static final String datahoraleitura = "dataHoraLeitura";
	public static final String iconestatus = "iconeStatus";
}