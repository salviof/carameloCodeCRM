package br.org.carameloCode.erp.modulo.crm.api.model.tipoparametrowhtzapmkt;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.TipoParametroWhtzapMkt;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoParametroWhtzapMkt.class)
public enum CPTipoParametroWhtzapMkt {
	_ID, _DESCRICAO, _TIPOPARAMETROENUM;

	public static final String id = "id";
	public static final String descricao = "descricao";
	public static final String tipoparametroenum = "tipoParametroEnum";
}