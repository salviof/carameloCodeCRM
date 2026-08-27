package br.org.carameloCode.erp.modulo.crm.api.model.tiporeservacrmremoto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.TipoReservaCRMRemoto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoReservaCRMRemoto.class)
public enum CPTipoReservaCRMRemoto {
	_METAFILTRODISP, _RELACIONEMANTOFILTRODISP;

	public static final String metafiltrodisp = "metaFiltroDisp";
	public static final String relacionemantofiltrodisp = "relacionemantoFiltroDisp";
}