package br.org.carameloCode.erp.modulo.crm.api.model.tipoeventoagendacrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.TipoEventoAgendaCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoEventoAgendaCRM.class)
public enum CPTipoEventoAgendaCRM {
	_METADISPONIVEL, _RELACIONAMENTOSDISPONIVEL;

	public static final String metadisponivel = "metadisponivel";
	public static final String relacionamentosdisponivel = "relacionamentosDisponivel";
}