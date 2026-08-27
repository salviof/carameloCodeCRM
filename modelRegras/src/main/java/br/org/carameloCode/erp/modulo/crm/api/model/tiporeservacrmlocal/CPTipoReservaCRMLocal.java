package br.org.carameloCode.erp.modulo.crm.api.model.tiporeservacrmlocal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.TipoReservaCRMLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoReservaCRMLocal.class)
public enum CPTipoReservaCRMLocal {
	_METAFILTRODISP, _RELACIONEMANTOFILTRODISP;

	public static final String metafiltrodisp = "metaFiltroDisp";
	public static final String relacionemantofiltrodisp = "relacionemantoFiltroDisp";
}