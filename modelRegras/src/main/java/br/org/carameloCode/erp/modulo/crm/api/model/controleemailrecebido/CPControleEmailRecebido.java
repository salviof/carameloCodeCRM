package br.org.carameloCode.erp.modulo.crm.api.model.controleemailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.ControleEmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ControleEmailRecebido.class)
public enum CPControleEmailRecebido {
	_ID, _CODIGO, _REMETENTE;

	public static final String id = "id";
	public static final String codigo = "codigo";
	public static final String remetente = "remetente";
}