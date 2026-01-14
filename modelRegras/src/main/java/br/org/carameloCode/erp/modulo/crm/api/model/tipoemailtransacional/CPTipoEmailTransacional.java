package br.org.carameloCode.erp.modulo.crm.api.model.tipoemailtransacional;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.transacionalMkt.tipo.TipoEmailTransacional;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoEmailTransacional.class)
public enum CPTipoEmailTransacional {
	_ID, _DESCRICAO, _CODIGOMAUTIC, _ATIVO;

	public static final String id = "id";
	public static final String descricao = "descricao";
	public static final String codigomautic = "codigoMautic";
	public static final String ativo = "ativo";
}